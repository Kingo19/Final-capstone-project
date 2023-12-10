BEGIN TRANSACTION;

-- Drop tables with foreign key constraints first
DROP TABLE IF EXISTS meal_type;
DROP TABLE IF EXISTS plan_meal;
DROP TABLE IF EXISTS meal_recipe;
DROP TABLE IF EXISTS user_meal;
DROP TABLE IF EXISTS recipe_users;
DROP TABLE IF EXISTS recipe_ingredient;

-- Then drop the tables that were referenced by the foreign keys
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS plan;
DROP TABLE IF EXISTS meal;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS users;


-- Users Table
CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- Recipe Table
CREATE TABLE recipe (
    recipe_id SERIAL NOT NULL,
    recipe_name varchar(256) NOT NULL UNIQUE,
    recipe_instructions varchar(2000) NOT NULL,
    CONSTRAINT PK_recipe PRIMARY KEY (recipe_id)
);

-- Ingredient Table
CREATE TABLE ingredient (
    ingredient_id SERIAL NOT NULL,
    ingredient_name varchar(200) NOT NULL,
    CONSTRAINT PK_ingredient_id PRIMARY KEY(ingredient_id),
    CONSTRAINT UQ_ingredient_name UNIQUE(ingredient_name)
);

-- Meal Table
CREATE TABLE meal (
    meal_id SERIAL,
    meal_name varchar(50) NOT NULL,
    CONSTRAINT PK_meal PRIMARY KEY (meal_id)
);

-- Plan Table
CREATE TABLE plan (
    plan_id SERIAL,
    plan_name varchar(100) NOT NULL UNIQUE,
    user_id int NOT NULL,
    plan_date DATE NOT NULL,
    CONSTRAINT PK_plans PRIMARY KEY (plan_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Type Table
CREATE TABLE type (
    type_id SERIAL,
    type_name varchar(50) NOT NULL,
    CONSTRAINT PK_type PRIMARY KEY (type_id)
);

-- Recipe Ingredient Table
CREATE TABLE recipe_ingredient (
    recipe_id int,
    ingredient_id int,
    CONSTRAINT PK_recipe_ingredient PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT FK_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)
);

-- Recipe Users Table
CREATE TABLE recipe_users (
    recipe_id int,
    user_id int,
    CONSTRAINT PK_recipe_user PRIMARY KEY (recipe_id, user_id),
    CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- User Meal Table
CREATE TABLE user_meal (
    meal_id int NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Meal Recipe Table
CREATE TABLE meal_recipe (
    meal_id int NOT NULL,
    recipe_id int NOT NULL,
    CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
    CONSTRAINT FK_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)
);

-- Plan Meal Table
CREATE TABLE plan_meal (
    plan_id int NOT NULL,
    meal_id int NOT NULL,
    CONSTRAINT FK_plan_id FOREIGN KEY (plan_id) REFERENCES plan(plan_id),
    CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id)
);

-- Meal Type Table
CREATE TABLE meal_type (
    meal_id int NOT NULL,
    type_id int NOT NULL,
    CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
    CONSTRAINT FK_type_id FOREIGN KEY (type_id) REFERENCES type(type_id)
);

COMMIT TRANSACTION;
