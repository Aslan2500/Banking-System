create table if not exists users(
	id serial primary key,
	name varchar(255),
	passport int
);

create table if not exists accounts(
	id serial primary key,
	password text,
	users_id int references users(id),
	amount_of_money int,
	amount_of_bitcoin numeric(7, 5)
);
