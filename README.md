E-Soil
===============


## Summary

This document describes the soil property prediction and crop suggestion website of the team of Anindya Paul,
Archana Bajaj, Di Wu and Litao Chen. This is project is originated from a keggle competation which is a soil property prediction
algorithm based on input spectrum. We implemented the algorithm and further add features like providing suitable crop options based on soil property and suggesting furtilizers to maximize user benefit.
In general, E-soil is a powerful tool for farmers, scientists and agricultural companies. 




2nd prize solution approach to Africa Soil
Property Prediction Challenge. [Soil-Prediction challenge](http://www.kaggle.com/c/afsis-soil-properties).
In this competition it is required to
predict 5 target soil functional properties from diffuse reflectance
infrared spectroscopy measurements. 
The solution consists of two steps: Data preprocessing and model
prediction. For the preprocessing stage, we used 2 methods, one
applied for target 1-4 (PIDN/Ca/P/pH/SOC ) and the other for target 5 (Sand).
The second step was to feed the processed features to a neural
network. In order to ensure that the CV error is stabilized, we had
to average enough models. We ended up with 100 models to get
reasonably stable error.  
