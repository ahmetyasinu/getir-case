# Getir Application

ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day.

In order to run this application, Docker should be build with 'docker-compose build' and then run with 'docker-compose up' commands. (In case of any errors maven clean install can be executed)

Before starting to try the end points, token should be generated and added as a Header in the Postman requests.
Postman requests are prepared and placed in 'resources' folder (getir-case/src/main/resources/postman).

-------------------------------------

#### **Used Technologies**
-Java 11

-SpringBoot

-MongoDB

-Maven

-Docker

-------------------------------------

#### **Testing**
-Integration Tests for each end-point and repository classes

-Unit Tests for each service classes


-------------------------------------

#### **Authentication**

**POST**: *localhost:8080/authenticate*

JSON:

{
"username": "getir",
"password": "password"
}

Response:

{
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnZXRpciIsImV4cCI6MTY0NDIyNDg5NSwiaWF0IjoxNjQ0MTg4ODk1fQ.OYE-dC-mZBuCD81lONeWl2LTkYzH3CXGlGH1aSCDMi0FjILv1azktVFNWIdkkFJvfQR8D5HEoA6nukuATlXUCg"
}


-------------------------------------

#### **Swagger**

Reading is Good

http://localhost:8080/swagger-ui.html

![image](https://github.com/cavlanece/getir-case/blob/master/swagger.PNG)
-------------------------------------

### **APIs**


#### **1-Customer**

-Persist new customers

**POST**: *localhost:8080/customer/add*

-Query all orders of the customer with pagination

**GET**: *localhost:8080/customer/getAllOrdersByCustomerId?id=6200629831a535752ff1d47f*


#### **2-Book**

-Persist new book

**POST**: *localhost:8080/book/add*

-Update book’s stock

**POST**: *localhost:8080/book/updateStock*


#### **3-Order**

-Persist new order

**POST**: *localhost:8080/order/add*

-Query order by id

**GET**: *localhost:8080/order/getById?id=620062e331a535752ff1d480*

-List orders by date interval

**GET**: *localhost:8080/order/getByDateInterval?startDate=2002-02-05 00:09:02&endDate=2022-02-07 23:09:03*


#### **4-Statistics**

-Serve customer’s monthly order statistics

**GET**: *localhost:8080/statistics/getMonthlyStatistics?customerId=6200629831a535752ff1d47f*


-------------------------------------
