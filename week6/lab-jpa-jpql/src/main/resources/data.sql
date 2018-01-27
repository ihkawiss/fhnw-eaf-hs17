insert into USER (ID, EMAIL, FIRST_NAME, LAST_NAME) values(1, 'peter.muster@fhnw.ch', 'Peter', 'Muster');
insert into USER (ID, EMAIL, FIRST_NAME, LAST_NAME) values(2, 'juerg.luthiger@fhnw.ch', 'Juerg', 'Luhthiger');

insert into MOVIE (ID, TITLE, RENTED, RELEASE_DATE) values(1, 'Ex Machina', '1', '2016-07-14');
insert into MOVIE (ID, TITLE, RENTED, RELEASE_DATE) values(2, 'Paddington', '0', '2016-04-28');
insert into MOVIE (ID, TITLE, RENTED, RELEASE_DATE) values(3, 'Interstellar', '0', '2016-03-31');

insert into RENTAL (ID, RENTAL_DATE, RENTAL_DAYS, USER_ID, MOVIE_ID) values(1, '2015-10-22', 7, 1, 1);
insert into RENTAL (ID, RENTAL_DATE, RENTAL_DAYS, USER_ID, MOVIE_ID) values(2, '2015-10-22', 7, 1, 3);
