
DROP DATABASE testdb;
CREATE DATABASE testdb;
USE testdb;

CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(12) UNIQUE NOT NULL
);

INSERT INTO users VALUES (2, 'admin');
INSERT INTO users VALUES (DEFAULT, 'client');
INSERT INTO users (login) VALUES ('client2');

SELECT * from users;

/*

GRANT ALL PRIVILEGES ON testdb.* TO testuser@localhost IDENTIFIED BY 'testpassword';


*/
