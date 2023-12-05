# --- First database schema

# --- !Ups

create table item (
  id      bigint not null,
  name    varchar(255) not null,
  constraint pk_item primary key (id)
);

create sequence item_seq start with 1000;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists item;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists item_seq;

