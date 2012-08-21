# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table accelerometer (
  id                        integer not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_accelerometer primary key (id))
;

create table device (
  id                        integer not null,
  manufacturer              varchar(255),
  version                   varchar(255),
  brand                     varchar(255),
  model                     varchar(255),
  constraint pk_device primary key (id))
;

create table gravity (
  id                        integer not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_gravity primary key (id))
;

create table gyroscope (
  id                        integer not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_gyroscope primary key (id))
;

create table light (
  id                        integer not null,
  x                         float,
  constraint pk_light primary key (id))
;

create table location (
  id                        integer not null,
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
  id                        integer not null,
  x                         float,
  y                         float,
  z                         float,
  constraint pk_magnetic_field primary key (id))
;

create table network (
  id                        integer not null,
  operator                  varchar(255),
  technology                varchar(255),
  is_network_roaming        boolean,
  cell                      integer,
  constraint pk_network primary key (id))
;

create table proximity (
  id                        integer not null,
  x                         float,
  constraint pk_proximity primary key (id))
;

create table sensor (
  id                        integer not null,
  measured                  timestamp not null,
  location_id               integer,
  network_id                integer,
  device_id                 integer not null,
  accelerometer_id          integer,
  proximity_id              integer,
  gravity_id                integer,
  gyroscope_id              integer,
  light_id                  integer,
  magfield_id               integer,
  constraint pk_sensor primary key (id))
;

create table wifi (
  id                        integer not null,
  sensor_id                 integer not null,
  bssid                     varchar(255),
  ssid                      varchar(255),
  capabilities              varchar(255),
  frequency                 integer,
  level                     integer,
  constraint pk_wifi primary key (id))
;

create sequence accelerometer_seq;

create sequence device_seq;

create sequence gravity_seq;

create sequence gyroscope_seq;

create sequence light_seq;

create sequence location_seq;

create sequence magnetic_field_seq;

create sequence network_seq;

create sequence proximity_seq;

create sequence sensor_seq;

create sequence wifi_seq;

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
alter table wifi add constraint fk_wifi_sensor_10 foreign key (sensor_id) references sensor (id) on delete restrict on update restrict;
create index ix_wifi_sensor_10 on wifi (sensor_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists accelerometer;

drop table if exists device;

drop table if exists gravity;

drop table if exists gyroscope;

drop table if exists light;

drop table if exists location;

drop table if exists magnetic_field;

drop table if exists network;

drop table if exists proximity;

drop table if exists sensor;

drop table if exists wifi;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists accelerometer_seq;

drop sequence if exists device_seq;

drop sequence if exists gravity_seq;

drop sequence if exists gyroscope_seq;

drop sequence if exists light_seq;

drop sequence if exists location_seq;

drop sequence if exists magnetic_field_seq;

drop sequence if exists network_seq;

drop sequence if exists proximity_seq;

drop sequence if exists sensor_seq;

drop sequence if exists wifi_seq;

