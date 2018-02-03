insert into pricecategories (pricecategory_id, pricecategory_type) values (1, 'Children');
insert into pricecategories (pricecategory_id, pricecategory_type) values (2, 'NewRelease');
insert into pricecategories (pricecategory_id, pricecategory_type) values (3, 'Regular');

insert into movies (movie_id, movie_title, movie_rented, movie_releasedate, pricecategory_fk) values (1, 'Herr der Ringe', true, '2014-09-01', 3);
insert into movies (movie_id, movie_title, movie_rented, movie_releasedate, pricecategory_fk) values (2, 'Batman', true, '2015-08-09', 3);
insert into movies (movie_id, movie_title, movie_rented, movie_releasedate, pricecategory_fk) values (3, 'Mein Name ist Eugen', false, '2014-09-09', 1);
insert into movies (movie_id, movie_title, movie_rented, movie_releasedate, pricecategory_fk) values (4, 'Ronja Räubertochter', false, '2013-08-31', 1);