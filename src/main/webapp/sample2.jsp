<script type="text/javascript">
	var banano1986 = parseInt('<%= request.getAttribute("stats1986.banano") %>');
	var bosque1986 = parseInt('<%= request.getAttribute("stats1986.bosque") %>');
	var cuerpoDeAgua1986 = parseInt('<%= request.getAttribute("stats1986.cuerpoDeAgua") %>');
	var herbazal1986 = parseInt('<%= request.getAttribute("stats1986.herbazal") %>');	
	var infraestructura1986 = parseInt('<%= request.getAttribute("stats1986.infraestructura") %>');
	var manglar1986 = parseInt('<%= request.getAttribute("stats1986.manglar") %>');
	var nubes1986 = parseInt('<%= request.getAttribute("stats1986.nubes") %>');
	var otrosCultivos1986 = parseInt('<%= request.getAttribute("stats1986.otrosCultivos") %>');
	var palmaAceitera1986 = parseInt('<%= request.getAttribute("stats1986.palmaAceitera") %>');
	var paramo1986 = parseInt('<%= request.getAttribute("stats1986.paramo") %>');
	var pina1986 = parseInt('<%= request.getAttribute("stats1986.pina") %>');
	var sabana1986 = parseInt('<%= request.getAttribute("stats1986.sabana") %>');
	var sombras1986 = parseInt('<%= request.getAttribute("stats1986.sombras") %>');
	var terrenoDescubierto1986 = parseInt('<%= request.getAttribute("stats1986.terrenoDescubierto") %>');
	var vegetacionAnegada1986 = parseInt('<%= request.getAttribute("stats1986.vegetacionAnegada") %>');

	var banano2000 = parseInt('<%= request.getAttribute("stats2000.banano") %>');
	var bosque2000 = parseInt('<%= request.getAttribute("stats2000.bosque") %>');
	var cuerpoDeAgua2000 = parseInt('<%= request.getAttribute("stats2000.cuerpoDeAgua") %>');
	var herbazal2000 = parseInt('<%= request.getAttribute("stats2000.herbazal") %>');	
	var infraestructura2000 = parseInt('<%= request.getAttribute("stats2000.infraestructura") %>');
	var manglar2000 = parseInt('<%= request.getAttribute("stats2000.manglar") %>');
	var nubes2000 = parseInt('<%= request.getAttribute("stats2000.nubes") %>');
	var otrosCultivos2000 = parseInt('<%= request.getAttribute("stats2000.otrosCultivos") %>');
	var palmaAceitera2000 = parseInt('<%= request.getAttribute("stats2000.palmaAceitera") %>');
	var paramo2000 = parseInt('<%= request.getAttribute("stats2000.paramo") %>');
	var pina2000 = parseInt('<%= request.getAttribute("stats2000.pina") %>');
	var sabana2000 = parseInt('<%= request.getAttribute("stats2000.sabana") %>');
	var sombras2000 = parseInt('<%= request.getAttribute("stats2000.sombras") %>');
	var terrenoDescubierto2000 = parseInt('<%= request.getAttribute("stats2000.terrenoDescubierto") %>');
	var vegetacionAnegada2000 = parseInt('<%= request.getAttribute("stats2000.vegetacionAnegada") %>');
	
	function drawChart() {
	
		//var data = google.visualization.arrayToDataTable([
		//	['Year', 'Banano', 'Bosque', 'Cuerpo de Agua', 'Herbazal', 'Infraestructura', 'Manglar', 'Nubes', 'Otros Cultivos','Palma Aceitera', 'Paramo', 'Pina', 'Sabana','Sombras', 'Terreno Descubierto', 'Vegetacion Anegada'],
		//	['1986', banano1986, bosque1986, cuerpoDeAgua1986, herbazal1986, infraestructura1986, manglar1986, nubes1986, otrosCultivos1986, palmaAceitera1986, paramo1986, pina1986, sabana1986, sombras1986, terrenoDescubierto1986, vegetacionAnegada1986]
		//	['2000', banano2000, bosque2000, cuerpoDeAgua2000, herbazal2000, infraestructura2000, manglar2000, nubes2000, otrosCultivos2000, palmaAceitera2000, paramo2000, pina2000, sabana2000, sombras2000, terrenoDescubierto2000, vegetacionAnegada2000]
		//]);	
  var data = google.visualization.arrayToDataTable([
    ['Year', 'Banano', 'Bosque', 'Cuerpo de Agua', 'Herbazal', 'Infraestructura', 'Manglar', 'Nubes', 'Otros Cultivos','Palma Aceitera', 'Paramo', 'Pina', 'Sabana','Sombras', 'Terreno Descubierto', 'Vegetacion Anegada'],
    ['1986',  banano1986, bosque1986, cuerpoDeAgua1986, herbazal1986, infraestructura1986, manglar1986, nubes1986, otrosCultivos1986, palmaAceitera1986, paramo1986, pina1986, sabana1986, sombras1986, terrenoDescubierto1986, vegetacionAnegada1986],
    ['2000',  banano2000, bosque2000, cuerpoDeAgua2000, herbazal2000, infraestructura2000, manglar2000, nubes2000, otrosCultivos2000, palmaAceitera2000, paramo2000, pina2000, sabana2000, sombras1986, terrenoDescubierto2000, vegetacionAnegada2000]
  ]);
		var options = {
			title: 'Comparacion entre anos',
			hAxis: {title: 'Ano', titleTextStyle: {color: 'red'}}
		};        
        
        var chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
        chart.draw(data, options);
	}
	

</script>

<div id="piechart" style="width: 900px; height: 500px;"></div>