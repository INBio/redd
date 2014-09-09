<script type="text/javascript">
	var banano = parseFloat('<%= request.getAttribute("stats.banano") %>');
	var bosque = parseFloat('<%= request.getAttribute("stats.bosque") %>');
	var cuerpoDeAgua = parseFloat('<%= request.getAttribute("stats.cuerpoDeAgua") %>');
	var herbazal = parseFloat('<%= request.getAttribute("stats.herbazal") %>');	
	var infraestructura = parseFloat('<%= request.getAttribute("stats.infraestructura") %>');
	var manglar = parseFloat('<%= request.getAttribute("stats.manglar") %>');
	var nubes = parseFloat('<%= request.getAttribute("stats.nubes") %>');
	var otrosCultivos = parseFloat('<%= request.getAttribute("stats.otrosCultivos") %>');
	var palmaAceitera = parseFloat('<%= request.getAttribute("stats.palmaAceitera") %>');
	var paramo = parseFloat('<%= request.getAttribute("stats.paramo") %>');
	var pina = parseFloat('<%= request.getAttribute("stats.pina") %>');
	var sabana = parseFloat('<%= request.getAttribute("stats.sabana") %>');
	var sombras = parseFloat('<%= request.getAttribute("stats.sombras") %>');
	var terrenoDescubierto = parseFloat('<%= request.getAttribute("stats.terrenoDescubierto") %>');
	var vegetacionAnegada = parseFloat('<%= request.getAttribute("stats.vegetacionAnegada") %>');
	
	function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Cobertura en km2'],
          ['Banano', banano],
          ['Bosque', bosque],
          ['Cuerpo de Agua', cuerpoDeAgua],
          ['Herbazal', herbazal],
          ['Infraestructura', infraestructura],
          ['Manglar', manglar],
          ['Nubes', nubes],
          ['Otros Cultivos', otrosCultivos],
          ['Palma Aceitera', palmaAceitera],
          ['Paramo', paramo],
          ['Pina', pina],
          ['Sabana', sabana],
          ['Sombras', sombras],
          ['Terreno Descubierto', terrenoDescubierto],
          ['Vegetacion Anegada', vegetacionAnegada]
        ]);	
        var options = {
          title: 'Cobertura'
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
	}
	
	function drawTable() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Categoria');
		data.addColumn('number', 'Km2');
		data.addRows([
          ['Banano', banano],
          ['Bosque', bosque],
          ['Cuerpo de Agua', cuerpoDeAgua],
          ['Herbazal', herbazal],
          ['Infraestructura', infraestructura],
          ['Manglar', manglar],
          ['Nubes', nubes],
          ['Otros Cultivos', otrosCultivos],
          ['Palma Aceitera', palmaAceitera],
          ['Paramo', paramo],
          ['Pina', pina],
          ['Sabana', sabana],
          ['Sombras', sombras],
          ['Terreno Descubierto', terrenoDescubierto],
          ['Vegetacion Anegada', vegetacionAnegada]
		]);

		var table = new google.visualization.Table(document.getElementById('table_div'));
		table.draw(data, {showRowNumber: true, sortColumn: 1, sortAscending: false});
	}	

</script>

<table border="1" width="600"><tr><td>
<div id="table_div" style="width: 350px; height: 500px;"></div>
</td><td>
<div id="piechart" style="width: 900px; height: 500px;"></div>
</td></tr></table>