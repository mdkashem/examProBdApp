drop table if exists Role;

create table Role (
  roleId SERIAL  primary key,
  role varchar (100) NOT NULL
 
);
