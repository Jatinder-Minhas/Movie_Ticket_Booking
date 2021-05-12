CREATE TABLE RegisteredUser
(
		userId BIGINT NOT NULL Primary key AUTO_INCREMENT,
		firstName VARCHAR(20) NOT NULL,
		lastName VARCHAR(20) NOT NULL,
		userName VARCHAR(36) NOT NULL UNIQUE,
		college VARCHAR(36),
		age numeric(3) not null,
		encryptedPassword VARCHAR(128) NOT NULL,
		ENABLED BIT NOT NULL
);

create table MOVIETICKETS
(
		movieId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		imgPath VARCHAR(255) not null,
		movieName VARCHAR(36) NOT NULL, 
		ticket_count NUMERIC(2) NOT NULL
);

create table movies_dates
(
		id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		movieId BIGINT NOT NULL,
		movieDate DATETIME NOT NULL
		
);

create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;


create table USER_ROLE
(
  ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

 
ALTER TABLE USER_ROLE
ADD FOREIGN KEY (userId) REFERENCES RegisteredUser(userId);

ALTER TABLE movies_dates
ADD FOREIGN KEY (movieId) REFERENCES MOVIETICKETS(movieId);




insert into RegisteredUser ( firstName, lastName, userName, age,encryptedPassword, ENABLED)
values ('Jatinder', 'Minhas', 'minhasJatinder', 45,'$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into sec_role (roleName)
values ('ROLE_ADMIN');

insert into sec_role (roleName)
values ('ROLE_USER');
 
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (1, 2);


insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('TheHungerGames', '/images/thehungergames.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (1, '2020-10-20 5:30:00');

insert into movies_dates (movieId, movieDate) 
values (1, '2020-10-20 1:00'); 

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('HarryPotter', '/images/harrypotter.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (2, '2020-10-20 8:00');

insert into movies_dates (movieId, movieDate) 
values (2, '2020-10-20 9:30');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Divergent', '/images/divergent.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (3, '2020-10-20 7:30');

insert into movies_dates (movieId, movieDate) 
values (3, '2020-10-20 10:30');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Inception', '/images/inception.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (4, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (4, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Fury', '/images/fury.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (5, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (5, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Dunkirt', '/images/dunkirt.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (6, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (6, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Breaking Bad', '/images/breakingbad.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (7, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (7, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Interstellar', '/images/interstellar.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (8, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (8, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Shutter Island', '/images/shutterisland.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (9, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (9, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Frozen', '/images/frozen.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (10, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (10, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Jumanji', '/images/jumanji.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (11, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (11, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Matrix: Reloaded', '/images/matrixreloaded.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (12, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (12, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('The Lion King', '/images/thelionking.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (13, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (13, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Finding Nemo', '/images/findingnemo.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (14, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (14, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Toy Story', '/images/toystory.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (15, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (15, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Alita: The Battle Angle', '/images/alitathebattleangle.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (16, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (16, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Tenet', '/images/tenet.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (17, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (17, '2020-10-20 4:00');

insert into MOVIETICKETS (movieName, imgPath, ticket_count)
values ('Black Panther', '/images/blackpanther.jpg', 47);

insert into movies_dates (movieId, movieDate) 
values (18, '2020-10-20 12:00');

insert into movies_dates (movieId, movieDate) 
values (18, '2020-10-20 4:00');

COMMIT;