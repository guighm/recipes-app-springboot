CREATE TABLE recipes_categories(
    id SERIAL NOT NULL PRIMARY KEY,
    recipe_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,

    FOREIGN KEY (recipe_id) references recipes(id),
    FOREIGN KEY (category_id) references categories(id)
);