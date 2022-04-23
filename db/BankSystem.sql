create table if not exists accounts(
	id serial primary key,
	name varchar(255),
	password text,
	usersID int,
	amountOfMoney int,
	amountOfBitcoin numeric(5, 5)
);

SELECT * FROM accounts;
