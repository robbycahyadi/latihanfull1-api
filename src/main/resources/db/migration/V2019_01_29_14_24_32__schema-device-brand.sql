create table device_brand (
  id          varchar(64) not null primary key,
  name        varchar(150) not null,
  code        varchar(100) not null,
  description text
) engine = InnoDB;
