<script type="text/javascript">
	var banano = parseInt('<%= request.getAttribute("stats.banano") %>');
	var bosque = parseInt('<%= request.getAttribute("stats.bosque") %>');
	var cuerpoDeAgua = parseInt('<%= request.getAttribute("stats.cuerpoDeAgua") %>');
	var herbazal = parseInt('<%= request.getAttribute("stats.herbazal") %>');	
	var infraestructura = parseInt('<%= request.getAttribute("stats.infraestructura") %>');
	var manglar = parseInt('<%= request.getAttribute("stats.manglar") %>');
	var nubes = parseInt('<%= request.getAttribute("stats.nubes") %>');
	var otrosCultivos = parseInt('<%= request.getAttribute("stats.otrosCultivos") %>');
	var palmaAceitera = parseInt('<%= request.getAttribute("stats.palmaAceitera") %>');
	var paramo = parseInt('<%= request.getAttribute("stats.paramo") %>');
	var pina = parseInt('<%= request.getAttribute("stats.pina") %>');
	var sabana = parseInt('<%= request.getAttribute("stats.sabana") %>');
	var sombras = parseInt('<%= request.getAttribute("stats.sombras") %>');
	var terrenoDescubierto = parseInt('<%= request.getAttribute("stats.terrenoDescubierto") %>');
	var vegetacionAnegada = parseInt('<%= request.getAttribute("stats.vegetacionAnegada") %>');
	
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
			hAxis: {title: 'Ano', titleTextStyle: {color: 'red'}}
		};        
        
        var chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
        chart.draw(data, options);
	}
	

</script>

<div id="piechart" style="width: 900px; height: 500px;"></div>