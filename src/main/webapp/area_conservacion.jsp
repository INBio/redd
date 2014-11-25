	<!-- AREAS DE CONSERVACION -->
	<table>
	<tr><td>
	    <select id="select_cobertura_conservacion">
	    	<option value="-">-</option>
			<s:iterator value="areasConservacionPolygons">
				<option value="<s:property value="id"/>"><s:property value="name"/></option>
			</s:iterator>
		</select> 
	</td><td>
	    <select id="select_land_cover_conservacion">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td>
		<input type="button" value="Calcular" id="bot_cobertura_conservacion">	
	</td></tr>
	</table>