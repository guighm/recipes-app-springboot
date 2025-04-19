CREATE TABLE recipes (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    preparation_time INTEGER NOT NULL,
    servings INTEGER NOT NULL,
    difficulty VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(3) NOT NULL,

    FOREIGN KEY (user_id) references users(id) ON DELETE CASCADE
);