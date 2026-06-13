package com.example.demo.service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import com.example.demo.model.SecurityReport;

@Service
public class HttpCheckService {

    private final RestTemplate restTemplate;

    public HttpCheckService() {
        this.restTemplate = createBrowserLikeRestTemplate();
    }

    private RestTemplate createBrowserLikeRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    
    // Добавляем перехватчик, который подставляет заголовки браузера
    restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
        request.getHeaders().set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        request.getHeaders().set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        request.getHeaders().set("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        request.getHeaders().set("Sec-Ch-Ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"");
        request.getHeaders().set("Sec-Ch-Ua-Mobile", "?0");
        request.getHeaders().set("Sec-Ch-Ua-Platform", "\"Windows\"");
        request.getHeaders().set("Upgrade-Insecure-Requests", "1");
        
        return execution.execute(request, body);
    }));
    
    return restTemplate;
}

    private static final List<String> CSP_HEADERS = Arrays.asList(
            "Content-Security-Policy",
            "Content-Security-Policy-Report-Only",
            "X-Content-Security-Policy",
            "X-WebKit-CSP");

    private static final List<String> HSTS_HEADERS = Arrays.asList(
            "Strict-Transport-Security",
            "X-Strict-Transport-Security");

    private static final List<String> INFO_HEADERS = Arrays.asList(
            "Server",
            "X-Powered-By");


    public SecurityReport analyze(String userInput) {

        String urlString = userInput;
        if (!urlString.startsWith("http")) {
            urlString = "https://" + urlString;
        }

        URI url = URI.create(urlString);

        SecurityReport report = new SecurityReport();
        report.setUrl(urlString);
        report.setHasHttps("https".equals(url.getScheme()));

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            HttpHeaders headers = responseEntity.getHeaders();

            report.setStatusCode(responseEntity.getStatusCode().value());

            boolean hasCsp = serhHender(CSP_HEADERS, headers);
            boolean hasHsts = serhHender(HSTS_HEADERS, headers);
            boolean serverInfo = serhHender(INFO_HEADERS, headers);

            report.setHasCsp(hasCsp);
            report.setHasHsts(hasHsts);
            report.setHasXFrameOptions(headers.containsKey("X-Frame-Options"));
            report.setHasXContentTypeOptions(headers.containsKey("X-Content-Type-Options"));
            report.setHasReferrerPolicy(headers.containsKey("Referrer-Policy"));
            report.setRevealsServerInfo(serverInfo);

            if (serverInfo) {
                String serverValue = headers.getFirst("Server");
                String poweredByValue = headers.getFirst("X-Powered-By");
                report.setServerInfo(serverValue != null ? serverValue : poweredByValue);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return report;
    }

    public boolean serhHender(List<String> list, HttpHeaders headers) {
        for (String heander : list) {
            if (headers.containsKey(heander))
                return true;
        }

        return false;
    }
}
