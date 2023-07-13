# innova-case-study
 Innova Hackathon Case Study 

## Innova Expense Tracking API

Bu proje, kişisel masrafların takibi için bir backend API sağlar.

## Kullanılan Teknolojiler

- Java 17
- Spring Boot 3.1.1
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL Connector
- Spring Boot DevTools
- Spring Boot Validation
- Lombok
- JSON Web Token (JWT)
## Proje Yapısı

Proje, aşağıdaki temel paket yapısına sahiptir:

- `com.emretaskin.innova.controller`: API endpoint'lerini içeren controller sınıfları
- `com.emretaskin.innova.dto.request`: API isteklerini temsil eden request DTO sınıfları
- `com.emretaskin.innova.dto.response`: API cevaplarını temsil eden response DTO sınıfları
- `com.emretaskin.innova.entity`: Veritabanı varlıklarını temsil eden entity sınıfları
- `com.emretaskin.innova.exception`: Hata durumlarını temsil eden exception sınıfları
- `com.emretaskin.innova.repository`: Veritabanı işlemlerini gerçekleştiren repository sınıfları
- `com.emretaskin.innova.service`: İş mantığını gerçekleştiren servis sınıfları ve arayüzleri
- `com.emretaskin.innova.security`: Güvenlik konfigürasyonunu ve JWT yetkilendirme işlemlerini içeren sınıflar
- `com.emretaskin.innova.config`: Spring yapılandırma sınıfları

## Projeyi Çalıştırma

Proje Docker kullanılarak başlatılabilir. Docker Compose dosyası projeye dahil edilmiştir ve backend API'si ile MySQL veritabanını başlatır.

Öncelikle, proje dizininde aşağıdaki komutu çalıştırarak Docker imajını oluşturun:

`docker-compose build`


Docker imajı oluşturulduktan sonra aşağıdaki komutla projeyi başlatabilirsiniz:

`docker-compose up`


Backend API, `http://localhost:8086` adresinde çalışacak şekilde başlatılacaktır.

## API Dokümantasyonu

API dokümantasyonu için Swagger kullanılmıştır. Başlatılan projenin Swagger UI'sına aşağıdaki URL ile erişebilirsiniz:

- [Swagger UI](http://localhost:8086/swagger-ui.html)


## Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasını inceleyebilirsiniz.
