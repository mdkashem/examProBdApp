drop table if exists account;

create table account(
  accountId SERIAL primary key,
  balance Decimal not null,
  created_on DATE NOT NULL DEFAULT CURRENT_DATE,  
  statusId INT REFERENCES account_status (statusId),
  typeId INT REFERENCES account_type (typeId)
);