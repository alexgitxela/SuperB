CREATE TABLE superbook (
	id uuid NOT NULL,
	publication_date date,
	title character varying NOT NULL,
	author character varying NOT NULL,
	publisher_id uuid NOT NULL,
	CONSTRAINT superbook_pkey
		PRIMARY KEY (id),
	CONSTRAINT uc_superbook_title
		UNIQUE (name)
);

INSERT INTO superbook (id, title, author, publisher_id) VALUES ('60d3a55c-b643-4cf2-b248-57b61d14e61b', 'Stupid Spoon', 'Stupid Author', '92d3a55c-b643-4cf2-b248-57b61d14e61b');
INSERT INTO superbook (id, title, author, publisher_id) VALUES ('70d3a55c-b643-4cf2-b248-57b61d14e61b', 'Stupid Fork', 'Mad Author', '42d3a55c-b643-4cf2-b248-57b61d14e61b');

CREATE TABLE publisher (
	id uuid NOT NULL,
	name character varying NOT NULL,
	CONSTRAINT publisher_pkey
		PRIMARY KEY (id),
	CONSTRAINT uc_publisher_name
		UNIQUE (name)
);

INSERT INTO publisher (id, name) VALUES ('92d3a55c-b643-4cf2-b248-57b61d14e61b', 'Stupid Publisher');
INSERT INTO publisher (id, name) VALUES ('9fa89c88-f488-49be-acbf-f9bb04291f64', 'Foolish Publisher');
INSERT INTO publisher (id, name) VALUES ('42d3a55c-b643-4cf2-b248-57b61d14e61b', 'Crazy Publisher');

CREATE TABLE actor (
	id uuid NOT NULL,
	name character varying NOT NULL,
	CONSTRAINT actor_pkey
		PRIMARY KEY (id),
	CONSTRAINT uc_actor_name
		UNIQUE (name)
);

INSERT INTO actor (id, name) VALUES ('57ad78f4-900c-4674-a510-79a77fe14dc7', 'Stupid Fork');
INSERT INTO actor (id, name) VALUES ('b354a4d0-bf40-429d-9cf0-c492c1e32bbf', 'Stupid Spoon');
INSERT INTO actor (id, name) VALUES ('d70318d1-0da6-438a-ae35-4c3683304567', 'Mad Napkin');

CREATE TABLE actor_to_superbook (
	superbook_id uuid NOT NULL,
	actor_id uuid NOT NULL,
	CONSTRAINT pk_actor_to_superbook
		PRIMARY KEY (superbook_id, actor_id)
);

INSERT INTO actor_to_superbook (superbook_id, actor_id) VALUES ('60d3a55c-b643-4cf2-b248-57b61d14e61b', '57ad78f4-900c-4674-a510-79a77fe14dc7');
INSERT INTO actor_to_superbook (superbook_id, actor_id) VALUES ('70d3a55c-b643-4cf2-b248-57b61d14e61b', '57ad78f4-900c-4674-a510-79a77fe14dc7');
INSERT INTO actor_to_superbook (superbook_id, actor_id) VALUES ('60d3a55c-b643-4cf2-b248-57b61d14e61b', 'd70318d1-0da6-438a-ae35-4c3683304567');

CREATE INDEX ix_superbook_publisher_id
	ON superbook
	USING btree
	(publisher_id);

ALTER TABLE superbook
	ADD CONSTRAINT fklo32kr1u74ko4hvpo0ne23agg
		FOREIGN KEY (publisher_id)
		REFERENCES publisher (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE actor_to_superbook
	ADD CONSTRAINT fk5mkwjhodr2g64ew8n9rqot5rh
		FOREIGN KEY (superbook_id)
		REFERENCES superbook (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE actor_to_superbook
	ADD CONSTRAINT fkm5l4enoho1c33jbawynqnmomt
		FOREIGN KEY (actor_id)
		REFERENCES actor (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION;