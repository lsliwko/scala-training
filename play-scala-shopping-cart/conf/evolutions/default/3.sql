# --- Create shopping cart table

# --- !Ups

create table shoppingcart (
  id      bigint not null,
  items   varchar(255),
  constraint pk_shoppingcart primary key (id)
);

create sequence shoppingcart_seq start with 1000;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists shoppingcart;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists shoppingcart_seq;
