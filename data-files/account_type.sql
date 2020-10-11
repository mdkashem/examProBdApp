drop table if exists account_type;

create table account_type(
  typeId SERIAL primary key,
  accountType varchar (100) NOT NULL
 
);