## Overview

The overal idea is to implement a BankAccount class that models a functioning bank account. Each instance of the class should have a numeric balance (float) and an account email (string). 

The class should have these 8 functions: 
Contstructor (set email and balance) 
getBalance: return current balane of object
getEmail: return object's email
withdraw: take away from object's current balance
deposit: add to object's balance
transfer: take money from one onject and give it to another object
isEmailValid: check if email is valid
isAmountValid:Check if amount is valid


Balance: Must always be >= 0

email must always be a valid email according to isEmailValid

##Validation Rules

##isAmountValid

An amount is valid if it is greate than 0, is a finite number

Non-numeric or null is invalid

##isEmailValid

An email is valid if it has exactly one '@', there is at least one character before the '@', it contains at least one "." after the '@', the "." isnt the final character, there isn't repeated special charcters

"getEmail"
public String getEmail()
Returns current email
no side effects

"Withdraw"
public void withdraw(double amount) throws InsufficientFundsException`
Withdraws money from the account

"Deposit"
public void deposit(double amount)
Deposits money into the account

"transfer"
public void transfer(BankAccount toAccount, double amount) throws InsufficientFundsException
Transfers money from this account to another account





## Example Scenarios 

##Constructor
new BankAccount("a@b.com", 100) 

new BankAccount("abc.com", 100) -> IllegalArgumentException (email invalid)



##withdraw 
Boy = 50 Boy.withdraw(10) -> Boy.balance = 40
Boy.withdraw(-1) -> IllegalArgumentException


##deposit
Apple = 500, Apple.deposit(10) -> Apple.balance = 50

##Transfer
A = 50, B = 10 A.transfer(B,20) -> A=30, B=30
A.transfer(null,5) -> IllegalArgumentException