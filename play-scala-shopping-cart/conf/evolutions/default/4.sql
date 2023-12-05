# --- Populate shopping cart table

# --- !Ups

insert into shoppingcart (id,items) values (1, null);
insert into shoppingcart (id,items) values (2, '1,2');
insert into shoppingcart (id,items) values (3, '2');

# --- !Downs

delete from shoppingcart where id in (1,2,3);
