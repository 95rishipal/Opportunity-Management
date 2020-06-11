Front End --> Angular Material 
Back End --> Spring Boot

Project Timeline 
[27/05/2020]  =  Setup Spring Boot and Angular Material. 
[28/05/2020]  =  Setup Components and Created End Points.
[29/05/2020] =   Connectivity of Opportunity Component.
[30/05/2020] =   JPA to JDBC End Points
[31/05/2020] =   UI of Project and Integrate all Componets 
[02/06/2020] =   Look and Feel of Project
[03/06/2020] =   Issue Resolve
[04/06/2020] =    Search Bar Setup & Audit Table
Copyright  with Accolite India 
<h3 align="center">Home Page<h3>
 <img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/Home%20Page.png?raw=true">
  
  <h3 align="center">Opportunity Page<h3>
 <img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/Opportunity%20Page.png?raw=true">
 <img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/Create%20Opportunity%20Dialog.png">
  
  
<h3 align="center">Trend Page<h3>
 <img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/Trends%20Page.png?raw=true">
  
  <h3 align="center">Search Page<h3>
 <img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/Search%20Page.png">
  
  <h3 align="center">Audit Page<h3>
 <img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/Audit%20Page.png">
<h3 align="center">Code Coverage<h3>
<img src="https://github.com/95rishipal/Opportunity-Management/blob/master/Img/ControllerCodeCoverage+Interceptor+Model+Dao.JPG?raw=true">


#!/bin/bash

#File: tree-md

tree=$(tree -tf --noreport -I '*~' --charset ascii $1 |
       sed -e 's/| \+/  /g' -e 's/[|`]-\+/ */g' -e 's:\(* \)\(\(.*/\)\([^/]\+\)\):\1[\4](\2):g')

printf "# Project tree\n\n${tree}"
