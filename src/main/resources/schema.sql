--DROP TABLE IF EXISTS category;
--DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS category(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    parentId INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    createDate DATETIME NOT NULL,
    categoryId INT NOT NULL,
    PRIMARY KEY (id),
    INDEX name (name),
    INDEx price (price),
    INDEX categoryId (categoryId)
);