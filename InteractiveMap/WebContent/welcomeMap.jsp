<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<style>
		.center {
		    margin: auto;
			width: 60%;
		    padding: 10px;
		}
		table {
		    font-family: arial, sans-serif;
		    border-collapse: collapse;
		    width: 100%;
		}
		td, th {
		    border: 1px solid #dddddd;
		    text-align: left;
		    padding: 8px;
		}
		
		tr:nth-child(even) {
		    background-color: #dddddd;
		}
		footer {
		    padding: 0.3em;
		    color: white;
		    background-color: black;
		    clear: left;
		    text-align: center;
		}
		.header {
		    padding: 0em;
		    color: white;
		    background-color: green;
		    clear: left;
		    text-align: center;
		}
		.solid {
			border-style: solid;
		}
	</style>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>eSoil - Tools for a greener planet</title>
		<link rel="icon" type="image/png" href="images/logo.png" />
	</head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<body background="images/c.jpg">
		<img src="images/final.PNG" alt="Farmer" style="width:100%;height:auto;" />
		
		<div id="screen" style="width:100%;height:100%; z-index: 99;">
	        <div id="map" style="width:100%;height:500px;" class="solid"></div>
	        <!-- <div id="crops" style="width:100%;" ></div> -->
	        
	        <script>
	        	var markers = [];
	        	var prev_infowindow = false;
	            function myMap() {
	            	var screenElement = document.getElementById("screen");
	            	if(!(screenElement.style.opacity == "0.40" 
            				|| screenElement.style.filter == "alpha(opacity=40)")) {
	            		
		                var mapCanvas = document.getElementById("map");
		                var myCenter=new google.maps.LatLng(0,19);
		                var mapOptions = {center: myCenter, 
		                					zoom: 3,
		                					panControl: false,
		                				    zoomControl: true,
		                				    mapTypeControl: false,
		                				    scaleControl: false,
		                				    streetViewControl: false,
		                				    overviewMapControl: false,
		                				    rotateControl: false
		                };
		                var map = new google.maps.Map(mapCanvas, mapOptions);
		                google.maps.event.addListener(map, 'click', function(event) {
		                                              placeMarker(map, event.latLng);
		                                              });
	            	}
	            }
	            
	        	function placeMarker(map, location) {
	        		var screenElement = document.getElementById("screen");
	        		if(!(screenElement.style.opacity == 0.40 
        				|| screenElement.style.filter == "alpha(opacity=40)")) {
	            	
		            	var marker = new google.maps.Marker({
							position: location,
							map: map
						});
		            	
		            	if(markers.length > 0) {
		            		for (var i = 0; i < markers.length; i++) {
		   						markers[i].setMap(null);
		   					}
		            	}
		            	markers = [];
		            	markers.push(marker);
		             
		             	var xmlhttp;
		             	var url="/InteractiveMap/ProcessLocationInformation?latitude="+location.lat()+"&longitude="+location.lng();
		             	if (window.XMLHttpRequest)
		             	{
		                 	xmlhttp=new XMLHttpRequest();
		             	}
		             	else
		             	{
		                 	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		             	}
		             	xmlhttp.onreadystatechange=function()
		             	{
		                 	if (xmlhttp.readyState==4 && xmlhttp.status==200)
		                 	{
								if( prev_infowindow ) {
									prev_infowindow.close();
								}
		                 		
		                     	var infowindow = new google.maps.InfoWindow({
		         					content: xmlhttp.responseText
		         					});
		                     	prev_infowindow = infowindow;
		         				infowindow.open(map,marker);
		         				
		         				//var cropElement = document.getElementById("crops");
		                		//cropElement.innerHTML = 'Latitude: ' + location.lat() + '<br/>Longitude: ' + location.lng() + '<br/>' +  xmlhttp.responseText;
		                		
		    	             	screenElement.style.opacity = "1.0";
		    	             	screenElement.style.filter = "alpha(opacity=100)";
		                		
		                		map.setCenter(location);
								//map.setZoom(6);
		                 	}
		             	}
						
		             	screenElement.style.opacity = "0.40";
		             	screenElement.style.filter = "alpha(opacity=40)";
		             	
		             	xmlhttp.open("POST", url, true);
		             	xmlhttp.send();
		             
		             	/*
						var myCity = new google.maps.Circle({
										center: location,
										radius: 50000,
										strokeColor: "#0000FF",
										strokeOpacity: 0.8,
										strokeWeight: 2,
										fillColor: "#0000FF",
										fillOpacity: 0.4
										});
		             	myCity.setMap(map);
		             	*/
						}		
					}
	        	
        		function submitForm() {
        			document.getElementById('id01').style.display='block';
        			var cropResultsElement = document.getElementById("cropsResultsInnerDiv");
        			var calcium = document.getElementById("calcium").value;
        			var soc = document.getElementById("soc").value;
        			var phosphorus = document.getElementById("phosphorus").value;
        			var aluminium = document.getElementById("aluminium").value;
        			var sand = document.getElementById("sand").value;
        			var magnesium = document.getElementById("magnesium").value;
        			var pH = document.getElementById("pH").value;
        			
        			var xmlhttp;
	             	var url="/InteractiveMap/ProcessSoilInformation?calcium="+calcium+"&soc="+soc+"&phosphorus="+phosphorus+"&aluminium="+aluminium+"&sand="+sand+"&magnesium="+magnesium+"&pH="+pH;
	             	if (window.XMLHttpRequest)
	             	{
	                 	xmlhttp=new XMLHttpRequest();
	             	}
	             	else
	             	{
	                 	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	             	}
	             	xmlhttp.onreadystatechange=function()
	             	{
	                 	if (xmlhttp.readyState==4 && xmlhttp.status==200)
	                 	{
	                 		cropResultsElement.innerHTML = xmlhttp.responseText;
	                 	}
	             	}
	             	
	             	cropResultsElement.innerHTML = "";
	             	xmlhttp.open("POST", url, true);
	             	xmlhttp.send();
        		}
	        
	        		
	        </script>
	        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQjw_wKeYj5029yDcLWLodu2r9itijINg&amp;callback=myMap"></script>
	        
	        <div>
				<p style="padding: 10px;">
					Have your own values? Click on the "upload" button to provide your soil information.
					<button onclick="document.getElementById('id01').style.display='block'" class="w3-btn">Upload</button>
				</p>
			</div>
			<div id="id01" class="w3-modal">
	    		<div class="w3-modal-content w3-card-4">
					<header class="w3-container w3-teal">
		        		<span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn">&times;</span>
		        		<h2>Enter or upload property data</h2>
		      		</header>
		      		<div class="w3-container">
		        		<div>
							<p style="font-size:12px", "Lucida Sans Unicode", "Lucida Grande", sans-serif>
								<b>Upload your file to get crop details for your data.</b> 
							</p>
							<p style="font-size:12px">
								<input type="file" />
								<input type="submit" />
								<br/>
								<br/>
								OR
								<br/>
								<br/>
								<b><i>Enter values manually</b></i>
							</p> 
		 				</div>
						<div>
							<table>
								<tr>
									<th>Property Name</th>
									<th>Enter Value</th>
								</tr>
								<tr>
									<td>Calcium</td>
									<td><input id="calcium" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td>Soil Organic Carbon</td>
									<td><input id="soc" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td>Phosphorus</td>
									<td><input id="phosphorus" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td>Aluminium</td>
									<td><input id="aluminium" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td>Sand Percentage</td>
									<td><input id="sand" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td>Magnesium</td>
									<td><input id="magnesium" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td>pH value</td>
									<td><input id="pH" type="number" name="quantity"></td>
								</tr>
								<tr>
									<td><b> Click here to submit </b> </td>
									<td>
										<input type="submit" onclick="submitForm()"> 
									</td>
								</tr>
							</table>
						</div>
		      		</div>
		      		<div id="cropsResults">
					<br/>
					<style>
							.progress {
									height: 50%;
									width: 200px;
							}
							table {
								font-family: arial, sans-serif;
								border-collapse: collapse;
								text-align: center;
								width: 70%;
							}
							td, th {
								border: 1px solid #dddddd;
								text-align: center;
							}
								
							tr:nth-child(even) {
								height: 10%
								background-color: #dddddd;
							}
							table.ex1 {
								table-layout: fixed;
							}
						</style>
						<head>
						  <meta charset="utf-8">
						  <meta name="viewport" content="width=device-width, initial-scale=1">
						  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
						  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
						  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
						</head>
						<div id="cropsResultsInnerDiv">
							
						</div>
					</div>
					<footer class="w3-container w3-teal">
						<p>E-SOIL</p>
		      		</footer>
		      	</div>
	    	</div>
		</div>
		<div class="container">
			<footer>Copyright © E-SOIL</footer>
		</div>
		</div>
    </body>
</html>