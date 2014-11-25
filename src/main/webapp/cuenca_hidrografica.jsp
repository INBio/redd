	<!-- CUENCA HIDROGRAFICA -->
	<table>
	<tr><td>
	    <select id="select_cobertura_cuenca">
	    	<option value="-">-</option>
			<s:iterator value="cuencasHidrograficasPolygons">
				<option value="<s:property value="id"/>"><s:property value="name"/></option>
			</s:iterator>
		</select> 
	</td><td>
	    <select id="select_land_cover_cuenca">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td>
		<input type="button" value="Calcular" id="bot_cobertura_cuenca">	
	</td></tr>
	</table>