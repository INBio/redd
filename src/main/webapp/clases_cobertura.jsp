    <table width="100%"><tr>
    
    <td colspan="2">
    
    <center>
    <select id="select_cobertura_dinamico_pre">
	<option value="-">1986</option>
	</select>
	</center>
	<p/><br/><p/>
	
	</td></tr>
    
		<s:iterator value="landCoverCategories">
			<tr><td><input type="checkbox" id="chbox_land_cover" value="<s:property value="id"/>"><font size="-2"><s:property value="name"/></font></td></tr>
		</s:iterator>
    
    
    </table>