
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
    <script src="http://maps.google.com/maps/api/js?v=3&amp;sensor=false"></script>
    <script src="http://openlayers.org/api/OpenLayers.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- 
	<script type="text/javascript" src="http://svn.osgeo.org/metacrs/proj4js/trunk/lib/proj4js.js"></script>
	<script type="text/javascript" src="http://spatialreference.org/ref/epsg/31467/proj4js/"></script>
	<script type="text/javascript" src="http://dev.openlayers.org/releases/OpenLayers-2.13.1/examples/using-proj4js.js"></script>
	
	
	
	
	
	<!-- Needed for google Charts -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart","table"]});
    </script>	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    <script type="text/javascript">
    
		var firstProjection = 'PROJCS["WGS_1984_UTM_Zone_17N",GEOGCS["GCS_WGS_1984",DATUM["D_WGS_1984",SPHEROID["WGS_1984",6378137,298.257223563]],PRIMEM["Greenwich",0],UNIT["Degree",0.017453292519943295]],PROJECTION["Transverse_Mercator"],PARAMETER["latitude_of_origin",0],PARAMETER["central_meridian",-81],PARAMETER["scale_factor",0.9996],PARAMETER["false_easting",500000],PARAMETER["false_northing",0],UNIT["Meter",1]]';
		var secondProjection = "+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0 +k=1.0 +units=m +nadgrids=@null +wktext  +no_defs";
    
    
        var map, drawControls,  geojson, vectors;
        
        OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';
        function init(){
        	
        	geojson = new OpenLayers.Format.GeoJSON();
        
            map = new OpenLayers.Map('map', {
		        projection: 'EPSG:3857',
		        layers: [
		            new OpenLayers.Layer.Google(
		                "Google Physical",
		                {type: google.maps.MapTypeId.TERRAIN}
		            ),
		            new OpenLayers.Layer.Google(
		                "Google Streets", // the default
		                {numZoomLevels: 20}
		            ),
		            new OpenLayers.Layer.Google(
		                "Google Hybrid",
		                {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 20}
		            ),
		            new OpenLayers.Layer.Google(
		                "Google Satellite",
		                {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22}
		            )
		        ], 
		        center: new OpenLayers.LonLat(-84.0, 9.7)
		            // Google.v3 uses web mercator as projection, so we have to
		            // transform our coordinates
		            .transform('EPSG:4326', 'EPSG:3857'),
		        zoom: 7		
    		});                  
            
            // allow testing of specific renderers via "?renderer=Canvas", etc
            var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
            renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

			var Banano = new OpenLayers.Layer.WMS("Banano",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Banano', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  
			
			var Bosque = new OpenLayers.Layer.WMS("Bosque",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Bosque', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  	
			
			var CuerpoDeAgua = new OpenLayers.Layer.WMS("CuerpoDeAgua",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_CuerpoDeAgua', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  		
			
			var Herbazal = new OpenLayers.Layer.WMS("Herbazal",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Herbazal', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  		
			
			var Infraestructura = new OpenLayers.Layer.WMS("Infraestructura",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Infraestructura', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  		
			
			var Manglar = new OpenLayers.Layer.WMS("Manglar",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Manglar', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  		
			
			var Nubes = new OpenLayers.Layer.WMS("Nubes",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Nubes', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);  	
			
			var OtrosCultivos = new OpenLayers.Layer.WMS("OtrosCultivos",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_OtrosCultivos', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);		
			
			var PalmaAceitera = new OpenLayers.Layer.WMS("PalmaAceitera",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_PalmaAceitera', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);			
			
			var Paramo = new OpenLayers.Layer.WMS("Paramo",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Paramo', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);			
			
			var Pina = new OpenLayers.Layer.WMS("Pina",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Pina', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);		
			
			var Sabana = new OpenLayers.Layer.WMS("Sabana",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Sabana', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);			
			
			var Sombras = new OpenLayers.Layer.WMS("Sombras",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_Sombras', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);	
			
			var TerrenoDescubierto = new OpenLayers.Layer.WMS("TerrenoDescubierto",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_TerrenoDescubierto', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);		
			
			var VegetacionAnegada = new OpenLayers.Layer.WMS("VegetacionAnegada",
				"http://localhost:8090/geoserver/redd/wms", 
				{'layers': 'redd:V2_VegetacionAnegada', transparent: true, format: 'image/gif'},
				{isBaseLayer: false}
			);																					
			
																	
		
			Banano.setVisibility(false);
			Bosque.setVisibility(false); 
			CuerpoDeAgua.setVisibility(false); 
			Herbazal.setVisibility(false); 
			Infraestructura.setVisibility(false); 
			Manglar.setVisibility(false); 
			Nubes.setVisibility(false); 
			OtrosCultivos.setVisibility(false); 
			PalmaAceitera.setVisibility(false); 
			Paramo.setVisibility(false); 
			Pina.setVisibility(false); 
			Sabana.setVisibility(false); 
			Sombras.setVisibility(false); 
			TerrenoDescubierto.setVisibility(false); 
			VegetacionAnegada.setVisibility(false); 
			
			map.addLayer(Banano);  
			map.addLayer(Bosque);   
			map.addLayer(CuerpoDeAgua);
			map.addLayer(Herbazal);
			map.addLayer(Infraestructura);
			map.addLayer(Manglar);  
			map.addLayer(Nubes);    
			map.addLayer(OtrosCultivos);  
			map.addLayer(PalmaAceitera);    
			map.addLayer(Paramo);  	
			map.addLayer(Pina);  	
			map.addLayer(Sabana);  	
			map.addLayer(Sombras);  	
			map.addLayer(TerrenoDescubierto);  	
			map.addLayer(VegetacionAnegada); 		
			
	
	
	
	
			// Disable dragging
			//
			//for (var i = 0; i< map.controls.length; i++) {
			//    if (map.controls[i].displayClass == 
			//                            "olControlNavigation") {
			//        map.controls[i].deactivate();
			//    }
			//}
	
	
	
	
	
			// la capa para escoger el pol’gono

            vectors = new OpenLayers.Layer.Vector("Vector Layer", {
                renderers: renderer
            });
            vectors.events.on({
                'featureselected': function(feature) {
                    document.getElementById('counter').innerHTML = this.selectedFeatures.length;
                },
                'featureunselected': function(feature) {
                    document.getElementById('counter').innerHTML = this.selectedFeatures.length;
                }
            });

            map.addLayer(vectors);
            

            
            map.addControl(new OpenLayers.Control.LayerSwitcher());
            
            drawControls = {          
                polygon: new OpenLayers.Control.DrawFeature(
                    vectors, OpenLayers.Handler.Polygon
                ),

            };
            
            for(var key in drawControls) {
                map.addControl(drawControls[key]);
            }

        }

        function toggleControl(element) {
            for(key in drawControls) {
                var control = drawControls[key];
                if(element.value == key && element.checked) {
                    control.activate();
                } else {
                    control.deactivate();
                }
            }
        }

        function update() {
            var clickout = document.getElementById("clickout").checked;
            if(clickout != drawControls.select.clickout) {
                drawControls.select.clickout = clickout;
            }

            var box = document.getElementById("box").checked;
            if(box != drawControls.select.box) {
                drawControls.select.box = box;
                if(drawControls.select.active) {
                    drawControls.select.deactivate();
                    drawControls.select.activate();
                }
            }
        }

		$(document).ready(function () {
			$("#refresh").click(function(){
			    $( "#statistics" ).html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
				var str = geojson.write(vectors.features, true);
				console.log(  str, "Logged!");				
				var data = $.parseJSON(str);
				var sss = geojson.parseGeometry(data.features[0].geometry);
				var urlCompleta = "grab?geojson=" + sss;
				$.get( urlCompleta, function( data ) {
				console.log(data);	
				$( "#statistics" ).html(data);
				drawChart();
				drawTable();
				});
			});
			$("#comparar").click(function(){
			    $( "#statistics" ).html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
				var str = geojson.write(vectors.features, true);
				console.log(  str, "Logged!");				
				var data = $.parseJSON(str);
				var sss = geojson.parseGeometry(data.features[0].geometry);
				var urlCompleta = "compare?geojson=" + sss;
				$.get( urlCompleta, function( data ) {
				console.log(data);	
				$( "#statistics" ).html(data);
				drawChart();
				});
			});			
		});
		

        
    </script>
  </head>
  <body onload="init()">
  [<a href="index">Home</a>] [<a href="comparacion">Comparacion Categorias</a>]
  
    <h1 id="title">Redd+ Visualizador</h1>      

    <div id="map" class="smallmap"></div>
    
    <div id="statistics">estadisticas</div>
    
    
    <ul id="controlToggle">    
        <li>
            <input type="radio" name="type" value="polygon" id="polygonToggle"
                   onclick="toggleControl(this);" />
            <label for="polygonToggle">dibujar poligono</label>
        </li>
    </ul>
    <input type="button" value="Cargar estadisticas" id="refresh">
    
    <p/><br/><p/><br/><p/>
    
    <select id="select1">
		<option value="1986">1986</option>
		<option value="2000">2000</option>
		<option value="2010">2010</option>
	</select>
    <select id="select2">
		<option value="1986">1986</option>
		<option value="2000">2000</option>
		<option value="2010">2010</option>
	</select>
    <input type="button" value="Comparar anos" id="comparar">
    
    
  </body>
</html>
