<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <title>REDD+ Visualizador</title>        
        <link rel="stylesheet" href="http://dev.openlayers.org/releases/OpenLayers-2.13.1/theme/default/style.css" type="text/css">
        <link rel="stylesheet" href="http://dev.openlayers.org/releases/OpenLayers-2.13.1/examples/style.css" type="text/css">
        <link rel="stylesheet" href="css/main.css" type="text/css">
   
	</head>
    <body>
    
    
    <div id="statistics"></div>
    <div id="map"></div>
    
    <div id="text"> 
<input type="button" value="Borrar poligonos" id="bot_borrar_poligonos">	
		<div id="accordion">
			<div>
			<h3>Clases de cobertura de la tierra</h3>
			<div>
				<center>Año de la cobertura</center>
				<%@include file="clases_cobertura.jsp" %>
				<hr/>
				<strong>Estadísticas de cobertura</strong><br/><br/>
				<i><font size="-1">División administrativa</font></i>
				<a href="#" class="tooltip">
			    	<img src="images/help_icon.png" />
			    	<span>
			        	<strong>Escoger un area administrativa</strong><br />
			        	Puede escoger una provincia, canton o distrito y visualizar la
			        	cobertura dentro de las 15 categorias diferentes para esa division
			        	administrativa especifica.
			    	</span>
				</a>
				<%@include file="carto_canvas.jsp" %>
				<br/>
				<i><font size="-1">Area de conservación</font></i>
				<a href="#" class="tooltip">
			    	<img src="images/help_icon.png" />
			    	<span>
			        	<strong>Escoger un área de conservación</strong><br />
			        	Escoja un área de conservación.
			    	</span>
				</a>
				<%@include file="area_conservacion.jsp" %>
				<br/>
				<i><font size="-1">Área silvestre protegida</font></i>
				<a href="#" class="tooltip">
			    	<img src="images/help_icon.png" />
			    	<span>
			        	<strong>Escoger un área silvestre</strong><br />
			        	Escoja un área silvestre.
			    	</span>
				</a>
				<%@include file="area_silvestre.jsp" %>	
								
				<br/>	
				<i><font size="-1">Cuenca Hidrografica</font></i>
				<a href="#" class="tooltip">
			    	<img src="images/help_icon.png" />
			    	<span>
			        	<strong>Escoger una cuenca hidrografica</strong><br />
			        	Escoja una cuenca hidrografica.
			    	</span>
				</a>
				<%@include file="cuenca_hidrografica.jsp" %>
				<br/>							
				<i><font size="-1">Polígono definido por el usuario</font></i>
				<a href="#" class="tooltip">
			    	<img src="images/help_icon.png" />
			    	<span>
			        	<img src="images/polygon_selector.png" style="float:right;" />
			        	<strong>Escoger un poligono</strong><br />
			        	Para seleccionar un polígono, haga clic en el selector
			        	en la esquina superior derecha del mapa.<br><img src="images/polygon_selector.png">
			    	</span>
				</a>
				<%@include file="dynamic_canvas.jsp" %>
			</div>
		</div>			
			<div>
				<h3>Cambio cobertura boscosa</h3>
				<div>	
					<%@include file="forest_gain_loss.jsp" %>
				</div>
			</div>
		</div>
		
        <div id="loader"></div>
	</div>
            

            

            

        
	<div id="header">
		<div style="position:absolute; top:10px; right:20px">
			<img src="http://redd-mas.cr/images/logos/redd-logo2.png" width="33" height="40"><img src="http://redd-mas.cr/images/logos/embajada-noruega.png" width="120" height="40">
		</div>
		<div style="padding:0 0 0 120px">
			<span style="font-size: 25px">Redd+ Visualizador de Cobertura Boscosa en Costa Rica.</span><br>
            <span>Proyecto: Lecciones aprendidas y desarrollo de capacidades para aplicar iniciativas REDD+: la experiencia de Costa Rica</span>
        </div>
    </div>
    

    

    
	
  
    
	<!-- Google & Openlayers libraries -->
    <script src="http://maps.google.com/maps/api/js?v=3&amp;sensor=false"></script>
    <script src="http://openlayers.org/api/OpenLayers.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    
    <!-- Simple Modal -->
    <script type="text/javascript" src="javascript/vendor/jquery.simplemodal-1.4.4.js"></script>
    
    <!-- Google Charts -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart","table"]});
    </script>    
    <script type="text/javascript" src="javascript/initializeMap.js"></script>
    
	<script type="text/javascript" src="javascript/navigationEvents.js"></script>    
	
	 
    
    <!-- Accordion -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>    
    <script type="text/javascript">
    	$(function() {
    		$("#accordion > div").accordion({ header: "h3", collapsible: true, active: false, heightStyle: "content" });
		});

    </script> 
    <%@include file="jsp/area_silvestre/panel.jsp" %>   
    </body>
    
</html>
