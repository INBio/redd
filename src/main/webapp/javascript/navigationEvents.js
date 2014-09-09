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
            var cantondId = $("#select_cobertura_canton").val();
            $("#distritos").html('<img src="images/ajax_loader.gif">');
            var urlCompleta = "selectDistritos?cantonId=" + cantondId;
            $.get(urlCompleta, function(data) {
                console.log(data);
                $("#distritos").html(data);
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
    });