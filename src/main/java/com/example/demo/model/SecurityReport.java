package com.example.demo.model;

import java.util.Map;

public class SecurityReport {
    
    private String url;
    private boolean hasHttps;
    private boolean hasHsts;
    private boolean hasCsp;
    private boolean hasXFrameOptions;
    private boolean hasXContentTypeOptions;
    private boolean hasReferrerPolicy;
    private boolean revealsServerInfo;
    private String serverInfo;
    private int statusCode;
    private Map<String, String> headers;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isHasHttps() {
        return hasHttps;
    }
    public void setHasHttps(boolean hasHttps) {
        this.hasHttps = hasHttps;
    }
    public boolean isHasHsts() {
        return hasHsts;
    }
    public void setHasHsts(boolean hasHsts) {
        this.hasHsts = hasHsts;
    }
    public boolean isHasCsp() {
        return hasCsp;
    }
    public void setHasCsp(boolean hasCsp) {
        this.hasCsp = hasCsp;
    }
    public boolean isHasXFrameOptions() {
        return hasXFrameOptions;
    }
    public void setHasXFrameOptions(boolean hasXFrameOptions) {
        this.hasXFrameOptions = hasXFrameOptions;
    }
    public boolean isHasXContentTypeOptions() {
        return hasXContentTypeOptions;
    }
    public void setHasXContentTypeOptions(boolean hasXContentTypeOptions) {
        this.hasXContentTypeOptions = hasXContentTypeOptions;
    }
    public boolean isHasReferrerPolicy() {
        return hasReferrerPolicy;
    }
    public void setHasReferrerPolicy(boolean hasReferrerPolicy) {
        this.hasReferrerPolicy = hasReferrerPolicy;
    }
    public boolean isRevealsServerInfo() {
        return revealsServerInfo;
    }
    public void setRevealsServerInfo(boolean revealsServerInfo) {
        this.revealsServerInfo = revealsServerInfo;
    }
    public String getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    
      

}
