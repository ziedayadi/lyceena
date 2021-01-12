## Welcome to LYCEENA Application 

### Start the application 
#### H2 database
_**Lyceena**_ now work with h2 database in server mode, to start it: 
```bat
java -cp %HOMEPATH%\.m2\repository\com\h2database\h2\1.4.200\h2-1.4.200.jar org.h2.tools.Server -ifNotExists
```

##### Authentication server
_**Lyceena**_ Authentication process is **_OAuth2_**, the used server is **_keycloak_**
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
 2. Adminisator login/password: **_bael-admin/admin_**
 1. Create realm **_lyceena_**
 2. Create clien **_lyceena-client_**
 3. Create roles _**admin**_ and **_student_** in lyceena-client
 4. Create roles **_app-admin_** and **_app-client_** in lyceena realm 
 5. make app-admin and app-client composite roles and add them respectively to admin and student
 6. create user1 and user2 
 7. give each user a role from tha realm roles 
 8. Reset password for users
 
 ##### Resources server
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
 ###### Set the client ID
 Get the client ID and set it in the file `authentication-service.ts` 
 ###### Build
It is an anular project, 
 ```bat
npm install
ng serve 
 ```