## Welcome to LYCEENA Application 

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