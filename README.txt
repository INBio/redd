

Para actualizar los datos de cobertura en caso que los "orig_fid" vengan con problemas,
ejecutar las siguientes instrucciones:
------------------------------------------------------------------------------------
UPDATE <tabla> SET orig_fid = 1  WHERE cobertura = 'Bosque';
UPDATE <tabla> SET orig_fid = 2  WHERE cobertura = 'Cuerpo de agua';
UPDATE <tabla> SET orig_fid = 3  WHERE cobertura = 'Otros cultivos';
UPDATE <tabla> SET orig_fid = 4  WHERE cobertura = 'Herbazal';
UPDATE <tabla> SET orig_fid = 5  WHERE cobertura = 'Infraestructura';
UPDATE <tabla> SET orig_fid = 6  WHERE cobertura = 'Manglar';
UPDATE <tabla> SET orig_fid = 7  WHERE cobertura = 'Terreno descubierto';
UPDATE <tabla> SET orig_fid = 8  WHERE cobertura = 'Vegetacion anegada';
UPDATE <tabla> SET orig_fid = 9  WHERE cobertura = 'Nubes';
UPDATE <tabla> SET orig_fid = 10 WHERE cobertura = 'Sombras';
UPDATE <tabla> SET orig_fid = 11 WHERE cobertura = 'Sabana';
UPDATE <tabla> SET orig_fid = 12 WHERE cobertura = 'Paramo';
UPDATE <tabla> SET orig_fid = 13 WHERE cobertura = 'Banano';
UPDATE <tabla> SET orig_fid = 14 WHERE cobertura = 'Palma aceitera';
UPDATE <tabla> SET orig_fid = 15 WHERE cobertura = 'Pi–a';