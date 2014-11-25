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
	
		//var data = google.visualization.arrayToDataTable([
		//	['Year', 'Banano', 'Bosque', 'Cuerpo de Agua', 'Herbazal', 'Infraestructura', 'Manglar', 'Nubes', 'Otros Cultivos','Palma Aceitera', 'Paramo', 'Pina', 'Sabana','Sombras', 'Terreno Descubierto', 'Vegetacion Anegada'],
		//	['1986', banano1986, bosque1986, cuerpoDeAgua1986, herbazal1986, infraestructura1986, manglar1986, nubes1986, otrosCultivos1986, palmaAceitera1986, paramo1986, pina1986, sabana1986, sombras1986, terrenoDescubierto1986, vegetacionAnegada1986]
		//	['2000', banano2000, bosque2000, cuerpoDeAgua2000, herbazal2000, infraestructura2000, manglar2000, nubes2000, otrosCultivos2000, palmaAceitera2000, paramo2000, pina2000, sabana2000, sombras2000, terrenoDescubierto2000, vegetacionAnegada2000]
		//]);	
  var data = google.visualization.arrayToDataTable([
    ['Year', 'Banano', 'Bosque', 'Cuerpo de Agua', 'Herbazal', 'Infraestructura', 'Manglar', 'Nubes', 'Otros Cultivos','Palma Aceitera', 'Paramo', 'Pina', 'Sabana','Sombras', 'Terreno Descubierto', 'Vegetacion Anegada'],
    ['2000',  banano, bosque, cuerpoDeAgua, herbazal, infraestructura, manglar, nubes, otrosCultivos, palmaAceitera, paramo, pina, sabana, sombras, terrenoDescubierto, vegetacionAnegada]
  ]);
		var options = {
			title: 'Ganancia / Perdida (km2)',
			hAxis: {title: 'Perdida / Ganancia', titleTextStyle: {color: 'red'}},
            width: 600,
            height: 500
		};        
        
        var chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
        chart.draw(data, options);
	}
	

</script>

<div id="piechart" style="width: 600px;"></div>