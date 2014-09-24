<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>SelectFeature Control on Layer.Vector</title> 
        <link rel="stylesheet" href="http://dev.openlayers.org/releases/OpenLayers-2.13.1/theme/default/style.css" type="text/css">
        <link rel="stylesheet" href="http://dev.openlayers.org/releases/OpenLayers-2.13.1/examples/style.css" type="text/css">
    <style type="text/css">
        #controlToggle li {
            list-style: none;
        }
	.smallmap {
	    width: 712px;
	    height: 456px;
	    border: 1px solid #ccc;
	}
</style>

	<!-- Google & Openlayers libraries -->
    <script src="http://maps.google.com/maps/api/js?v=3&amp;sensor=false"></script>
    <script src="http://openlayers.org/api/OpenLayers.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    
    <!-- Google Charts -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart","table"]});
    </script>    
	
	<!-- Custom libraries -->
    <script type="text/javascript">
    	  
    </script>  	
	<script type="text/javascript" src="javascript/initializeMap.js"></script>
	<script type="text/javascript" src="javascript/mapEvents.js"></script>
	<script type="text/javascript" src="javascript/navigationEvents.js"></script>

  </head>
  <body onload="init()">
  
  [<a href="index">Home</a>] [<a href="comparacion">Comparacion Categorias</a>]
  
    <h1 id="title">Redd+ Visualizador (VERSION EN DESARROLLO!)</h1>      

	<table><tr><td>
	<input type="radio" name="type" value="polygon" id="polygonToggle" onclick="toggleControl(this);"/>
	<label for="polygonToggle">dibujar poligono</label>
	<div id="map" class="smallmap"></div>
	</td><td width="10">
	</td><td>
	<strong>Ganancia / Perdida Boscosa</strong>
	<br/><p/><br/>
	<%@include file="forest_gain_loss.jsp" %>
	<hr/>	
	<strong>Escoja su division administrativa</strong>
	<br/><p/><br/>
	<%@include file="carto_canvas.jsp" %>
	<hr/>
	<strong>Trazado a mano</strong>
	<br/><p/><br/>
	<%@include file="dynamic_canvas.jsp" %>
	</td></tr></table>


    

	<p/><br/>
    
    
    <div id="statistics"></div>

	</center>

  </body>
</html>
