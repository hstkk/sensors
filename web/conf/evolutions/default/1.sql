# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table device (
  id                        integer auto_increment not null,
  device_id                 varchar(255),
  manufacturer              varchar(255),
  version                   varchar(255),
  brand                     varchar(255),
  model                     varchar(255),
  constraint pk_device primary key (id))
;

create table location (
  id                        integer auto_increment not null,
  altitude                  double,
  latitude                  double,
  longitude                 double,
  constraint pk_location primary key (id))
;

create table network (
  id                        integer auto_increment not null,
  operator                  varchar(255),
  type                      varchar(255),
  is_network_roaming        tinyint(1) default 0,
  cell                      integer,
  constraint pk_network primary key (id))
;

create table sensor (
  id                        integer auto_increment not null,
  created                   datetime,
  location_id               integer,
  network_id                integer,
  device_id                 integer,
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



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table device;

drop table location;

drop table network;

drop table sensor;

drop table wifi;

SET FOREIGN_KEY_CHECKS=1;

