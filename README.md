# Banking-System
This program represents small Banking System with several options

Program connected with PostgreSQL database, so each created account saves into the database

Current Bitcoin price parsed via https://api.coindesk.com/v1/bpi/currentprice.json

Also, database include all information about account - password, users name, 
users ID (Passport number and series), amount of money on balance and amount of Bitcoin

How user see program:
Firstly, program offer customer to enter account or create new account if he/she doesn't have one
These options requires - name, ID and password. 

Then, program offer following options:
1) Deposit money - program asks how much money you want to deposit
2) Withdraw money - program asks how much money you want to withdraw (if you don't have enough money then nothing happens)
3) Buy cryptocurrency - program asks how much money you want to spend from your account, Bitcoin price parsed automatically
4) Sell cryptocurrency - program asks how much crypto you want to sell, Bitcoin price parsed automatically
5) Show balance - program shows how much money and Bitcoin you have on your account
6) Exit - finishes program

You can see Flowchart in order to see process is made. [Flowachar](Flowchart.jpg)
