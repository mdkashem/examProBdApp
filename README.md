# examPro API
## Introduction
# 1.1	Purpose: 
This software is intended for any Organization or Teacher to test their candidates or students’ knowledge of a specific topic such as Java, Python, SQL, General knowledge, English etc. 
# 1.2	Product Scope: 
This application will help to generate multiple choice quiz and evaluate candidate’s knowledge. Also, this application will allow subscribers to take mock exam. 
# 2	Overall Description 
## 2.1	product Perspective 
This product is a web application intended to require any device that support web browser. 
# API Description
 - Admin role:
    - Admin can view list of users
    - Admin can insert/register for  new user
    - admin can update information of any user
    - admin can search any user by id 

 - User roles:
    - User can login
    - user can take quiz 
    - submit selected answer
    - update his information 
## End Points for admin roles
- POST: / login
    - to login as admin role
- GET: /user/all
    - to see all existing users 
- GET: /user?id=
    - find user by their id 
- GET: /subject/all
    - to see all subject
- POST: /user/create
    - to create new user 
- POST: /quiz/create
    - to create question for a topic of a subject 
- POST: /update  
    - update selected user's information 
## End points for user role test 
- POST: /login 
    - to login as a regular user
- GET: /take/quiz
    - to take an exam 
- POST: /submit/quiz
    - to submit the quiz with the selected answers
- GET: /user/info
    - to user's information 
- GET: /logout
    - to logout 
## DATABASE TABLES 
![Relational Database Table](./images/data_table.png)
## Documentation included in the docs folder 
### If you open the index.html in a browser it should be look like this image bellow:
![Sample image](./images/documentation.PNG)
### Technologies :
- JAVA 8
- postGreSQL 10
- Maven
    - Dependencies:
        - Junit 4.13.1
        - Jackson 2.11.4
        - servlet 4
        - postgreSQL 42.2.14
        - log4j 1.2.17
        - JAVA 1.8 
- Tomcat 8.5



