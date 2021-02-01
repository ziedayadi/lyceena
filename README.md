## Welcome to LYCEENA Application 

### Github repository
https://github.com/ziedayadi/lyceena


### Technologies
##### BACK-END:
- Java 12
- spring-boot 2.4.0
- spring-data-jpa
- spring-web : REST web-services 
- spring-security oauth2 
- Lombok
- spring-test , mock-mvc 
- jacoco for test coverage

##### FRON-END
- Angular 9

### Prerequisites 

- JDK 12 or more 
- H2 jar file 
- keycloak serve see : https://www.keycloak.org/downloads

### Start the application 

#### H2 database
_**Lyceena**_ now work with h2 database in server mode, to start it: 
```bat
java -cp %HOMEPATH%\.m2\repository\com\h2database\h2\1.4.200\h2-1.4.200.jar org.h2.tools.Server -ifNotExists
```

##### Authentication server
* _**Lyceena**_ Authentication process is **_OAuth2_**, the used server is **_keycloak_**
* go to `{KEYCLOAK_INSTALLATION_DIRECTORY}\standalone\configuration\standalone.xml` and update port number to 8083
* execute  `{KEYCLOAK_INSTALLATION_DIRECTORY}\bin\standalone.bat`

###### Build
go to authentication-server and run 
```bat
mvn clean install
```
###### Run
go to authentication-server and run 
```bat
java -jar target\lyceena-auth-server-1.0-SNAPSHOT.jar
```
###### configure the authentication server
 1. connect to keycloak administration console http://localhost:8083/auth/
 2. Adminisator login/password: **_admin/admin_**
 1. Create realm **_lyceena_**
 2. Create clien **_lyceena-client_**
 4. Create roles **ADMIN** lyceena realm 
 6. create user1 and user2 
 7. give each user a role from tha realm roles 
 8. Reset password for users
 
 ##### Resources server
 ###### Configure
 Set signing key from keycloak/lyceena releam/keys in application.properties
 
 ###### Envirenemt variables 
| Envirement variable       | description  | default value |
|-------------------|--------------|  --------------| 
|  DATASOURCE_URL   | datasouce url for the BD | jdbc:h2:tcp://localhost/~/LYCEENA_H2 |
|  SERVER_PORT   | server port | 7071 | 
|  DRIVER_CLASSNAME   | Driver class name | org.h2.Driver | 
|  DATASOURCE_USERNAME   | Datasource user name (DB) | sa | 
|  JPA_DIALECT   | Jpa dialect |  org.hibernate.dialect.H2Dialect| 
|  DDL_AUTO   | DDL Auto (create, update) |  create| 
|  SHOW_SQL   | Hibernate show sql |  false| 
|  JWT_ISSUER_URI   | JWT ISSUER URI |  `http://localhost:8083/auth/realms/lyceena` | 
|  JWT_SET_URI   | JWT CERTS URI | `http://localhost:8083/auth/realms/lyceena/protocol/openid-connect/certs`  | 
|  JWT_SIGNING_KEY   | JWT Signing key | **NONE**| 
|  LOG_LEVEL   | LOG LEVEL | info  | 


 ###### Build
 It is a spring boot application, go to /backend and run: 
 ```bat
 mvn clean install
 ```
###### Run
go to backend and run 
```bat
java -jar target\lyceena-1.0-SNAPSHOT.jar
```
 ##### Front-end project
 ###### Set the client secret
 Get the **client Secret** and set it in the file `authentication-service.ts` 
 ###### Build
It is an anular project, 
 ```bat
npm install
ng serve 
 ```