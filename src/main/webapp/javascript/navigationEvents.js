function returnBounds(features) {
	var bounds;
	if(features) {
		if(features.constructor != Array) {
			features = [features];
		}
		for(var i=0; i<features.length; ++i) {
			if (!bounds) {
				bounds = features[i].geometry.getBounds();
			} else {
				bounds.extend(features[i].geometry.getBounds());
			}
		}
	}
    return bounds;
}


	$(document).ready(function() {
    	
        $("#select_cobertura_provincia").change(function() {
            var provinciaId = $("#select_cobertura_provincia").val();
            $("#cantones").html('<img src="images/ajax_loader.gif">');
            
            var urlCompleta = "displayPolygon?polygonId=" + provinciaId;
            $.get(urlCompleta, drawGeographicPolygon);        
            
            urlCompleta = "selectCantones?id=" + provinciaId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#cantones").html(data);
            });
        });
        $(document.body).on('change', '#select_cobertura_canton' , function() {
            var cantonId = $("#select_cobertura_canton").val();
            $("#distritos").html('<img src="images/ajax_loader.gif">');
            
            var urlCompleta = "displayPolygon?polygonId=" + cantonId;
            $.get(urlCompleta, drawGeographicPolygon);         
            
            urlCompleta = "selectDistritos?id=" + cantonId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#distritos").html(data);
            });
          
        });
        $(document.body).on('change', '#select_cobertura_distrito' , function() {
            var distritoId = $("#select_cobertura_distrito").val();
            var urlCompleta = "displayPolygon?polygonId=" + distritoId;
            $.get(urlCompleta, drawGeographicPolygon);
        });    
        
        function drawGeographicPolygon(data) {
        	vectors.removeAllFeatures();
            var wkt = new OpenLayers.Format.WKT();
            var features = wkt.read(data);
            
            // zooming to the selected distrito
            var bounds = returnBounds(features);
            
            map.zoomToExtent(bounds);
            map.zoomOut();
            map.moveByPx(-300,0);
            vectors.addFeatures(features);
        }
        
        function closeDialog(dialog) {
        	dialog.data.fadeOut('slow', function () {
        		dialog.container.slideUp('slow', function () {
        			dialog.overlay.fadeOut('slow', function () {
        				$.modal.close(); // must call this!
        			});
        		});
        	});
        }
        
        function openDialog(dialog) {
        	dialog.overlay.fadeIn('slow', function () {
        		dialog.container.slideDown('slow', function () {
        			dialog.data.fadeIn('slow');
        		});
        	});
        }   
        
        function coberturaClick(data) {
            console.log(data);
            $("#statistics").html(data);
            drawChart();
            drawTable();   
            $("#statistics").modal({
                onClose: closeDialog,
                onOpen: openDialog
            });
        }
        
        $("#bot_cobertura_distrito").click(function() {
            var distritoId = $("#select_cobertura_distrito").val();
            var coverageYearId = $("#select_land_cover_distrito").val();
            var urlCompleta = "grabStats?polygonId=" + distritoId + "&coverageId=" + coverageYearId;
            $("#statistics").html("<img src='images/ajax_loader.gif'>");
            $.get(urlCompleta, coberturaClick);
        });     
        $("#bot_cobertura_canton").click(function() {
            var cantonId = $("#select_cobertura_canton").val();
            var coverageYearId = $("#select_land_cover_canton").val();
            var urlCompleta = "grabStats?polygonId=" + cantonId + "&coverageId=" + coverageYearId;
            $("#statistics").html("<img src='images/ajax_loader.gif'>");
            $.get(urlCompleta, coberturaClick);
        });        
        $("#bot_cobertura_provincia").click(function() {
            var provinciaId = $("#select_cobertura_provincia").val();
            var coverageYearId = $("#select_land_cover_provincia").val();
            var urlCompleta = "grabStats?polygonId=" + provinciaId + "&coverageId=" + coverageYearId;
            $("#statistics").html("<img src='images/ajax_loader.gif'>");
            $.get(urlCompleta, coberturaClick);
        });       
        

        function removeVisibilityGainLoss() {
        	No_Bosque_2000.setVisibility(false);
        	Bosque_1986.setVisibility(false);
        	Bosque_2000.setVisibility(false);
        	Bosque_2011.setVisibility(false);
        	BosqueLoss_1986_2000.setVisibility(false);
        	BosqueGain_1986_2000.setVisibility(false);
     	   
        }
        
        $("#bot_perdida_ganancia_boscosa").click(function() {
        	var type = $("#select_land_cover_ganancia_perdida :selected").val();
        	
        	var layerBosqueStr = 'Bosque_';
        	var layerNoBosque = 'No_Bosque_'; 
        	var layerLossStr = 'BosqueLoss_';
        	var layerGainStr = 'BosqueGain_';
            var firstYear = $("#select_land_cover_ganancia_perdida_first :selected").text();
            var secondYear = $("#select_land_cover_ganancia_perdida_second :selected").text();
            
            var layerLandCover = 'LandCover_';
            
        	layerLossStr = layerLossStr.concat(firstYear).concat("_").concat(secondYear);
        	layerGainStr = layerGainStr.concat(firstYear).concat("_").concat(secondYear);   
        	layerBosqueStr = layerBosqueStr.concat(secondYear);
        	layerNoBosque = layerNoBosque.concat(secondYear);
        	layerLandCover = layerLandCover.concat(secondYear);
            
            if(type==1) {
            	removeVisibilityGainLoss();
            	arr_gainLoss[layerBosqueStr].setVisibility(true);
            	arr_gainLoss[layerNoBosque].setVisibility(true);
            	arr_gainLoss[layerLossStr].setVisibility(true);
            	arr_gainLoss[layerGainStr].setVisibility(true);
            }
            else if(type==2) {
            	removeVisibilityGainLoss();
            	arr_deforestation[layerLandCover].setVisibility(true);
            	arr_deforestation[layerLossStr].setVisibility(true);
            }
            else if(type==3) {
            	removeVisibilityGainLoss();
            	arr_forestation[layerLandCover].setVisibility(true);
            	arr_forestation[layerGainStr].setVisibility(true);
            }             
        });   
        
        $(document).ready(function() {
            $("[id=chbox_land_cover]").click(function(event) {
            	var categoriaStr = 'category_id:' + event.target.value;
            	var yearCategoria = $("#select_land_cover_clases_cobertura :selected").text();
            	var categoriaId = event.target.value + '_' + yearCategoria;
            	var yearCategoriaStr = 'redd:land_cover_category_' + yearCategoria;
            	var style = event.target.value;
            	
            	if(arrayCategorias[categoriaId]==null) {
            	arrayCategorias[categoriaId] = new OpenLayers.Layer.WMS("Bosque 1986",
                        "http://172.16.16.82:8080/geoserver/redd/wms", {
                            'layers': yearCategoriaStr,
                            transparent: true,
                            'styles':style,
                            viewparams:categoriaStr,
                            format: 'image/png'
                        }, {
                            isBaseLayer: false
                        }
                    );            	
            	}
            	if( this.checked ) {
            		arrayCategorias[categoriaId].setVisibility(true);
            		map.addLayer(arrayCategorias[categoriaId]);  
            		select_land_cover_clases_cobertura
            	}
            	else {
            		arrayCategorias[categoriaId].setVisibility(false);
            	}

            });
        });            
        

        
        $("#bot_statistics").click(function() {
            $("#loader").html("Cargando las estadisticas<br/>" + '<img src="images/ajax_loader.gif">');
            var coverageId = $("#select_cobertura_dinamico").val();
            var str = geojson.write(vectors.features, true);
            console.log(str, "Logged!");
            var data = $.parseJSON(str);
            var polygonJson = geojson.parseGeometry(data.features[0].geometry);
            var urlCompleta = "grab?geojson=" + polygonJson + "&coverageId=" + coverageId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#loader").empty();
                $("#statistics").html(data);
                drawChart();
                drawTable();
                $("#statistics").modal({
	                onClose: closeDialog,
	                onOpen: openDialog
                });
            });
        });
        $("#bot_compare").click(function() {
        	$("#loader").html("Cargando las estadisticas<br/>" + '<img src="images/ajax_loader.gif">');
            $("#statistics").html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
            var preCoverageId = $("#select_cobertura_dinamico_pre").val();
            var postCoverageId = $("#select_cobertura_dinamico_post").val();
            var str = geojson.write(vectors.features, true);
            console.log(str, "Logged!");
            var data = $.parseJSON(str);
            var polygonJson = geojson.parseGeometry(data.features[0].geometry);
            var urlCompleta = "compare?geojson=" + polygonJson + "&preCoverageId=" + preCoverageId + "&postCoverageId=" + postCoverageId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#loader").empty();
                $("#statistics").html(data);
                drawChart();
                $("#statistics").modal({
	                onClose: closeDialog,
	                onOpen: openDialog
                });              
            });
        });        
        
        
    });