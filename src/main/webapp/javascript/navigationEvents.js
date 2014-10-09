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
            $.get(urlCompleta, function(data) {
            	vectors.removeAllFeatures();
                var wkt = new OpenLayers.Format.WKT();
                var features = wkt.read(data);
                
                // zooming to the selected distrito
                var bounds = returnBounds(features);
                
                map.zoomToExtent(bounds);
                map.zoomOut();
                map.moveByPx(-300,0);
                vectors.addFeatures(features);
            });             
            
            urlCompleta = "selectCantones?provinciaId=" + provinciaId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#cantones").html(data);
            });
        });
        $(document.body).on('change', '#select_cobertura_canton' , function() {
            var cantonId = $("#select_cobertura_canton").val();
            $("#distritos").html('<img src="images/ajax_loader.gif">');
            
            var urlCompleta = "displayPolygon?polygonId=" + cantonId;
            $.get(urlCompleta, function(data) {
            	vectors.removeAllFeatures();
                var wkt = new OpenLayers.Format.WKT();
                var features = wkt.read(data);
                
                // zooming to the selected distrito
                var bounds = returnBounds(features);
                
                map.zoomToExtent(bounds);
                map.zoomOut();
                map.moveByPx(-300,0);
                vectors.addFeatures(features);
            });            
            
            urlCompleta = "selectDistritos?cantonId=" + cantonId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#distritos").html(data);
            });
          
        });
        $(document.body).on('change', '#select_cobertura_distrito' , function() {
            var distritoId = $("#select_cobertura_distrito").val();
            var urlCompleta = "displayPolygon?polygonId=" + distritoId;
            $.get(urlCompleta, function(data) {
            	vectors.removeAllFeatures();
                var wkt = new OpenLayers.Format.WKT();
                var features = wkt.read(data);
                
                // zooming to the selected distrito
                var bounds = returnBounds(features);
                
                map.zoomToExtent(bounds);
                map.zoomOut();
                map.moveByPx(-300,0);
                vectors.addFeatures(features);
            });
        });        
        $("#bot_cobertura_distrito").click(function() {
            var distritoId = $("#select_cobertura_distrito").val();
            var coverageYearId = $("#select_land_cover_distrito").val();
            var urlCompleta = "grabStats?polygonId=" + distritoId + "&coverageId=" + coverageYearId;
            $("#statistics").html("<img src='images/ajax_loader.gif'>");
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
                drawTable();   
                $("#statistics").modal({
                		
                		
	                onClose: function (dialog) {
	                	dialog.data.fadeOut('slow', function () {
	                		dialog.container.slideUp('slow', function () {
	                			dialog.overlay.fadeOut('slow', function () {
	                				$.modal.close(); // must call this!
	                			});
	                		});
	                	});
	                },
	                onOpen: function (dialog) {
	                	dialog.overlay.fadeIn('slow', function () {
	                		dialog.container.slideDown('slow', function () {
	                			dialog.data.fadeIn('slow');
	                		});
	                	});
	                }
                });
            });
        });     
        $("#bot_cobertura_canton").click(function() {
            var cantonId = $("#select_cobertura_canton").val();
            var coverageYearId = $("#select_land_cover_canton").val();
            var urlCompleta = "grabStats?polygonId=" + cantonId + "&coverageId=" + coverageYearId;
            $("#statistics").html("<img src='images/ajax_loader.gif'>");
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
                drawTable();   
                $("#statistics").modal({
                		
                		
	                onClose: function (dialog) {
	                	dialog.data.fadeOut('slow', function () {
	                		dialog.container.slideUp('slow', function () {
	                			dialog.overlay.fadeOut('slow', function () {
	                				$.modal.close(); // must call this!
	                			});
	                		});
	                	});
	                },
	                onOpen: function (dialog) {
	                	dialog.overlay.fadeIn('slow', function () {
	                		dialog.container.slideDown('slow', function () {
	                			dialog.data.fadeIn('slow');
	                		});
	                	});
	                }
                });
            });
        });        
        $("#bot_cobertura_provincia").click(function() {
            var provinciaId = $("#select_cobertura_provincia").val();
            var coverageYearId = $("#select_land_cover_provincia").val();
            var urlCompleta = "grabStats?polygonId=" + provinciaId + "&coverageId=" + coverageYearId;
            $("#statistics").html("<img src='images/ajax_loader.gif'>");
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
                drawTable();   
                $("#statistics").modal({
                		
                		
	                onClose: function (dialog) {
	                	dialog.data.fadeOut('slow', function () {
	                		dialog.container.slideUp('slow', function () {
	                			dialog.overlay.fadeOut('slow', function () {
	                				$.modal.close(); // must call this!
	                			});
	                		});
	                	});
	                },
	                onOpen: function (dialog) {
	                	dialog.overlay.fadeIn('slow', function () {
	                		dialog.container.slideDown('slow', function () {
	                			dialog.data.fadeIn('slow');
	                		});
	                	});
	                }
                });
            });
        });       
        

        $("#bot_perdida_ganancia_boscosa").click(function() {
        	var type = $("#select_land_cover_ganancia_perdida :selected").val();
        	
        	var layerBosqueStr = 'Bosque_';
        	var layerNoBosque = 'No_Bosque_'; 
        	var layerLossStr = 'BosqueLoss_';
        	var layerGainStr = 'BosqueGain_';
            var firstYear = $("#select_land_cover_ganancia_perdida_first :selected").text();
            var secondYear = $("#select_land_cover_ganancia_perdida_second :selected").text();
            
            var layerLandCover = 'LandCover_';
            
            if(type==1) {
            	layerBosqueStr = layerBosqueStr.concat(secondYear);
            	layerNoBosque = layerNoBosque.concat(secondYear);
            	layerLossStr = layerLossStr.concat(firstYear).concat("_").concat(secondYear);
            	layerGainStr = layerGainStr.concat(firstYear).concat("_").concat(secondYear);
            	arr_gainLoss[layerBosqueStr].setVisibility(true);
            	arr_gainLoss[layerNoBosque].setVisibility(true);
            	arr_gainLoss[layerLossStr].setVisibility(true);
            	arr_gainLoss[layerGainStr].setVisibility(true);
            }
            else if(type==2) {
            	layerLandCover = layerLandCover.concat(secondYear);
            	layerLossStr = layerLossStr.concat(firstYear).concat("_").concat(secondYear);
            	arr_deforestation[layerLandCover].setVisibility(true);
            	arr_deforestation[layerLossStr].setVisibility(true);
            }
            else if(type==3) {
            	layerLandCover = layerLandCover.concat(secondYear);
            	layerGainStr = layerGainStr.concat(firstYear).concat("_").concat(secondYear);
            	arr_forestation[layerLandCover].setVisibility(true);
            	arr_forestation[layerGainStr].setVisibility(true);
            }             
        });   
        
        $(document).ready(function() {
            $("[id=chbox_land_cover]").click(function(event) {
            	var categoriaId = event.target.value;
            	var categoriaStr = 'category_id:' + categoriaId;
            	
            	if(arrayCategorias[categoriaId]==null) {
            	arrayCategorias[categoriaId] = new OpenLayers.Layer.WMS("Bosque 1986",
                        "http://172.16.16.82:8080/geoserver/redd/wms", {
                            'layers': 'redd:land_cover_category_1986',
                            transparent: true,
                            'styles':categoriaId,
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
            		
            		
	                onClose: function (dialog) {
	                	dialog.data.fadeOut('slow', function () {
	                		dialog.container.slideUp('slow', function () {
	                			dialog.overlay.fadeOut('slow', function () {
	                				$.modal.close(); // must call this!
	                			});
	                		});
	                	});
	                },
	                onOpen: function (dialog) {
	                	dialog.overlay.fadeIn('slow', function () {
	                		dialog.container.slideDown('slow', function () {
	                			dialog.data.fadeIn('slow');
	                		});
	                	});
	                }
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
            		
            		
	                onClose: function (dialog) {
	                	dialog.data.fadeOut('slow', function () {
	                		dialog.container.slideUp('slow', function () {
	                			dialog.overlay.fadeOut('slow', function () {
	                				$.modal.close(); // must call this!
	                			});
	                		});
	                	});
	                },
	                onOpen: function (dialog) {
	                	dialog.overlay.fadeIn('slow', function () {
	                		dialog.container.slideDown('slow', function () {
	                			dialog.data.fadeIn('slow');
	                		});
	                	});
	                }
                });                
            });
        });        
        
        
    });