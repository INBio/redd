	$(document).ready(function() {
    	
        $("#select_cobertura_provincia").change(function() {
            var provinciaId = $("#select_cobertura_provincia").val();
            $("#cantones").html('<img src="images/ajax_loader.gif">');
            var urlCompleta = "selectCantones?provinciaId=" + provinciaId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#cantones").html(data);
            });
        });
        $(document.body).on('change', '#select_cobertura_canton' , function() {
            var cantonId = $("#select_cobertura_canton").val();
            $("#distritos").html('<img src="images/ajax_loader.gif">');
            var urlCompleta = "selectDistritos?cantonId=" + cantonId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#distritos").html(data);
            });
        });
        $(document.body).on('change', '#select_cobertura_distrito' , function() {
            var cantonId = $("#select_cobertura_distrito").val();
            var urlCompleta = "displayPolygon?polygonId=" + cantonId;
            $.get(urlCompleta, function(data) {
            	vectors.removeAllFeatures();
                var wkt = new OpenLayers.Format.WKT();
                var features = wkt.read(data);
                
                // zooming to the selected distrito
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
                vectors.addFeatures(features);
                map.zoomToExtent(bounds);
                }
            });
        });        
        $("#bot_cobertura_distrito").click(function() {
            var distritoId = $("#select_cobertura_distrito").val();
            var coverageYearId = $("#select_land_cover_distrito").val();
            var urlCompleta = "grabStats?polygonId=" + distritoId + "&coverageId=" + coverageYearId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
                drawTable();
            });
        });       
        $("#bot_perdida_boscosa").click(function() {
        	var layerStr = 'BosqueLoss_';
            var firstYear = $("#select_land_cover_ganancia_perdida_first :selected").text();
            var secondYear = $("#select_land_cover_ganancia_perdida_second :selected").text();
            layerStr = layerStr.concat(firstYear).concat("_").concat(secondYear);
            arr_gainLoss['BosqueLoss_1986_2000'].setVisibility(false);
            arr_gainLoss['BosqueLoss_1986_2011'].setVisibility(false);
            arr_gainLoss['BosqueGain_1986_2000'].setVisibility(false);
            arr_gainLoss['BosqueGain_1986_2011'].setVisibility(false);
            arr_gainLoss[layerStr].setVisibility(true);
        });      
        $("#bot_ganancia_boscosa").click(function() {
        	var layerStr = 'BosqueGain_';
            var firstYear = $("#select_land_cover_ganancia_perdida_first :selected").text();
            var secondYear = $("#select_land_cover_ganancia_perdida_second :selected").text();
            layerStr = layerStr + firstYear + "_" + secondYear;
            arr_gainLoss['BosqueLoss_1986_2000'].setVisibility(false);
            arr_gainLoss['BosqueLoss_1986_2011'].setVisibility(false);
            arr_gainLoss['BosqueGain_1986_2000'].setVisibility(false);
            arr_gainLoss['BosqueGain_1986_2011'].setVisibility(false);
            arr_gainLoss[layerStr].setVisibility(true);            
        });      
    });