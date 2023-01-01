# **INFORMASI PROJEK**

> ## Projek Crud Test Waizly

![ERD Picture](spring-product.png) 

Bahasa Pemrograman: __*JAVA version 11.0.15*__

Framework : __*Spring boot version 2.7.8*__

DBMS : __*MYSQL*__

IDE : __*Visual Studio Code*__


> ## Dependencies
✬ spring-boot-devtools                     
✬ spring-boot-starter-data-jpa                      
✬ spring-boot-starter-validation                          
✬ spring-boot-starter-web                      
✬ springdoc-openapi-ui   
✬ modelmapper
✬ mysql-connector-j
✬ spring-boot-starter-test

> ## Entity 

   
- ### Category, atribut: 
    - id
    - name *(wajib diisi)*
  
- ### Product, atribut:
    - id
    - name *(wajib diisi)*
    - description *(wajib diisi)*
    - price *(wajib diisi)*

- ### Supplier, atribut:
    - id
    - name *(wajib diisi)*
    - address *(wajib diisi)*
    - email *(unique - wajib diisi)*


> ## Relasi Antar Entitas / Assocision
- Many To One : Product -> Category
- Many To Many : Product -> Supplier

> ## Format Response 
- Status true *(Boolean)*
- Pesan berhasil *(String)*
- Data yang dikembalikan *(Data)*


