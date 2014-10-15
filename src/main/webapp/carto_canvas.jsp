	<!-- PROVINCIAS / CANTONES / DISTRITOS -->
	<table>
	<tr><td>
	    <select id="select_cobertura_provincia">
	    	<option value="-">-</option>
			<s:iterator value="geoPolygons">
				<option value="<s:property value="id"/>"><s:property value="name"/></option>
			</s:iterator>
		</select> 
	</td><td>
	    <select id="select_land_cover_provincia">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td>
		<input type="button" value="Cobertura" id="bot_cobertura_provincia">	
	</td></tr>
	<tr><td>
	<div id="cantones">
	    <select id="select_cobertura_canton">
			<option value="-">-</option>
		</select> 	
	</div>
	</td><td>
	    <select id="select_land_cover_canton">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td>
		<input type="button" value="Cobertura" id="bot_cobertura_canton">
	</td></tr>
	<tr><td>
	<div id="distritos">
	    <select id="select_cobertura_distrito">
			<option value="-">-</option>
		</select> 	
	</div>
	</td><td>
	    <select id="select_land_cover_distrito">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>	
	</td><td>
		<input type="button" value="Cobertura" id="bot_cobertura_distrito">
	</td></tr>
	</table>