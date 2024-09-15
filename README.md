# Online Sipariş Sistemi - Product Microservice Modülü

Bu servis, Spring boot üzerinde DDD kullanılarak geliştirilmiştir.
application, domain ve infrastructure katmanları bulunmaktadır.

Order microservisi ile iletişim, event'ler üzerinden Apache Kafka ile sağlanmaktadır.
Apache Kafka uygulamasının localhost:9092'de çalışıyor olması gerekmektedir.

Bir Product oluşturulduğunda, Order servisine productCreateEvent'i gönderilmekte ve Order servisinde
productID'nin tutulması sağlanmaktadır.
Order tarafından createOrder event'i geldiğinde ise, Product stock kontrolleri yapılmakta ve miktara göre başarılı/başarsızı cevap dönülmektedir.

Database olarak PostgreSQL kullanılmaktadır, compose.yaml dosyası üzerinde yapılandırılmıştır ve 
spring boot uygulaması ayağa kaldırılırken database servisi de başlatılmaktadır.

Spring boot uygulaması Run edildiğinde Product microservisi ve PostgreSQL servisi çalışır duruma gelmektedir.

REST API'ları:
Product findAll ve save ile ilişkili GET ve POST metodlarına Swagger üzerinden erişilebilir.
http://localhost:8080/swagger-ui/index.html

