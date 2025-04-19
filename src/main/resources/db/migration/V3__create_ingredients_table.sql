CREATE TABLE ingredients (
    id SERIAL NOT NULL PRIMARY KEY,
    recipe_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(255) NOT NULL,

    FOREIGN KEY (recipe_id) references recipes(id) ON DELETE CASCADE
);