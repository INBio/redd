	<!-- GANANCIA / PERDIDA BOSCOSA-->
	<table>
	<tr><td>
	    <select id="select_land_cover_ganancia_perdida_first">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td rowspan="2">
		<input type="button" value="Perdida Boscosa" id="bot_perdida_boscosa">	
		<input type="button" value="Ganancia Boscosa" id="bot_ganancia_boscosa">	
	</td></tr>
	<tr><td>
	    <select id="select_land_cover_ganancia_perdida_second">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td></tr>
	</table>