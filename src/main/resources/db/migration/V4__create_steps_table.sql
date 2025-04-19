CREATE TABLE steps (
    id SERIAL NOT NULL PRIMARY KEY,
    recipe_id INTEGER NOT NULL,
    step_number INTEGER NOT NULL,
    description TEXT NOT NULL,

    FOREIGN KEY (recipe_id) references recipes(id) ON DELETE CASCADE
);