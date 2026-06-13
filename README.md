# 🔍 Анализатор HTTP-заголовков безопасности

Бэкенд-сервис на Spring Boot, который проверяет настройки безопасности любого сайта.

---

## Что умеет

- Принимает URL и проверяет HTTPS, HSTS, CSP, X-Frame-Options, X-Content-Type-Options, Referrer-Policy
- Определяет, скрывает ли сервер информацию о себе (Server, X-Powered-By)
- Маскируется под браузер Chrome, чтобы сайты не блокировали запросы
- Возвращает отчёт в HTML или JSON

---

## Технологии

- Java 17
- Spring Boot 3
- Maven
- RestTemplate
- HTML / CSS

---

## Запуск

### Локально

```bash
git clone https://github.com/SComik/url-check.git
cd url-check
./mvnw clean package
java -jar target/*.jar
```

Открой http://localhost:8080

---

## Демо

https://url-check-mtym.onrender.com

---

## Пример

<img width="595" height="450" alt="image" src="https://github.com/user-attachments/assets/d926d5ad-b7bf-42c3-a5a8-8a5c022a3fe1" />

