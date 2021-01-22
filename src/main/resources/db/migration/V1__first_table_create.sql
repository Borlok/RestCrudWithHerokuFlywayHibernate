CREATE TABLE if not exists customers
(
    customer_id serial PRIMARY KEY
);

create table if not exists specialty_list
(
    specialty_id serial PRIMARY KEY,
    specialty    varchar(50) not null unique
);

CREATE TABLE if not exists accounts
(
    id             serial PRIMARY KEY,
    customer_id    int REFERENCES customers (customer_id) on delete cascade,
    name           varchar(60) DEFAULT NULL,
    account_status varchar(50) DEFAULT NULL
);

create table if not exists specialties
(
    customer_id  int references customers (customer_id) on delete cascade,
    specialty_id int references specialty_list (specialty_id) on delete cascade,
    primary key (customer_id, specialty_id)
);
