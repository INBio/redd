<%@ taglib prefix="s" uri="/struts-tags" %> 
    <select id="select_cobertura_canton">
        <option value="-">-</option>
		<s:iterator value="cantones">
			<option value="<s:property value="id"/>"><s:property value="name"/></option>
		</s:iterator>
	</select>