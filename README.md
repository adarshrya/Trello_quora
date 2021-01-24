# Trello_quora

Course 5 - Project

**Teamates**

- Chayan Debnath
- Sanath Swaroop
- Adarsha Acharya

**System Requirement**

- Java 1.8
- Maven 3.6.3
- PostgreSQL DB

**Code Setup**
```
Open CMD run below commands
git clone https://github.com/adarshrya/Trello_quora.git
cd Trello_Quora
mvn clean -Psetup 
mvn clean install -PskipTest
```
Goto http://localhost:8080/api/swagger-ui.html

# Api List

**UserController**

 - /user/signup
 - /user/signin
 - /user/signout

**CommonController**

 - /userprofile/{userId}

**AdminController**

 - /admin/user/{userId}

**QuestionController**

 - /question/create
 - /question/all
 - /question/edit/{questionId}
 - /question/delete/{questionId}
 - /question/all/{userId}

**AnswerController**

 - /question/{questionId}/answer/create
 - /answer/edit/{answerId}
 - /answer/delete/{answerId}
 - /answer/all/{questionId}
