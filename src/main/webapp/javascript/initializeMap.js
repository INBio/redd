    	var arrayCategorias = new Array();

	var map, drawControls, geojson, vectors;
    var Bosque_1986, Bosque_2000, Bosque_2011; 
    var No_Bosque_2000;
    var BosqueLoss_1986_2000, BosqueLoss_1986_2011, BosqueGain_1986_2000, BosqueGain_1986_2011;
    var arr_gainLoss;
    
    OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';
    
    /**
     * Initialize everything related to the map, including Google Layers,
     * and all other custom layers.
     */
  
    
    geojson = new OpenLayers.Format.GeoJSON();
    
    
map = new OpenLayers.Map( {
	projection: 'EPSG:3857',
	maxResolution: 1000,
	theme: null,
    div: "map",
    layers: [
    		new OpenLayers.Layer.OSM("Open Street Map"),
             new OpenLayers.Layer.Google(
             "Google Streets", // the default
             {
                 numZoomLevels: 20
             }
                ),                     
        new OpenLayers.Layer.Google(
            "Google Physical", {
                type: google.maps.MapTypeId.TERRAIN
            }
        ),        
    ],
    controls: [
        new OpenLayers.Control.Navigation({
            dragPanOptions: {
                enableKinetic: true
            }
        }),
		 new OpenLayers.Control.MousePosition({
			prefix: '<a target="_blank" ' +
			'href="http://spatialreference.org/ref/epsg/4326/">' +
			'EPSG:4326</a> coordinates: ',
			separator: ' | ',
			numDigits: 2,
			emptyString: 'Mouse is not over map.'
		}),   
        new OpenLayers.Control.ZoomBox({alwaysZoom:true}),
        new OpenLayers.Control.ZoomPanel(),
        new OpenLayers.Control.ScaleLine(),
    ],
    center: new OpenLayers.LonLat(-85.0, 9.7)
	// Google.v3 uses web mercator as projection, so we have to
	// transform our coordinates
	.transform('EPSG:4326', 'EPSG:3857'),
	zoom: 8
});

//this layer is the one used for selecting the free-hand polygon
vectors = new OpenLayers.Layer.Vector("Vector Layer");

//Creation of a custom panel with a ZoomBox control with the alwaysZoom option sets to true				
OpenLayers.Control.CustomNavToolbar = OpenLayers.Class(OpenLayers.Control.Panel, {

    /**
     * Constructor: OpenLayers.Control.NavToolbar 
     * Add our two mousedefaults controls.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be used
     *     to extend the control.
     */
	
	
    initialize: function(options) {
        OpenLayers.Control.Panel.prototype.initialize.apply(this, [options]);
        this.addControls([
          new OpenLayers.Control.EditingToolbar(vectors),
          new OpenLayers.Control.ZoomBox({alwaysZoom:true})
        ]);
		// To make the custom navtoolbar use the regular navtoolbar style
		this.displayClass = 'olControlNavToolbar'
    },
	
	

    /**
     * Method: draw 
     * calls the default draw, and then activates mouse defaults.
     */
    draw: function() {
        var div = OpenLayers.Control.Panel.prototype.draw.apply(this, arguments);
        this.defaultControl = this.controls[0];
        return div;
    }
});



map.addControl(new OpenLayers.Control.LayerSwitcher());




// allow testing of specific renderers via "?renderer=Canvas", etc
var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;


var No_Bosque_2000 = new OpenLayers.Layer.WMS("No Bosque 2000",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
            'layers': 'redd:no_bosque_2000',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );



BosqueLoss_1986_2000 = new OpenLayers.Layer.WMS("Perdida Boscosa 1986-2000",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
    	'layers': 'redd:bosque_perdido_2000',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );    
/*
BosqueLoss_1986_2011 = new OpenLayers.Layer.WMS("Perdida Boscosa 1986-2011",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
            'layers': 'redd:bosque_loss_1986_2011_split',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );     
*/
BosqueGain_1986_2000 = new OpenLayers.Layer.WMS("Ganancia Boscosa 1986-2000",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
    	'layers': 'redd:bosque_recuperado_2000',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );     


/*
BosqueGain_1986_2011 = new OpenLayers.Layer.WMS("Ganancia Boscosa 1986-2011",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
            'layers': 'redd:bosque_gain_1986_2011_split',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );  
*/

var Bosque_1986 = new OpenLayers.Layer.WMS("Bosque 1986",
    "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
        'layers': 'redd:bosque_1986',
        transparent: true,
        format: 'image/png'
    }, {
        isBaseLayer: false
    }
);

var BosquePermanece_2000 = new OpenLayers.Layer.WMS("Bosque permanece 86-2000",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
            'layers': 'redd:bosque_permanece_2000',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );


var Bosque_2000 = new OpenLayers.Layer.WMS("Bosque 2000",
        "http://172.16.16.82:8080/geoserver/wms", {
            'layers': 'redd:bosque_2000',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );

var Bosque_2011 = new OpenLayers.Layer.WMS("Bosque 2011",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
            'layers': 'redd:bosque_2011',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    );

LandCover_2000 = new OpenLayers.Layer.WMS("Land Cover 2000",
        "http://172.16.16.82:8080/geoserver/gwc/service/wms", {
            'layers': 'redd:land_cover_2000',
            transparent: true,
            format: 'image/png'
        }, {
            isBaseLayer: false
        }
    ); 





// array that holds all forest gains/losses to be referenced by JQuery functions
arr_gainLoss = {BosquePermanece_2000: BosquePermanece_2000, No_Bosque_2000: No_Bosque_2000, Bosque_1986: Bosque_1986, Bosque_2000: Bosque_2000, Bosque_2011: Bosque_2011, BosqueLoss_1986_2000: BosqueLoss_1986_2000, /*BosqueLoss_1986_2011: BosqueLoss_1986_2011,*/ BosqueGain_1986_2000: BosqueGain_1986_2000/*, BosqueGain_1986_2011: BosqueGain_1986_2011*/}
arr_deforestation = {LandCover_2000: LandCover_2000, BosqueLoss_1986_2000: BosqueLoss_1986_2000}
arr_forestation = {LandCover_2000: LandCover_2000, BosqueGain_1986_2000: BosqueGain_1986_2000}

LandCover_2000.setVisibility(false);
Bosque_1986.setVisibility(false);
BosquePermanece_2000.setVisibility(false);
Bosque_2000.setVisibility(false);
Bosque_2011.setVisibility(false);
No_Bosque_2000.setVisibility(false);

BosqueLoss_1986_2000.setVisibility(false);
BosqueGain_1986_2000.setVisibility(false);
//BosqueLoss_1986_2011.setVisibility(false);
//BosqueGain_1986_2011.setVisibility(false);

// add all configured layers to the map
map.addLayer(LandCover_2000);
map.addLayer(Bosque_1986);
map.addLayer(Bosque_2000);
map.addLayer(BosquePermanece_2000);
map.addLayer(Bosque_2011);
map.addLayer(No_Bosque_2000);
map.addLayer(BosqueLoss_1986_2000);
map.addLayer(BosqueGain_1986_2000);
//map.addLayer(BosqueLoss_1986_2011);
//map.addLayer(BosqueGain_1986_2011);
map.addLayer(vectors);


    

        
		var panel = new OpenLayers.Control.CustomNavToolbar();
        map.addControl(panel);	


    function toggleControl(element) {
        for (key in drawControls) {
            var control = drawControls[key];
            if (element.value == key && element.checked) {
                control.activate();
            } else {
                control.deactivate();
            }
        }
    }

    function update() {
        var clickout = document.getElementById("clickout").checked;
        if (clickout != drawControls.select.clickout) {
            drawControls.select.clickout = clickout;
        }

        var box = document.getElementById("box").checked;
        if (box != drawControls.select.box) {
            drawControls.select.box = box;
            if (drawControls.select.active) {
                drawControls.select.deactivate();
                drawControls.select.activate();
            }
        }
    }