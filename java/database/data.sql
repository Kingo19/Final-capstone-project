BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO recipe (recipe_name, recipe_instructions) VALUES
('Chocolate Cake', 'Mix ingredients and bake for 30 minutes.'),
('Pasta Carbonara', 'Cook pasta, fry bacon, mix with eggs and cheese.'),
('Grilled Cheese Sandwich', 'Butter bread, add cheese, and grill.');

INSERT INTO ingredient (ingredient_name) VALUES
('Flour'),
('Sugar'),
('Eggs'),
('Butter'),
('Bacon'),
('Pasta'),
('Cheese'),
('Bread');

-- Assuming the IDs for Chocolate Cake are 1, Flour is 1, Sugar is 2, Eggs is 3
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES
(1, 1), -- Chocolate Cake includes Flour
(1, 2), -- Chocolate Cake includes Sugar
(1, 3); -- Chocolate Cake includes Eggs

-- Assuming the IDs for Pasta Carbonara are 2, Pasta is 6, Bacon is 5, Eggs is 3, Cheese is 7
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES
(2, 6), -- Pasta Carbonara includes Pasta
(2, 5), -- Pasta Carbonara includes Bacon
(2, 3), -- Pasta Carbonara includes Eggs
(2, 7); -- Pasta Carbonara includes Cheese


-- Assuming the IDs for Grilled Cheese Sandwich are 3, Bread is 8, Butter is 4, Cheese is 7
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES
(3, 8), -- Grilled Cheese Sandwich includes Bread
(3, 4), -- Grilled Cheese Sandwich includes Butter
(3, 7); -- Grilled Cheese Sandwich includes Cheese

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

--INSERT INTO weekday (weekday) VALUES ('Sunday');
--
--INSERT INTO weekday (weekday) VALUES ('Monday');
--
--INSERT INTO weekday (weekday) VALUES ('Tuesday');
--
--INSERT INTO weekday (weekday) VALUES ('Wednesday');
--
--INSERT INTO weekday (weekday) VALUES ('Thursday');
--
--INSERT INTO weekday (weekday) VALUES ('Friday');
--
--INSERT INTO weekday (weekday) VALUES ('Saturday');


COMMIT TRANSACTION;
