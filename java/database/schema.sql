BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS recipe_ingredient;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS recipe_users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE recipe (
	recipe_id SERIAL NOT NULL,
	recipe_name varchar(256) NOT NULL,
	recipe_instructions varchar(400) NOT NULL,
	constraint PK_recipe PRIMARY KEY (recipe_id)
);

CREATE TABLE ingredient (
	ingredient_id serial NOT NULL,
	ingredient_name varchar(200) NOT NULL,
	CONSTRAINT PK_ingredient_id PRIMARY KEY(ingredient_id),
	CONSTRAINT UQ_ingredient_name UNIQUE(ingredient_name)
);

CREATE TABLE recipe_ingredient (
	recipe_id int,
    ingredient_id int,
    CONSTRAINT PK_recipe_ingredient PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT FK_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)
);

CREATE TABLE recipe_users (
	recipe_id int,
    user_id int,
    CONSTRAINT PK_recipe_user PRIMARY KEY (recipe_id, user_id),
    CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;
