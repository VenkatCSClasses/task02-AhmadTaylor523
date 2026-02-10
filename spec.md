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

## Validation Rules

public static boolean isAmountValid(double amount)

Return True if:
Double.isfinite(amount) is true, 
amount > 0 
The number has no more than 2 decimal places 
Otherwise return false 

public static boolean isEmailValid (string email)

An email is valid if it has exactly one '@', there is at least one character before the '@', it contains at least one "." after the '@', the "." isnt the final character, there isn't repeated special charcters

## The 8 functions 

*Constructor*
public BankAccount(String email, double startingBalance)

//Creates a new Account
In order to do this it has to check isEmailValid(email) and isAmountValid(startingBalance). If these are bother true, then this.email = email, and this.balanace = startingBalance

//IMPORTANT: if either one is false, then it will not create an object. 

*getEmail*
public String getEmail()
Returns current email
no side effects

*Withdraw*
public void withdraw(double amount) throws InsufficientFundsException`
Withdraws money from the account

// It has to check isAmountValid(amount) in order to possibly do this. If the amount is a valid number but greater than the account balance, it will throw a insufficient funds exception. If no exception thrown, the balance will decrease by the amount. 


*Deposit*
public void deposit(double amount)
Deposits money into the account

//This just does an isAmountValid(amount) to make sure it is a real number that someone can deposit. If it is balance += amount. If not it will throw illegalArgumentException


*transfer*
public void transfer(BankAccount toAccount, double amount) throws InsufficientFundsException
Transfers money from this account to another account

In order to do this it checks both : is Account null, isAmountValid just to try to withdraw. Once you get to withdraw it has it's own checks that could throw illegal argument exceptions. IF NONE are thrown, then it will withdraw from account 1 and deposit into account 2. 

*isEmailValid* 
-described above 

*isAmountValid* 
-described above 


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