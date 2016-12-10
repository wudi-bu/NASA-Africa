E-Soil
===============


## Summary

This document describes the soil property prediction and crop suggestion website of the team of Anindya Paul,
Archana Bajaj, Di Wu and Litao Chen. This is project is originated from a keggle competation which requires to
predict 5 target soil functional properties from diffuse reflectance
infrared spectroscopy measurements.  We implemented the algorithm and further add features like providing suitable crop options based on soil property and suggesting furtilizers to maximize user benefit. More over, because the data provided by keggle is only from African soil, we use SOIL-GRID API to get soil property from all over the world.
In general, E-soil is a powerful tool for farmers, scientists and agricultural companies. 


## Use Cases

Users: 
(1)Farmers in Africa can use this product to get suggestions on which crop is most suitable to grow based on soil element content.
(2)Agricultural Companies wishing to maximize their profit by choosing the most suitable to grow based on soil element content
       or an agricultural Company whose main rigion is in the east and willing to go east, they need infomation on soil property.
(3)agrologists wishing to keep track of soil varience per year after cultivation.
       
Paying customers:
(1)governments:In short term,help African farmers,in the long term,by the year of 2030,we will be facing a food crisis,
if all countries in the wrold decide to cooperate in grwing crops by only grow the most suitable crops to increase food production,
our product is a prototype.
(2)agricultural Companies
(3)agriculture labs. 
(4)Furthermore, the website can be integrated to connect
                  with amazon (after suggesting the best crop to grow, we provide possible sellers of seeds and fertilizers via Amazon)
                  
Beneficiaries: Farmers, Agricultural Researchers.  


## Functionality description
Our website supports 2 usage modes. One is that the user can simply click on the map shown on our front page and if the soil property of that specific location is provided by SOIL-GRID API, the user will see a pop window with the suggested crops and the correspnding accuracy along with what kinds of fertilizers to use to make sure the soil is most proper to grow that specific crops. The other mode is that the user can upload soil properties manually to have us predict which crops to grow and so forth. In this mode, user can also upload the soil spectrum file, we will provide soil property along with the crop prediction information.

## Dependencies and Setup Instructions
Web development:Eclipse Neon.1a Release (4.6.1)
DataBase: Mysql
Server: Apache Tomcat v8.5
JAVA: JDK1.80
Our project will be running on cloud(AWS) until the end of this semester, this is our link http://ec2-35-160-122-226.us-west-2.compute.amazonaws.com:8080/InteractiveMap/. If you are in any interest in setting up another instance, here is an instruction of how to do that. First, you need to download JAVA JDK 1.80, here is a link to that http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html . Do remember the directory of the JDK, you will be needing that to setup Eclipse environment. Second, you need to download the newest version of Mysql, here is a link to that http://www.mysql.com/downloads/ , then you can create a new database and then import the database file provided in the dababase folder. Third, you need to download Tomcat, here is a link to that https://tomcat.apache.org. Finally, you need to download Eclipse, here is a link to that, http://www.eclipse.org/neon/. After installing Eclispe, you should setup the JAVA JDK build path according to the directory of your JDK. Then you can import our project into Eclipse. Our project can be found in the InteractiveMap branch. You need to chage the build path of the project and change the database connection class based on your database information along with the Tomcat directory on your computer. Then use ANT to create a WAR file and deploy the WAR file on Tomcat. You are all set to go.
