
INSERT INTO users (username,password_hash,role) VALUES ('DemoUser','$2a$10$K/fkp7IQ5HxTPGGHDXEiaeDVHxI5fQlqAlbyJatJUUKVl/Zxetw5q','ROLE_USER');

INSERT INTO ingredient(ingredient_name) VALUES
('6 Cobs of corn'),
('2 lbs Yukon Gold Potatoes'),
('1 Carrot'),
('1 Onion'),
('2 Sticks of Celery'),
('6 Slices thick cut bacon'),
('6 cups chicken broth'),
('2 cups of 2% milk'),
('Ceyenne pepper'),
('1 small loaf of french bread'),
('4 tbsp of butter'),
('Old Bay seasoning'),
('2 cloves of garlic'),
('1 chili pepper'),
('2 scallions'),
('10 ounces grape tomatoes'),
('1 lemon'),
('10 ounces tagliatelle pasta'),
('4 tbsp sour cream'),
('10 ounce shrimp'),
('1 tsp olive oil'),
('2 tbsp butter'),
('4 oz kale'),
('9 oz chicken sausage'),
('1 loaf ciabatta bread'),
('12 cups chicken stock'),
('1 oz parmesan cheese'),
('2 1/2 oz couscous');

INSERT INTO recipe(recipe_name, recipe_instructions) VALUES
('Corn Chowder', 'Sautee bacon in a dutch oven until crisps and renders fat. Remove bacon to a plate. Sautee veggies (onion, celery, and carrot) in the bacon grease until softened. Add potatoes and corn kernels and season with salt, pepper, and cayenne pepper. Add chicken stock into the pot and simmer until potatoes are tender. Serve in warm bowls garnished with bacon and chives.'),
('Old Bay Toast', 'Pre-heat oven to 425 degrees f. Let butter sit until room temp. Cut french bread in half length-wise.Then combine in a bowl with a couple tsp of Old Bay seasoning to taste, 2 tsp is typically fine. Spread butter onto french bread, then put into the oven on the top rack to toast. 2-5 minutes.'),
('Shrimp Tagliatelle','Wash and dry all produce. Bring a large pot of salted water to a boil. Mince garlic. Trim, then thinly slice scallions, keeping greens and whites separate. Finely mince chili, removing seeds and ribs for less heat. Halve tomatoes. Cut lemon into wedges. Rinse shrimp and pat dry with a paper towel. Heat a drizzle of olive oil in large pan over medium-high heat. Add garlic, scallion whites, and chili (to taste). Cook until fragrant, about 30 seconds. Add shrimp and cook, tossing, until starting to turn pink but not quite cooked through, 1-2 minutes. Season with salt and pepper. Once water is boiling, add tagliatelle to pot. (TIP: If any noodles are stuck together, separate them first.) Cook, stirring occasionally, until al dente, 4-5 minutes. Carefully scoop out and reserve ¼ cup pasta cooking water, then drain. Meanwhile, add tomatoes to pan with shrimp. Cook, tossing, until wilted and juicy, 2-3 minutes. Season with salt and pepper. Remove from heat and set aside until pasta is ready. TIP: If you like it extra hot, add any remaining chili (to taste) at this point. Remove pan from heat and stir in sour cream, a squeeze of lemon, and as much pasta cooking water as needed to reach a saucy consistency. Season with salt and pepper. Divide between plates or bowls and garnish with scallion greens. Serve with lemon wedges on the side for squeezing over.'),
('Chicken sausage and Kale soup',' Bring 1 TBSP butter (2 TBSP for 4 servings) to room temperature. Wash and dry produce. • Trim, peel, and cut carrot into a small dice. Halve, peel, and finely chop onion. Remove and discard any large stems from kale. Peel and thinly slice garlic.  Add garlic and half the Italian Seasoning (all for 4 servings) to pot. Cook, stirring, until fragrant, 1 minute. Stir half the Parmesan into soup until melted. Season with plenty of salt and pepper. • Divide soup between bowls and sprinkle with remaining Parmesan. Serve with garlic toasts on the side.');

INSERT INTO recipe_ingredient(recipe_id, ingredient_id) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(2,10),
(2,11),
(2,12),
(3,13),
(3,14),
(3,15),
(3,16),
(3,17),
(3,18),
(3,19),
(3,20),
(3,21),
(3,22),
(4,3),
(4,4),
(4,23),
(4,24),
(4,25),
(4,26),
(4,27),
(4,28);


INSERT INTO recipe_users(recipe_id, user_id) VALUES
(1,3),
(2,3);
(3,3);
(4,3);

INSERT INTO meal(meal_name) VALUES
('Corn chowder with Old Bay toast'),
('Shrimp Tagliatelle with soup');

INSERT INTO meal_type(meal_id, type_id) VALUES
(1,3),
(2,2);

INSERT INTO user_meal(meal_id, user_id) VALUES
(1,3),
(2,3);

INSERT INTO meal_recipe(meal_id, recipe_id) VALUES
(1,1),
(1,2),
(2,3),
(2,4);

INSERT INTO daily_plan(daily_plan_name, dayofplan, user_id) VALUES
('Chowder for dinner', '2023-12-16', 3),
('Pasta and soup for lunch', '2023-12-17', 3);

INSERT INTO daily_plan_meals(daily_plan_id,meal_id,meal_time) VALUES
('1','1', '05:00:00'),
(2,2,'12:00:00');