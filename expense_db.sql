DROP user expenseTracker;
DROP DATABASE expenseTrackerDb;
CREATE user expenseTracker with password 'password';
CREATE DATABASE expenseTrackerDb with template = template0 owner = expenseTracker;
\connect  expenseTrackerDb;
alter default privileges grant all on tables to expenseTracker;
alter default privileges grant all on sequences  to expenseTracker;

 CREATE table et_users(
 user_id integer primary key not null,
 first_name varchar(20) not null,
 last_name varchar(20) not null,
 email varchar(20) not null,
 password text not null,
 ):

CREATE table et_categories(
category_id integer primary key not null,
 user_id integer not null,
 title varchar(20) not null,
 description  varchar(20) not null,

);

alter table et_categories and constraint cat_users_fk
foreign key(user_id) references  et_users(user_id);

CREATE table et_transaction (
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric (10,2) not null,
note varchar(20) not null,
transaction_date bigint not null,
);

alter table et_transaction and constraint trans_cat_fk
foreign key(category_id) references  et_categories (category_id);
alter table et_transaction and constraint trans_cat_fk
foreign key(user_id) references  et_users(user_id);

CREATE sequences et_users_seq increment  1 start 1;
CREATE sequences et_categories_seq increment  1 start 1;
CREATE sequences et_transaction_seq increment  1 start 1000;