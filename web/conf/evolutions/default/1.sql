# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table accelerometer (
  id                        integer auto_increment not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_accelerometer primary key (id))
;

create table device (
  id                        integer auto_increment not null,
  manufacturer              varchar(255),
  version                   varchar(255),
  brand                     varchar(255),
  model                     varchar(255),
  constraint pk_device primary key (id))
;

create table gravity (
  id                        integer auto_increment not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_gravity primary key (id))
;

create table gyroscope (
  id                        integer auto_increment not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_gyroscope primary key (id))
;

create table light (
  id                        integer auto_increment not null,
  x                         float,
  constraint pk_light primary key (id))
;

create table location (
  id                        integer auto_increment not null,
  altitude                  double,
  latitude                  double,
  longitude                 double,
  accuracy                  float,
  speed                     float,
  provider                  varchar(255),
  satellites                integer,
  constraint pk_location primary key (id))
;

create table magnetic_field (
  id                        integer auto_increment not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_magnetic_field primary key (id))
;

create table network (
  id                        integer auto_increment not null,
  operator                  varchar(255),
  technology                varchar(255),
  is_network_roaming        tinyint(1) default 0,
  cell                      integer,
  constraint pk_network primary key (id))
;

create table proximity (
  id                        integer auto_increment not null,
  x                         float,
  constraint pk_proximity primary key (id))
;

create table sensor (
  id                        integer auto_increment not null,
  measured                  datetime not null,
  location_id               integer,
  network_id                integer,
  device_id                 integer,
  accelerometer_id          integer,
  proximity_id              integer,
  gravity_id                integer,
  gyroscope_id              integer,
  light_id                  integer,
  magfield_id               integer,
  constraint pk_sensor primary key (id))
;

create table wifi (
  id                        integer auto_increment not null,
  bssid                     varchar(255),
  ssid                      varchar(255),
  capabilities              varchar(255),
  frequency                 integer,
  level                     integer,
  constraint pk_wifi primary key (id))
;

alter table sensor add constraint fk_sensor_location_1 foreign key (location_id) references location (id) on delete restrict on update restrict;
create index ix_sensor_location_1 on sensor (location_id);
alter table sensor add constraint fk_sensor_network_2 foreign key (network_id) references network (id) on delete restrict on update restrict;
create index ix_sensor_network_2 on sensor (network_id);
alter table sensor add constraint fk_sensor_device_3 foreign key (device_id) references device (id) on delete restrict on update restrict;
create index ix_sensor_device_3 on sensor (device_id);
alter table sensor add constraint fk_sensor_accelerometer_4 foreign key (accelerometer_id) references accelerometer (id) on delete restrict on update restrict;
create index ix_sensor_accelerometer_4 on sensor (accelerometer_id);
alter table sensor add constraint fk_sensor_proximity_5 foreign key (proximity_id) references proximity (id) on delete restrict on update restrict;
create index ix_sensor_proximity_5 on sensor (proximity_id);
alter table sensor add constraint fk_sensor_gravity_6 foreign key (gravity_id) references gravity (id) on delete restrict on update restrict;
create index ix_sensor_gravity_6 on sensor (gravity_id);
alter table sensor add constraint fk_sensor_gyroscope_7 foreign key (gyroscope_id) references gyroscope (id) on delete restrict on update restrict;
create index ix_sensor_gyroscope_7 on sensor (gyroscope_id);
alter table sensor add constraint fk_sensor_light_8 foreign key (light_id) references light (id) on delete restrict on update restrict;
create index ix_sensor_light_8 on sensor (light_id);
alter table sensor add constraint fk_sensor_magfield_9 foreign key (magfield_id) references magnetic_field (id) on delete restrict on update restrict;
create index ix_sensor_magfield_9 on sensor (magfield_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table accelerometer;

drop table device;

drop table gravity;

drop table gyroscope;

drop table light;

drop table location;

drop table magnetic_field;

drop table network;

drop table proximity;

drop table sensor;

drop table wifi;

SET FOREIGN_KEY_CHECKS=1;

