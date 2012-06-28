# --- !Ups

insert into location values(1, 0, 62.7, 24.7);
insert into location values(2, 0, 62, 24);
insert into sensor (location_id) values(1);
insert into sensor (location_id) values(2);

# --- !Downs

delete from sensor;
delete from location;