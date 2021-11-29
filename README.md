# jooq-demo
Example project for JPAStreamer, JOOQ or JINQ

Execution time with the different libraries

JINQ
---------------------------------------------
ns         %     Task name
---------------------------------------------
575064200  078%  getAttributeValues
089023500  012%  getAllDSAFromSourceRelationship
026828400  004%  getEntities
045532200  006%  getRelationsShipDatasetFlags

JOOQ
---------------------------------------------
ns         %     Task name
---------------------------------------------
790695000  095%  getAttributeValues
021368600  003%  getAllDSAFromSourceRelationship
009240900  001%  getEntities
009983700  001%  getRelationsShipDatasetFlags

JPAStreamer
---------------------------------------------
ns         %     Task name
---------------------------------------------
225410300  072%  getAttributeValues
033178900  011%  getAllDSAFromSourceRelationship
024116400  008%  getEntities
029418000  009%  getRelationsShipDatasetFlags
