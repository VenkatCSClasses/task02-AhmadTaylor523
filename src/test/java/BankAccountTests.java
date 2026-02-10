import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BankAccountTests {

    // getBalance tests
    @Test
    public void getBalance_typicalPositiveInteger() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertEquals(200.0, account.getBalance());
    }

    @Test
    public void getBalance_zeroBalance() {
        BankAccount account = new BankAccount("zero@bal.com", 0.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void getBalance_fractionalBalance() {
        BankAccount account = new BankAccount("frac@bal.com", 12.34);
        assertEquals(12.34, account.getBalance());
    }

    @Test
    public void getBalance_veryLargeBalance() {
        BankAccount account = new BankAccount("rich@bank.com", 1000000000.0);
        assertEquals(1000000000.0, account.getBalance());
    }

    // constructor tests
    @Test
    public void constructor_negativeStartingBalance_throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("neg@bal.com", -50.0);
        });
    }

    @Test
    public void constructor_valid_setsFields() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertEquals("a@b.com", account.getEmail());
        assertEquals(200.0, account.getBalance());
    }

    @Test
    public void constructor_invalidEmail_throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("", 100.0);
        });
    }

    @Test
    public void constructor_startingBalance_moreThan2Decimals_throws_1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("valid@mail.com", 100.110111);
        });
    }

    @Test
    public void constructor_startingBalance_moreThan2Decimals_throws_2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("valid@email.com", 100.001);
        });
    }

    @Test
    public void constructor_negativeStartingBalance_throws_2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("validemail2@gmail.com", -100.0);
        });
    }

    @Test
    public void constructor_negativeStartingBalance_throws_3() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("validemail12@gmail.com", -1.0);
        });
    }

    // withdraw tests
    @Test
    public void withdraw_validWithinBalance_reducesBalance() throws InsufficientFundsException {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        account.withdraw(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void withdraw_insufficientFunds_throws() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(300.0);
        });
    }

    @Test
    public void withdraw_equalToBalance_resultsZero() throws InsufficientFundsException {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        account.withdraw(200.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void withdraw_negativeAmount_throws() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
    }

    @Test
    public void withdraw_lessThanBalance_valid() throws InsufficientFundsException {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    public void withdraw_greaterThanBalance_throws() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(100.01);
        });
    }

    @Test
    public void withdraw_tooSmall_threeDecimals_throws() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0.001);
        });
    }

    @Test
    public void withdraw_boundary_justLessThanBalance() throws InsufficientFundsException {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        account.withdraw(99.99);
        assertEquals(0.01, account.getBalance(), 0.0001);
    }

    @Test
    public void withdraw_boundary_justGreaterThanBalance_throws() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(100.01);
        });
    }

    @Test
    public void withdraw_moreThanTwoDecimals_throws_1() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0.111);
        });
    }

    @Test
    public void withdraw_negative_throws_2() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-10.0);
        });
    }

    @Test
    public void withdraw_moreThanTwoDecimals_throws_3() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(2.11001);
        });
    }

    @Test
    public void withdraw_negative_throws_4() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-1.0);
        });
    }

    // isEmailValid tests
    @Test
    public void isEmailValid_validSimple() {
        assertTrue(BankAccount.isEmailValid("a@b.com"));
    }

    @Test
    public void isEmailValid_emptyString() {
        assertFalse(BankAccount.isEmailValid(""));
    }

    @Test
    public void isEmailValid_consecutivePeriodsDomain() {
        assertFalse(BankAccount.isEmailValid("ab@s..com"));
    }

    @Test
    public void isEmailValid_validHyphen() {
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
    }

    @Test
    public void isEmailValid_startsWithDot() {
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
    }

    @Test
    public void isEmailValid_containsHash() {
        assertFalse(BankAccount.isEmailValid("abc#def@h.com"));
    }

    @Test
    public void isEmailValid_missingAt() {
        assertFalse(BankAccount.isEmailValid("heywassup.com"));
    }

    @Test
    public void isEmailValid_doubleDotsMultiple() {
        assertFalse(BankAccount.isEmailValid("abc..def@mail..com"));
    }

    // isAmountValid tests
    @Test
    public void isAmountValid_valid() {
        assertTrue(BankAccount.isAmountValid(100.0));
    }

    @Test
    public void isAmountValid_zero() {
        assertTrue(BankAccount.isAmountValid(0.0));
    }

    @Test
    public void isAmountValid_negative() {
        assertFalse(BankAccount.isAmountValid(-50.0));
    }

    @Test
    public void isAmountValid_moreThan2Decimals() {
        assertFalse(BankAccount.isAmountValid(10.999));
    }

    // deposit tests
    @Test
    public void deposit_valid_increasesBalance() {
        BankAccount account = new BankAccount("test@example.com", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    public void deposit_moreThan2Decimals_throws() {
        BankAccount account = new BankAccount("test@example.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(10.999);
        });
    }

    @Test
    public void deposit_negative_throws() {
        BankAccount account = new BankAccount("test@example.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10.0);
        });
    }

    // transfer tests
    @Test
    public void transfer_valid_movesMoney() throws InsufficientFundsException {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        accountA.transfer(accountB, 25.0);
        assertEquals(75.0, accountA.getBalance());
        assertEquals(75.0, accountB.getBalance());
    }

    @Test
    public void transfer_nullDestination_throws() {
        BankAccount account = new BankAccount("testA@example.com", 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(null, 10.0);
        });
    }

    @Test
    public void transfer_moreThan2Decimals_throws() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        assertThrows(IllegalArgumentException.class, () -> {
            accountA.transfer(accountB, 15.999);
        });
    }

    @Test
    public void transfer_negative_throws() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        assertThrows(IllegalArgumentException.class, () -> {
            accountA.transfer(accountB, -15.0);
        });
    }

    @Test
    public void transfer_insufficientFunds_throws() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        assertThrows(InsufficientFundsException.class, () -> {
            accountA.transfer(accountB, 200.0);
        });
    }
}
