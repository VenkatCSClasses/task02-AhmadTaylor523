#d Bank Account Project

A Java application implementing a `BankAccount` class with comprehensive functionality for managing bank accounts, including deposits, withdrawals, transfers, and email validation.

## Project Structure

```
src/
├── main/java/
│   ├── BankAccount.java
│   └── InsufficientFundsException.java
└── test/java/
    └── BankAccountTests.java
```

## Features

### BankAccount Class

- **Account Creation**: Initialize an account with an email and starting balance
- **Email Validation**: Validates email addresses with custom rules
- **Balance Management**: 
  - Get current balance
  - Deposit money
  - Withdraw money
  - Transfer between accounts
- **Amount Validation**: Ensures amounts are valid (non-negative, max 2 decimal places)

### Key Methods

- `BankAccount(String email, double startingBalance)` - Create a new account
- `double getBalance()` - Get current account balance
- `String getEmail()` - Get account email
- `void deposit(double amount)` - Deposit money
- `void withdraw(double amount)` - Withdraw money
- `void transfer(BankAccount toAccount, double amount)` - Transfer to another account
- `static boolean isEmailValid(String email)` - Validate email format
- `static boolean isAmountValid(double amount)` - Validate amount

### Custom Exception

- `InsufficientFundsException` - Thrown when attempting to withdraw/transfer more than available balance

## Building and Running

### Prerequisites

- Java 17+
- Maven 3.8.9+

### Build

```bash
mvn clean compile
```

### Run Tests

```bash
mvn test
```

## Test Coverage

The project includes comprehensive unit tests covering:

- Balance retrieval
- Constructor validation
- Deposit operations
- Withdrawal operations
- Transfer operations
- Email validation
- Amount validation
- Edge cases and boundary conditions

Run tests with:

```bash
mvn test
```

## Validation Rules

### Email Validation

- Cannot be empty
- Must contain exactly one '@' symbol
- Cannot start with a dot
- Cannot contain '#' character
- Must have at least one character before '@'
- Must have a domain with at least one '.'
- Cannot end with a dot
- Cannot have consecutive dots

### Amount Validation

- Must be non-negative
- Must not be infinite or NaN
- Must have at most 2 decimal places

## Example Usage

```java
// Create accounts
BankAccount account1 = new BankAccount("user1@example.com", 1000.00);
BankAccount account2 = new BankAccount("user2@example.com", 500.00);

// Check balance
double balance = account1.getBalance(); // 1000.00

// Deposit money
account1.deposit(250.00); // balance: 1250.00

// Withdraw money
account1.withdraw(100.00); // balance: 1150.00

// Transfer between accounts
account1.transfer(account2, 300.00);
// account1 balance: 850.00
// account2 balance: 800.00
```

## Error Handling

The application throws appropriate exceptions:

- `IllegalArgumentException` - For invalid email, negative amounts, or amounts with more than 2 decimal places
- `InsufficientFundsException` - When attempting to withdraw/transfer more than available balance

## Author

Ahmad Taylor
