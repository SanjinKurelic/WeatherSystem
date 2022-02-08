create table station (
  id bigint auto_increment primary key,
  name varchar(50) not null,
  model varchar(20) not null,
  model_version varchar(8) not null,
  postal_code int not null,
  city varchar(50) not null
);
