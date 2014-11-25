    <table width="100%"><tr>
    
    <td colspan="2">
    
    <center>
	<select id="select_land_cover_clases_cobertura">
	<s:iterator value="landCovers">
		<option value="<s:property value="id"/>"><s:property value="year"/></option>
		</s:iterator>	
	</select>
	</center>
	<p/><br/><p/>
	
	</td></tr>
    
		<s:iterator value="landCoverCategories">
			<tr><td>
			<div id="cobertura<s:property value="id"/>">.</div>
			</td><td>
				<input type="checkbox"  id="chbox_land_cover" value="<s:property value="id"/>"><font size="-2"><s:property value="name"/></font>
			</td></tr>
		</s:iterator>
    
    
    </table>