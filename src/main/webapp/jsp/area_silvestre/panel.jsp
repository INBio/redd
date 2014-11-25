<script type="text/javascript">
$(function() {

			<%@page import="java.util.List"%>
			<%@page import="redd.model.GeographicLayerPolygon"%>

			/*
			 * Autocomplete with key-value pairs
			 */
			data = [
			
			<% for (int i=0; i<((List<GeographicLayerPolygon>)request.getAttribute("areasSilvestresPolygons")).size(); i++) { %>
			    { value: "<%= ((List<GeographicLayerPolygon>)request.getAttribute("areasSilvestresPolygons")).get(i).getId() %>", label: "<%= ((List<GeographicLayerPolygon>)request.getAttribute("areasSilvestresPolygons")).get(i).getName() %>" },
			<% } %>	
			];



				$("#areas_silvestres_text").autocomplete({
					source: function(request, response) {
						var results = $.ui.autocomplete.filter(data, request.term);
						response(results.slice(0, 5));
					},
					select: function(event, ui) {
						// prevent autocomplete from updating the textbox
						event.preventDefault();
						// manually update the textbox and hidden field
						$(this).val(ui.item.label);
						$("#areas_silvestres_text_hidden").val(ui.item.value).trigger('change');
					},
					open: function(event, ui) {
            			$(".ui-autocomplete").css("z-index", 20000);
        			}						
				});
			});
			
			</script>