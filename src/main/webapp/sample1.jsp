
<script type="text/javascript">
	var banano = parseInt('<%= request.getAttribute("stats1986.banano") %>');
	var bosque = parseInt('<%= request.getAttribute("stats1986.bosque") %>');
	var cuerpoDeAgua = parseInt('<%= request.getAttribute("stats1986.cuerpoDeAgua") %>');
	var herbazal = parseInt('<%= request.getAttribute("stats1986.herbazal") %>');	
	var infraestructura = parseInt('<%= request.getAttribute("stats1986.infraestructura") %>');
	var manglar = parseInt('<%= request.getAttribute("stats1986.manglar") %>');
	var nubes = parseInt('<%= request.getAttribute("stats1986.nubes") %>');
	var otrosCultivos = parseInt('<%= request.getAttribute("stats1986.otrosCultivos") %>');
	var palmaAceitera = parseInt('<%= request.getAttribute("stats1986.palmaAceitera") %>');
	var paramo = parseInt('<%= request.getAttribute("stats1986.paramo") %>');
	var pina = parseInt('<%= request.getAttribute("stats1986.pina") %>');
	var sabana = parseInt('<%= request.getAttribute("stats1986.sabana") %>');
	var sombras = parseInt('<%= request.getAttribute("stats1986.sombras") %>');
	var terrenoDescubierto = parseInt('<%= request.getAttribute("stats1986.terrenoDescubierto") %>');
	var vegetacionAnegada = parseInt('<%= request.getAttribute("stats1986.vegetacionAnegada") %>');
	
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