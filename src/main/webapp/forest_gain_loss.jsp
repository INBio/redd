	<!-- GANANCIA / PERDIDA BOSCOSA-->
	
	Rango de anos a observar:
	<table>
	<tr><td>
	    <select id="select_land_cover_ganancia_perdida_first">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td>
    	<select id="select_land_cover_ganancia_perdida_second">
    	<s:iterator value="landCovers">
			<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td></tr><tr><td colspan="2">
		<select id="select_land_cover_ganancia_perdida">
			<option value="1">Combinado: Perdida/Aumento</option>
			<option value="2">Deforestacion</option>
			<option value="3">Aumento Cobertura</option>
			<option value="4">Extension Forestal</option>
		</select>

		<input type="button" value="Ver" id="bot_perdida_ganancia_boscosa">	
		

	</td></tr>
	</table>