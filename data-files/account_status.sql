drop table if exists account_status;

create table account_status(
  statusId SERIAL primary key,
  status varchar (100) NOT NULL
 
);