<%@ taglib prefix="s" uri="/struts-tags" %> 
    <select id="select_cobertura_distrito">
    	<option value="-">-</option>
		<s:iterator value="distritos">
			<option value="<s:property value="id"/>"><s:property value="name"/></option>
		</s:iterator>
	</select>