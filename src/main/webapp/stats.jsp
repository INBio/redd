<script type="text/javascript">
	var banano = parseFloat(parseFloat('<%= request.getAttribute("stats.banano") %>').toFixed(2));
	var bosque = parseFloat(parseFloat('<%= request.getAttribute("stats.bosque") %>').toFixed(2));
	var cuerpoDeAgua = parseFloat(parseFloat('<%= request.getAttribute("stats.cuerpoDeAgua") %>').toFixed(2));
	var herbazal = parseFloat(parseFloat('<%= request.getAttribute("stats.herbazal") %>').toFixed(2));	
	var infraestructura = parseFloat(parseFloat('<%= request.getAttribute("stats.infraestructura") %>').toFixed(2));
	var manglar = parseFloat(parseFloat('<%= request.getAttribute("stats.manglar") %>').toFixed(2));
	var nubes = parseFloat(parseFloat('<%= request.getAttribute("stats.nubes") %>').toFixed(2));
	var otrosCultivos = parseFloat(parseFloat('<%= request.getAttribute("stats.otrosCultivos") %>').toFixed(2));
	var palmaAceitera = parseFloat(parseFloat('<%= request.getAttribute("stats.palmaAceitera") %>').toFixed(2));
	var paramo = parseFloat(parseFloat('<%= request.getAttribute("stats.paramo") %>').toFixed(2));
	var pina = parseFloat(parseFloat('<%= request.getAttribute("stats.pina") %>').toFixed(2));
	var sabana = parseFloat(parseFloat('<%= request.getAttribute("stats.sabana") %>').toFixed(2));
	var sombras = parseFloat(parseFloat('<%= request.getAttribute("stats.sombras") %>').toFixed(2));
	var terrenoDescubierto = parseFloat(parseFloat('<%= request.getAttribute("stats.terrenoDescubierto") %>').toFixed(2));
	var vegetacionAnegada = parseFloat(parseFloat('<%= request.getAttribute("stats.vegetacionAnegada") %>').toFixed(2));
	
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
          title: 'Cobertura',
          width: 600,
          height: 500,
          colors: ['#00EEEE', '#267300', '#0000FF', '#FFFFBE', '#FF0000', '#A020F0', '#FFFFFF', '#EE9A00', '#8B8B00', '#EED2EE', '#008B8B', '#EE30A7', '#000000', '#8B4726', '#7FFF00'],
    	  pieSliceBorderColor : "#000000"     
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
<div id="basic-modal-content">
<table border="1" width="600"><tr><td>
<div id="table_div" style="width: 300px;"></div>
</td><td>
<div id="piechart" style="width: 600px;"></div>
</td></tr></table>
</div>