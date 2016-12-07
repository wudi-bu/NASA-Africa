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
Our website supports 2 usage modes. One is that the user can simply click on the map and if the soil property of that specific location is provided by SOIL-GRID API, the user will see a pop window with the suggested crops and the correspnding accuracy along with what kinds of fertilizers to use to make sure the soil is most proper to grow that specific crops. The other is that the user can upload soil properties manually to have us predict which crops to grow and so forth. In this mode, user can also upload the soil spectrum file, we will provide soil property along with the crop prediction information.

