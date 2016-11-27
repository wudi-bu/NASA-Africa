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
		<!-- 
		<object class="header" style="font-size:23px">
			<img src="images/esoil.png" alt="Farmer" style="width:120px;height:80px;" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<b style="background-color: green">
				<i> Concerned about what to grow on your land? Let us help you!</i>
			</b>
		</object>
		-->
	</head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<body background="images/c.jpg">
		<img src="images/final.PNG" alt="Farmer" style="width:100%;height:auto;" />
		
		<div id="screen" style="width:100%;height:100%; z-index: 99;">
	        <div id="map" style="width:100%;height:500px;" class="solid"></div>
	        <div id="crops" style="width:100%;" ></div>
	        
	        <script>
	        	var markers = [];
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
		                     	//var infowindow = new google.maps.InfoWindow({
		         				//	content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng() + '<br>Message: ' +  xmlhttp.responseText
		         				//	});
		         				//infowindow.open(map,marker);
		         				
		         				var cropElement = document.getElementById("crops");
		                		cropElement.innerHTML = 'Latitude: ' + location.lat() + '<br/>Longitude: ' + location.lng() + '<br/>' +  xmlhttp.responseText;
		                		
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
	        
	        		
	        </script>
	        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQjw_wKeYj5029yDcLWLodu2r9itijINg&amp;callback=myMap"></script>
	        
	        <div class="w3-container">
			<pre style="font-size:17px", "Lucida Sans Unicode", "Lucida Grande", sans-serif>
				Have your own values? Click on the "upload" button to provide your soil information.
				<button onclick="document.getElementById('id01').style.display='block'" class="w3-btn">Upload</button>
			</pre>
		</div>
		<div id="id01" class="w3-modal">
	    	<div class="w3-modal-content w3-card-4">
				<header class="w3-container w3-teal">
	        		<span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn">&times;</span>
	        		<h2>Enter or upload property data</h2>
	      		</header>
	      		<div class="w3-container">
	        		<form action="demo_form.asp">
						<pre style="font-size:18px", "Lucida Sans Unicode", "Lucida Grande", sans-serif><b> Do you have soil data for your location?
	 						Upload your file to get crop details for your data. </b> 
						</pre>
						<p style="font-size:15px">
						<input type="file"><input type="submit"> <br><br> OR <br><br> <b> <i>Enter values manually </b></i></p> 
	 				</form>
					<form action="demo_form.asp">
						<table>
							<tr>
								<th>Property Name</th>
								<th>Enter Value</th>
							</tr>
							<tr>
								<td>Calcium</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td>Soil Organic Carbon</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td>Phosphorus</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td>Aluminium</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td>Sand Percentage</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td>Magnesium</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td>pH value</td>
								<td><input type="number" name="quantity"></td>
							</tr>
							<tr>
								<td><b> Click here to submit </b> </td>
								<td>
									<input type="submit"> 
								</td>
							</tr>
						</table>
					</form>
	      		</div>
				<footer class="w3-container w3-teal">
					<p>E-SOIL</p>
	      		</footer>
	    	</div>
		</div>
		<div class="container">
			<footer>Copyright © E-SOIL</footer>
		</div>
		</div>
    </body>
</html>