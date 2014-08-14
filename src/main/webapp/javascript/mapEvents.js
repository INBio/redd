
    $(document).ready(function() {
    	// 
        $("#bot_statistics").click(function() {
            $("#statistics").html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
            var str = geojson.write(vectors.features, true);
            console.log(str, "Logged!");
            var data = $.parseJSON(str);
            var sss = geojson.parseGeometry(data.features[0].geometry);
            var urlCompleta = "grab?geojson=" + sss;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
                drawTable();
            });
        });
        $("#bot_compare").click(function() {
            $("#statistics").html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
            var str = geojson.write(vectors.features, true);
            console.log(str, "Logged!");
            var data = $.parseJSON(str);
            var sss = geojson.parseGeometry(data.features[0].geometry);
            var urlCompleta = "compare?geojson=" + sss;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
            });
        });
        $("#bot_distrito").click(function() {
            $("#statistics").html("Cargando las estadisticas" + '<img src="images/ajax_loader.gif">');
            var str = geojson.write(vectors.features, true);
            console.log(str, "Logged!");
            var data = $.parseJSON(str);
            var urlCompleta = "ver_distrito?distrito=" + "DANIEL FLORES";
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#statistics").html(data);
                drawChart();
            });
        });
    });
