public class BankAccount {
    private String email;
    private double balance;

    /**
     * Constructor for BankAccount
     * @param email The email for the account
     * @param startingBalance The starting balance for the account
     * @throws IllegalArgumentException if email is invalid or startingBalance is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (!isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Invalid starting balance");
        }
        this.email = email;
        this.balance = startingBalance;
    }

    /**
     * Gets the current email
     * @return The email of this account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the current balance
     * @return The balance of this account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Withdraws money from the account
     * @param amount The amount to withdraw
     * @throws IllegalArgumentException if amount is invalid
     * @throws InsufficientFundsException if amount is greater than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance -= amount;
    }

    /**
     * Deposits money into the account
     * @param amount The amount to deposit
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit(double amount) {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        balance += amount;
    }

    /**
     * Transfers money from this account to another account
     * @param toAccount The account to transfer to
     * @param amount The amount to transfer
     * @throws IllegalArgumentException if toAccount is null or amount is invalid
     * @throws InsufficientFundsException if amount is greater than balance
     */
    public void transfer(BankAccount toAccount, double amount) throws InsufficientFundsException {
        if (toAccount == null) {
            throw new IllegalArgumentException("Destination account cannot be null");
        }
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.withdraw(amount);
        toAccount.deposit(amount);
    }

    /**
     * Validates if an email is valid
     * @param email The email to validate
     * @return true if email is valid, false otherwise
     */
    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Check if email starts with a dot
        if (email.startsWith(".")) {
            return false;
        }

        // Check for hash character
        if (email.contains("#")) {
            return false;
        }

        // Check for exactly one '@'
        int atCount = 0;
        int atIndex = -1;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                atCount++;
                atIndex = i;
            }
        }
        if (atCount != 1) {
            return false;
        }

        // Check at least one character before '@'
        if (atIndex == 0) {
            return false;
        }

        // Check for at least one '.' after '@'
        String domain = email.substring(atIndex + 1);
        if (!domain.contains(".")) {
            return false;
        }

        // Check '.' is not the final character
        if (email.endsWith(".")) {
            return false;
        }

        // Check for repeated special characters (consecutive dots or other special characters)
        if (email.contains("..")) {
            return false;
        }

        return true;
    }

    /**
     * Validates if an amount is valid
     * @param amount The amount to validate
     * @return true if amount is valid, false otherwise
     */
    public static boolean isAmountValid(double amount) {
        // Check if amount is finite
        if (!Double.isFinite(amount)) {
            return false;
        }

        // Check if amount is greater than or equal to 0
        if (amount < 0) {
            return false;
        }

        // Check if amount has no more than 2 decimal places
        double multiplied = amount * 100;
        return multiplied == Math.round(multiplied);
    }
}
