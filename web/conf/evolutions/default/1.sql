# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table network (
  id                        integer not null,
  name                      varchar(255),
  quality                   double,
  mac                       varchar(255),
  constraint pk_network primary key (id))
;

create sequence network_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists network;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists network_seq;

