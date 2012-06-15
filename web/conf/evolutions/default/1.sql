# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table device (
  device_id                 varchar(255),
  device                    varchar(255),
  manufacturer              varchar(255),
  version                   varchar(255),
  brand                     varchar(255),
  model                     varchar(255),
  product                   varchar(255))
;

create table network (
  id                        integer not null,
  name                      varchar(255),
  quality                   double,
  mac                       varchar(255),
  constraint pk_network primary key (id))
;

create table sensor (
  id                        integer not null,
  constraint pk_sensor primary key (id))
;

create sequence network_seq;

create sequence sensor_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists device;

drop table if exists network;

drop table if exists sensor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists network_seq;

drop sequence if exists sensor_seq;

