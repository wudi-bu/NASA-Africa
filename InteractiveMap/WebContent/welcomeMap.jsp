<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Agricultural Map of Africa</title>
	</head>
	<body>
		<div id="screen" style="width:100%;height:100%; z-index: 99;">
	        <div id="map" style="width:100%;height:500px;"></div>
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
		</div>
    </body>
</html>