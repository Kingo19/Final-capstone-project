START TRANSACTION;

CREATE TABLE ingredients (
	ingredient_id serial,
	ingredient_name varchar(200) NOT NULL,
	CONSTRAINT PK_ingredient_id PRIMARY KEY(ingredient_id),
	CONSTRAINT UQ_ingredient_name UNIQUE(ingredient_name)
);
commit;