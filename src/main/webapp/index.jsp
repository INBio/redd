
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
	<script type="text/javascript" src="javascript/initializeMap.js"></script>
	<script type="text/javascript" src="javascript/mapEvents.js"></script>

  </head>
  <body onload="init()">
  [<a href="index">Home</a>] [<a href="comparacion">Comparacion Categorias</a>]
  
    <h1 id="title">Redd+ Visualizador (VERSION EN DESARROLLO!)</h1>      



            <input type="radio" name="type" value="polygon" id="polygonToggle"
                   onclick="toggleControl(this);"/>
            <label for="polygonToggle">dibujar poligono</label>


    <div id="map" class="smallmap"></div>
    
    <div id="statistics"></div>
    

    <p/><br/>
    <table width="712"><tr><td width="237">
        <center>
    <select id="select1" class="styled-select">
		<option value="1986">1986</option>
		<option value="2000">2000</option>
		<option value="2010">2010</option>
	</select>
    <br/><p/><br/>
    <input type="button" value="Cargar estad&#237;sticas" id="bot_statistics">
    
    </td><td width="237">
    
    <center>
    <select id="select1" class="styled-select">
		<option value="1986">1986</option>
		<option value="2000">2000</option>
		<option value="2010">2010</option>
	</select>
		
	
	
    <select id="select2" class="styled-select">
		<option value="1986">1986</option>
		<option value="2000" selected>2000</option>
		<option value="2010">2010</option>
	</select>
	
	<br/><p/><br/>
    <input type="button" value="Comparar a&#241;os" id="bot_compare">
    </center>
    </td><td width="237">
    <center>
    <select id="select_distritos" class="styled-select">
		<option value="1986">Acapulco</option>
		<option value="2000">Aguabuena</option>
		<option value="2010">Aguacaliente</option>
	</select>   
	<br/><p/><br/>
	<input type="button" value="Ver Info Distrito" id="bot_distrito"> 
	</center>
    </td></tr></table>
  </body>
</html>
