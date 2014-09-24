
    $(document).ready(function() {
    	// 
        $("#bot_statistics").click(function() {
            $("#statistics").html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
            var coverageId = $("#select_cobertura_dinamico").val();
            var str = geojson.write(vectors.features, true);
            console.log(str, "Logged!");
            var data = $.parseJSON(str);
            var polygonJson = geojson.parseGeometry(data.features[0].geometry);
            var urlCompleta = "grab?geojson=" + polygonJson + "&coverageId=" + coverageId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
                drawTable();
            });
        });
        $("#bot_compare").click(function() {
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
                $("#statistics").html(data);
                drawChart();
            });
        });
    });
