	<!-- AREAS DE CONSERVACION -->

	<table>
	<tr><td>
		
			<input id="areas_silvestres_text" type="text" placeholder="Area Silvestre Protegida"></p>
			<input id="areas_silvestres_text_hidden" type="hidden">
		
	</td></tr><tr><td>
	    <select id="select_land_cover_silvestre">
			<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>	
		</select>
	</td><td>
		<input type="button" value="Calcular" id="bot_cobertura_silvestre">	
	</td></tr>
	</table>