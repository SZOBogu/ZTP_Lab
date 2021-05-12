DROP SCHEMA IF EXISTS `ztp`;

CREATE SCHEMA `ztp`;

use `ztp`;

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `title` varchar(250) DEFAULT NULL,
                         `author` varchar(50) DEFAULT NULL,
                         `year` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO books (title, author, year) VALUES ('101 asian dishes you need to cook out of beaver gums', 'Ainsley Harriot', 1995);
INSERT INTO books (title, author, year) VALUES ('Becoming a big guy (for you)', 'Bane', 2004);
INSERT INTO books (title, author, year) VALUES ('In defence of anime', 'Vladimir Putin', 2012);
INSERT INTO books (title, author, year) VALUES ('Crimes of Games Workshop, Vol I', 'Adam Bozek', 2018);
INSERT INTO books (title, author, year) VALUES ('How I ams becomes the greatest giuitars player', 'Skwisgaar Skwigelf ', 2007);

SELECT * FROM books;