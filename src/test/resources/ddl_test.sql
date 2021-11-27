CREATE SEQUENCE IF NOT EXISTS sc_pk_entity START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
ALTER SEQUENCE sc_pk_entity RESTART WITH (select max(id_entity) from entity) + 1;

CREATE SEQUENCE IF NOT EXISTS sc_pk_relationship START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
ALTER SEQUENCE sc_pk_relationship RESTART WITH (select max(id_relationship) from relationship) + 1;

CREATE SEQUENCE IF NOT EXISTS sc_pk_attributeValue START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
ALTER SEQUENCE sc_pk_attributeValue RESTART WITH (select max(id_attribute_value) from attribute_value) + 1;