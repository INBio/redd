    <table><tr><td>
    <center>
	    <select id="select_cobertura_dinamico">
	    	<option value="-">-</option>
	    	<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>
		</select> 	        
    <br/><p/><br/>
    <input type="button" value="Cargar estad&#237;sticas" id="bot_statistics">
    
    </td><td width="237">
    
    <center>
	    <select id="select_cobertura_dinamico_pre">
	    	<option value="-">-</option>
	    	<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>
		</select>
	
	    <select id="select_cobertura_dinamico_post">
	    	<option value="-">-</option>
	    	<s:iterator value="landCovers">
				<option value="<s:property value="id"/>"><s:property value="year"/></option>
			</s:iterator>
		</select>
	
	<br/><p/><br/>
    <input type="button" value="Comparar a&#241;os" id="bot_compare">
    </center>
    </td></tr></table>