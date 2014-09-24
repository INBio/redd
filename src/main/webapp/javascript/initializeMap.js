
    var map, drawControls, geojson, vectors;
    var stuff = { myText: 'hello world!' };
    var BosqueLoss_1986_2000, BosqueLoss_1986_2011, BosqueGain_1986_2000, BosqueGain_1986_2011;
    var arr_gainLoss;

    OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';

    /**
     * Initialize everything related to the map, including Google Layers,
     * and all other custom layers.
     */
    function init() {

        geojson = new OpenLayers.Format.GeoJSON();

        map = new OpenLayers.Map('map', {
            projection: 'EPSG:3857',
            layers: [
                new OpenLayers.Layer.Google(
                    "Google Physical", {
                        type: google.maps.MapTypeId.TERRAIN
                    }
                ),
                new OpenLayers.Layer.Google(
                    "Google Streets", // the default
                    {
                        numZoomLevels: 20
                    }
                ),
                new OpenLayers.Layer.Google(
                    "Google Hybrid", {
                        type: google.maps.MapTypeId.HYBRID,
                        numZoomLevels: 20
                    }
                ),
                new OpenLayers.Layer.Google(
                    "Google Satellite", {
                        type: google.maps.MapTypeId.SATELLITE,
                        numZoomLevels: 22
                    }
                )
            ],
            center: new OpenLayers.LonLat(-84.0, 9.7)
                // Google.v3 uses web mercator as projection, so we have to
                // transform our coordinates
                .transform('EPSG:4326', 'EPSG:3857'),
            zoom: 7
        });

        // allow testing of specific renderers via "?renderer=Canvas", etc
        var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
        renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

        var demolayer = new OpenLayers.Layer.WMS(
                "City of Boulder","http://localhost:8090/geoserver/gwc/service/wms", {
                'layers': 'redd:bosque_gain_1986_2000',
                format: 'image/png',
                transparent: true
                }, {
                    isBaseLayer: false
                }
            );   
        
        
        var Pina = new OpenLayers.Layer.WMS("Pina",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:V3_Pina',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );        
        
        BosqueLoss_1986_2000 = new OpenLayers.Layer.WMS("Perdida Boscosa 1986-2000",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:bosque_loss_1986_2000',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );    
        
        BosqueLoss_1986_2011 = new OpenLayers.Layer.WMS("Perdida Boscosa 1986-2011",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:bosque_loss_1986_2011',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );     
        
        BosqueGain_1986_2000 = new OpenLayers.Layer.WMS("Ganancia Boscosa 1986-2000",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:bosque_gain_1986_2000_split',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );     
        
        
        
        BosqueGain_1986_2011 = new OpenLayers.Layer.WMS("Ganancia Boscosa 1986-2011",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:bosque_gain_1986_2011',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );  
     
        

        BosqueLoss_1986_2000.setVisibility(false);
        BosqueLoss_1986_2011.setVisibility(false);
        BosqueGain_1986_2000.setVisibility(false);
        BosqueGain_1986_2011.setVisibility(false);


        // add all configured layers to the map
        map.addLayer(BosqueLoss_1986_2000);
        map.addLayer(BosqueLoss_1986_2011);
        map.addLayer(BosqueGain_1986_2000);
        map.addLayer(BosqueGain_1986_2011);        

        arr_gainLoss = {BosqueLoss_1986_2000: BosqueLoss_1986_2000, BosqueLoss_1986_2011: BosqueLoss_1986_2011, BosqueGain_1986_2000: BosqueGain_1986_2000, BosqueGain_1986_2011: BosqueGain_1986_2011}
        
        var Puntos = new OpenLayers.Layer.WMS("Puntos",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:V3_TEMP',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );    
        
        var PuntosTotal = new OpenLayers.Layer.WMS("PuntosTotal",
                "http://localhost:8090/geoserver/redd/wms", {
                    'layers': 'redd:V3_TEMP_TOTAL',
                    transparent: true,
                    format: 'image/gif'
                }, {
                    isBaseLayer: false
                }
            );         
        
        Banano = new OpenLayers.Layer.WMS("Banano",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Banano',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        Bosque = new OpenLayers.Layer.WMS("Bosque",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Bosque',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var CuerpoDeAgua = new OpenLayers.Layer.WMS("CuerpoDeAgua",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_CuerpoDeAgua',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Herbazal = new OpenLayers.Layer.WMS("Herbazal",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Herbazal',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Infraestructura = new OpenLayers.Layer.WMS("Infraestructura",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Infraestructura',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Manglar = new OpenLayers.Layer.WMS("Manglar",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Manglar',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Nubes = new OpenLayers.Layer.WMS("Nubes",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Nubes',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var OtrosCultivos = new OpenLayers.Layer.WMS("OtrosCultivos",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_OtrosCultivos',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var PalmaAceitera = new OpenLayers.Layer.WMS("PalmaAceitera",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_PalmaAceitera',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Paramo = new OpenLayers.Layer.WMS("Paramo",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Paramo',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Pina = new OpenLayers.Layer.WMS("Pina",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Pina',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Sabana = new OpenLayers.Layer.WMS("Sabana",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Sabana',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var Sombras = new OpenLayers.Layer.WMS("Sombras",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_Sombras',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var TerrenoDescubierto = new OpenLayers.Layer.WMS("TerrenoDescubierto",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_TerrenoDescubierto',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var VegetacionAnegada = new OpenLayers.Layer.WMS("VegetacionAnegada",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:V3_VegetacionAnegada',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var heredia = new OpenLayers.Layer.WMS("Heredia",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:PROVINCIA_HEREDIA',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        var guanacaste = new OpenLayers.Layer.WMS("Guanacaste",
            "http://localhost:8090/geoserver/redd/wms", {
                'layers': 'redd:PROVINCIA_GUANACASTE',
                transparent: true,
                format: 'image/gif'
            }, {
                isBaseLayer: false
            }
        );

        // this layer is the one used for selecting the free-hand polygon
        vectors = new OpenLayers.Layer.Vector("Vector Layer", {
            renderers: renderer
        });
        vectors.events.on({
            'featureselected': function(feature) {
                document.getElementById('counter').innerHTML = this.selectedFeatures.length;
            },
            'featureunselected': function(feature) {
                document.getElementById('counter').innerHTML = this.selectedFeatures.length;
            }
        });        
        
        demolayer.setVisibility(false);
        Puntos.setVisibility(false);
        PuntosTotal.setVisibility(false);

        Banano.setVisibility(false);
        Bosque.setVisibility(false);
        CuerpoDeAgua.setVisibility(false);
        Herbazal.setVisibility(false);
        Infraestructura.setVisibility(false);
        Manglar.setVisibility(false);
        Nubes.setVisibility(false);
        OtrosCultivos.setVisibility(false);
        PalmaAceitera.setVisibility(false);
        Paramo.setVisibility(false);
        Pina.setVisibility(false);
        Sabana.setVisibility(false);
        Sombras.setVisibility(false);
        TerrenoDescubierto.setVisibility(false);
        VegetacionAnegada.setVisibility(false);
        heredia.setVisibility(false);
        guanacaste.setVisibility(false);

        // add all configured layers to the map
        map.addLayer(demolayer);
        
        map.addLayer(Puntos);
        map.addLayer(PuntosTotal);
        map.addLayer(Banano);
        map.addLayer(Bosque);
        map.addLayer(CuerpoDeAgua);
        map.addLayer(Herbazal);
        map.addLayer(Infraestructura);
        map.addLayer(Manglar);

        map.addLayer(vectors);

        map.addControl(new OpenLayers.Control.LayerSwitcher());

        drawControls = {
            polygon: new OpenLayers.Control.DrawFeature(
                vectors, OpenLayers.Handler.Polygon
            ),
        };

        for (var key in drawControls) {
            map.addControl(drawControls[key]);
        }
    }

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