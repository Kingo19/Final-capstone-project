BEGIN;


DROP TABLE IF EXISTS meal_type CASCADE;
DROP TABLE IF EXISTS plan_meal CASCADE;
DROP TABLE IF EXISTS meal_recipe CASCADE;
DROP TABLE IF EXISTS user_meal CASCADE;
DROP TABLE IF EXISTS recipe_users CASCADE;
DROP TABLE IF EXISTS recipe_ingredient CASCADE;
DROP TABLE IF EXISTS daily_plan_meals CASCADE;

DROP TABLE IF EXISTS type CASCADE;
DROP TABLE IF EXISTS meal_plan CASCADE;
DROP TABLE IF EXISTS meal CASCADE;
DROP TABLE IF EXISTS ingredient CASCADE;
DROP TABLE IF EXISTS recipe CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS daily_plan CASCADE;
DROP TABLE IF EXISTS weekday CASCADE;



CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL
);


CREATE TABLE recipe (
    recipe_id SERIAL PRIMARY KEY,
    recipe_name varchar(256) NOT NULL UNIQUE,
    recipe_instructions varchar(2000) NOT NULL
);


CREATE TABLE ingredient (
    ingredient_id SERIAL PRIMARY KEY,
    ingredient_name varchar(200) NOT NULL UNIQUE
);


CREATE TABLE meal (
    meal_id SERIAL PRIMARY KEY,
    meal_name varchar(50) NOT NULL
);


--CREATE TABLE meal_plan (
--  	meal_plan_id serial NOT NULL,
--  	user_id int NOT NULL,
--  	meal_plan_name varchar(80),
--  	CONSTRAINT pk_meal_plan_id PRIMARY KEY(meal_plan_id),
--  	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
--  );


CREATE TABLE type (
    type_id SERIAL PRIMARY KEY,
    type_name varchar(50) NOT NULL
);


CREATE TABLE recipe_ingredient (
    recipe_id int REFERENCES recipe(recipe_id),
    ingredient_id int REFERENCES ingredient(ingredient_id),
    PRIMARY KEY (recipe_id, ingredient_id)
);


CREATE TABLE recipe_users (
    recipe_id int REFERENCES recipe(recipe_id),
    user_id int REFERENCES users(user_id),
    PRIMARY KEY (recipe_id, user_id)
);

CREATE TABLE meal_recipe (
    meal_id int NOT NULL REFERENCES meal(meal_id),
    recipe_id int NOT NULL REFERENCES recipe(recipe_id)
);

CREATE TABLE meal_type (
    meal_id int NOT NULL REFERENCES meal(meal_id),
    type_id int NOT NULL REFERENCES type(type_id)
);

CREATE TABLE user_meal (
    meal_id int NOT NULL REFERENCES meal(meal_id),
    user_id int NOT NULL REFERENCES users(user_id)
);

--CREATE TABLE weekday (
--    weekday_id serial NOT NULL,
--    weekday varchar(15) NOT NULL,
--    CONSTRAINT pk_weekday_id PRIMARY KEY(weekday_id)
--);

CREATE TABLE daily_plan (
daily_plan_name varchar(50) NOT NULL,
daily_plan_id serial NOT NULL,
--meal_plan_id int NOT NULL,
--weekday_id int NOT NULL,
dayOfPlan date NOT NULL,
user_id int NOT NULL,
CONSTRAINT pk_daily_plan_id PRIMARY KEY (daily_plan_id),
--CONSTRAINT fk_meal_plan_id FOREIGN KEY (meal_plan_id) REFERENCES meal_plan(meal_plan_id),
--CONSTRAINT fk_weekday_id FOREIGN KEY (weekday_id) REFERENCES weekday(weekday_id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE daily_plan_meals (
	daily_plan_id int NOT NULL,
	meal_id int NOT NULL,
	CONSTRAINT fk_daily_plan_id FOREIGN KEY (daily_plan_id) REFERENCES daily_plan(daily_plan_id),
	CONSTRAINT fk_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id)
);
COMMIT TRANSACTION;
