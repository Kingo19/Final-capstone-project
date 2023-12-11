BEGIN;

-- Drop tables with foreign key constraints first
DROP TABLE IF EXISTS meal_type CASCADE;
DROP TABLE IF EXISTS plan_meal CASCADE;
DROP TABLE IF EXISTS meal_recipe CASCADE;
DROP TABLE IF EXISTS user_meal CASCADE;
DROP TABLE IF EXISTS recipe_users CASCADE;
DROP TABLE IF EXISTS recipe_ingredient CASCADE;

-- Then drop the tables that were referenced by the foreign keys
DROP TABLE IF EXISTS type CASCADE;
DROP TABLE IF EXISTS plan CASCADE;
DROP TABLE IF EXISTS meal CASCADE;
DROP TABLE IF EXISTS ingredient CASCADE;
DROP TABLE IF EXISTS recipe CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Users Table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL
);

-- Recipe Table
CREATE TABLE recipe (
    recipe_id SERIAL PRIMARY KEY,
    recipe_name varchar(256) NOT NULL UNIQUE,
    recipe_instructions varchar(2000) NOT NULL
);

-- Ingredient Table
CREATE TABLE ingredient (
    ingredient_id SERIAL PRIMARY KEY,
    ingredient_name varchar(200) NOT NULL UNIQUE
);

-- Meal Table
CREATE TABLE meal (
    meal_id SERIAL PRIMARY KEY,
    meal_name varchar(50) NOT NULL
);

-- Plan Table
CREATE TABLE plan (
    plan_id SERIAL PRIMARY KEY,
    plan_name varchar(100) NOT NULL UNIQUE,
    user_id int NOT NULL REFERENCES users(user_id),
    plan_date DATE NOT NULL
);

-- Type Table
CREATE TABLE type (
    type_id SERIAL PRIMARY KEY,
    type_name varchar(50) NOT NULL
);

-- Recipe Ingredient Table
CREATE TABLE recipe_ingredient (
    recipe_id int REFERENCES recipe(recipe_id),
    ingredient_id int REFERENCES ingredient(ingredient_id),
    PRIMARY KEY (recipe_id, ingredient_id)
);

-- Recipe Users Table
CREATE TABLE recipe_users (
    recipe_id int REFERENCES recipe(recipe_id),
    user_id int REFERENCES users(user_id),
    PRIMARY KEY (recipe_id, user_id)
);

-- User Meal Table
CREATE TABLE user_meal (
    meal_id int NOT NULL REFERENCES meal(meal_id),
    user_id int NOT NULL REFERENCES users(user_id)
);

-- Meal Recipe Table
CREATE TABLE meal_recipe (
    meal_id int NOT NULL REFERENCES meal(meal_id),
    recipe_id int NOT NULL REFERENCES recipe(recipe_id)
);

-- Plan Meal Table
CREATE TABLE plan_meal (
    plan_id int NOT NULL REFERENCES plan(plan_id),
    meal_id int NOT NULL REFERENCES meal(meal_id),
    meal_time varchar(50) NOT NULL,
    PRIMARY KEY (plan_id, meal_id, meal_time)
);

-- Meal Type Table
CREATE TABLE meal_type (
    meal_id int NOT NULL REFERENCES meal(meal_id),
    type_id int NOT NULL REFERENCES type(type_id)
);

-- Insert Meal Types
INSERT INTO type (type_name) VALUES
('Breakfast'),
('Lunch'),
('Dinner'),
('Appetizer'),
('Salad'),
('Main-course'),
('Side-dish'),
('Baked-goods'),
('Dessert'),
('Snack'),
('Soup'),
('Holiday'),
('Vegetarian Dishes');


COMMIT;
