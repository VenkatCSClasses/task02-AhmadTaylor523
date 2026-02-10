public class BankAccountTests {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        // getBalance tests
        testGetBalance_typicalPositiveInteger();
        testGetBalance_zeroBalance();
        testGetBalance_fractionalBalance();
        testGetBalance_veryLargeBalance();

        // constructor tests
        testConstructor_negativeStartingBalance_throws();
        testConstructor_valid_setsFields();
        testConstructor_invalidEmail_throws();
        testConstructor_startingBalance_moreThan2Decimals_throws_1();
        testConstructor_startingBalance_moreThan2Decimals_throws_2();
        testConstructor_negativeStartingBalance_throws_2();
        testConstructor_negativeStartingBalance_throws_3();

        // withdraw tests
        testWithdraw_validWithinBalance_reducesBalance();
        testWithdraw_insufficientFunds_throws();
        testWithdraw_equalToBalance_resultsZero();
        testWithdraw_negativeAmount_throws();
        testWithdraw_lessThanBalance_valid();
        testWithdraw_greaterThanBalance_throws();
        testWithdraw_tooSmall_threeDecimals_throws();
        testWithdraw_boundary_justLessThanBalance();
        testWithdraw_boundary_justGreaterThanBalance_throws();
        testWithdraw_moreThanTwoDecimals_throws_1();
        testWithdraw_negative_throws_2();
        testWithdraw_moreThanTwoDecimals_throws_3();
        testWithdraw_negative_throws_4();

        // isEmailValid tests
        testIsEmailValid_validSimple();
        testIsEmailValid_emptyString();
        testIsEmailValid_consecutivePeriodsDomain();
        testIsEmailValid_validHyphen();
        testIsEmailValid_startsWithDot();
        testIsEmailValid_containsHash();
        testIsEmailValid_missingAt();
        testIsEmailValid_doubleDotsMultiple();

        // isAmountValid tests
        testIsAmountValid_valid();
        testIsAmountValid_zero();
        testIsAmountValid_negative();
        testIsAmountValid_moreThan2Decimals();

        // deposit tests
        testDeposit_valid_increasesBalance();
        testDeposit_moreThan2Decimals_throws();
        testDeposit_negative_throws();

        // transfer tests
        testTransfer_valid_movesMoney();
        testTransfer_nullDestination_throws();
        testTransfer_moreThan2Decimals_throws();
        testTransfer_negative_throws();
        testTransfer_insufficientFunds_throws();

        // Print summary
        System.out.println("\n========== TEST SUMMARY ==========");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
    }

    // Helper method for assertions
    private static void assertTrue(String testName, boolean condition) {
        if (condition) {
            testsPassed++;
            System.out.println("✓ " + testName);
        } else {
            testsFailed++;
            System.out.println("✗ " + testName);
        }
    }

    private static void assertThrowsException(String testName, TestRunner runner, Class<?> exceptionType) {
        try {
            runner.run();
            testsFailed++;
            System.out.println("✗ " + testName + " (no exception thrown)");
        } catch (Exception e) {
            if (exceptionType.isInstance(e)) {
                testsPassed++;
                System.out.println("✓ " + testName);
            } else {
                testsFailed++;
                System.out.println("✗ " + testName + " (wrong exception: " + e.getClass().getName() + ")");
            }
        }
    }

    private static void assertEquals(String testName, double expected, double actual) {
        if (Math.abs(expected - actual) < 0.0001) {
            testsPassed++;
            System.out.println("✓ " + testName);
        } else {
            testsFailed++;
            System.out.println("✗ " + testName + " (expected: " + expected + ", got: " + actual + ")");
        }
    }

    private static void assertEquals(String testName, String expected, String actual) {
        if (expected.equals(actual)) {
            testsPassed++;
            System.out.println("✓ " + testName);
        } else {
            testsFailed++;
            System.out.println("✗ " + testName + " (expected: " + expected + ", got: " + actual + ")");
        }
    }

    // getBalance tests
    private static void testGetBalance_typicalPositiveInteger() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertEquals("getBalance_typicalPositiveInteger", 200.0, account.getBalance());
    }

    private static void testGetBalance_zeroBalance() {
        BankAccount account = new BankAccount("zero@bal.com", 0.0);
        assertEquals("getBalance_zeroBalance", 0.0, account.getBalance());
    }

    private static void testGetBalance_fractionalBalance() {
        BankAccount account = new BankAccount("frac@bal.com", 12.34);
        assertEquals("getBalance_fractionalBalance", 12.34, account.getBalance());
    }

    private static void testGetBalance_veryLargeBalance() {
        BankAccount account = new BankAccount("rich@bank.com", 1000000000.0);
        assertEquals("getBalance_veryLargeBalance", 1000000000.0, account.getBalance());
    }

    // constructor tests
    private static void testConstructor_negativeStartingBalance_throws() {
        assertThrowsException("constructor_negativeStartingBalance_throws", () -> {
            new BankAccount("neg@bal.com", -50.0);
        }, IllegalArgumentException.class);
    }

    private static void testConstructor_valid_setsFields() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertEquals("constructor_valid_setsFields_email", "a@b.com", account.getEmail());
        assertEquals("constructor_valid_setsFields_balance", 200.0, account.getBalance());
    }

    private static void testConstructor_invalidEmail_throws() {
        assertThrowsException("constructor_invalidEmail_throws", () -> {
            new BankAccount("", 100.0);
        }, IllegalArgumentException.class);
    }

    private static void testConstructor_startingBalance_moreThan2Decimals_throws_1() {
        assertThrowsException("constructor_startingBalance_moreThan2Decimals_throws_1", () -> {
            new BankAccount("valid@mail.com", 100.110111);
        }, IllegalArgumentException.class);
    }

    private static void testConstructor_startingBalance_moreThan2Decimals_throws_2() {
        assertThrowsException("constructor_startingBalance_moreThan2Decimals_throws_2", () -> {
            new BankAccount("valid@email.com", 100.001);
        }, IllegalArgumentException.class);
    }

    private static void testConstructor_negativeStartingBalance_throws_2() {
        assertThrowsException("constructor_negativeStartingBalance_throws_2", () -> {
            new BankAccount("validemail2@gmail.com", -100.0);
        }, IllegalArgumentException.class);
    }

    private static void testConstructor_negativeStartingBalance_throws_3() {
        assertThrowsException("constructor_negativeStartingBalance_throws_3", () -> {
            new BankAccount("validemail12@gmail.com", -1.0);
        }, IllegalArgumentException.class);
    }

    // withdraw tests
    private static void testWithdraw_validWithinBalance_reducesBalance() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        try {
            account.withdraw(100.0);
            assertEquals("withdraw_validWithinBalance_reducesBalance", 100.0, account.getBalance());
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ withdraw_validWithinBalance_reducesBalance (" + e.getMessage() + ")");
        }
    }

    private static void testWithdraw_insufficientFunds_throws() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertThrowsException("withdraw_insufficientFunds_throws", () -> {
            account.withdraw(300.0);
        }, InsufficientFundsException.class);
    }

    private static void testWithdraw_equalToBalance_resultsZero() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        try {
            account.withdraw(200.0);
            assertEquals("withdraw_equalToBalance_resultsZero", 0.0, account.getBalance());
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ withdraw_equalToBalance_resultsZero (" + e.getMessage() + ")");
        }
    }

    private static void testWithdraw_negativeAmount_throws() {
        BankAccount account = new BankAccount("a@b.com", 200.0);
        assertThrowsException("withdraw_negativeAmount_throws", () -> {
            account.withdraw(-50.0);
        }, IllegalArgumentException.class);
    }

    private static void testWithdraw_lessThanBalance_valid() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        try {
            account.withdraw(50.0);
            assertEquals("withdraw_lessThanBalance_valid", 50.0, account.getBalance());
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ withdraw_lessThanBalance_valid (" + e.getMessage() + ")");
        }
    }

    private static void testWithdraw_greaterThanBalance_throws() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        assertThrowsException("withdraw_greaterThanBalance_throws", () -> {
            account.withdraw(100.01);
        }, InsufficientFundsException.class);
    }

    private static void testWithdraw_tooSmall_threeDecimals_throws() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        assertThrowsException("withdraw_tooSmall_threeDecimals_throws", () -> {
            account.withdraw(0.001);
        }, IllegalArgumentException.class);
    }

    private static void testWithdraw_boundary_justLessThanBalance() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        try {
            account.withdraw(99.99);
            assertEquals("withdraw_boundary_justLessThanBalance", 0.01, account.getBalance());
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ withdraw_boundary_justLessThanBalance (" + e.getMessage() + ")");
        }
    }

    private static void testWithdraw_boundary_justGreaterThanBalance_throws() {
        BankAccount account = new BankAccount("c@d.com", 100.0);
        assertThrowsException("withdraw_boundary_justGreaterThanBalance_throws", () -> {
            account.withdraw(100.01);
        }, InsufficientFundsException.class);
    }

    private static void testWithdraw_moreThanTwoDecimals_throws_1() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrowsException("withdraw_moreThanTwoDecimals_throws_1", () -> {
            account.withdraw(0.111);
        }, IllegalArgumentException.class);
    }

    private static void testWithdraw_negative_throws_2() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrowsException("withdraw_negative_throws_2", () -> {
            account.withdraw(-10.0);
        }, IllegalArgumentException.class);
    }

    private static void testWithdraw_moreThanTwoDecimals_throws_3() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrowsException("withdraw_moreThanTwoDecimals_throws_3", () -> {
            account.withdraw(2.11001);
        }, IllegalArgumentException.class);
    }

    private static void testWithdraw_negative_throws_4() {
        BankAccount account = new BankAccount("e@f.com", 100.0);
        assertThrowsException("withdraw_negative_throws_4", () -> {
            account.withdraw(-1.0);
        }, IllegalArgumentException.class);
    }

    // isEmailValid tests
    private static void testIsEmailValid_validSimple() {
        assertTrue("isEmailValid_validSimple", BankAccount.isEmailValid("a@b.com"));
    }

    private static void testIsEmailValid_emptyString() {
        assertTrue("isEmailValid_emptyString", !BankAccount.isEmailValid(""));
    }

    private static void testIsEmailValid_consecutivePeriodsDomain() {
        assertTrue("isEmailValid_consecutivePeriodsDomain", !BankAccount.isEmailValid("ab@s..com"));
    }

    private static void testIsEmailValid_validHyphen() {
        assertTrue("isEmailValid_validHyphen", BankAccount.isEmailValid("abc-d@mail.com"));
    }

    private static void testIsEmailValid_startsWithDot() {
        assertTrue("isEmailValid_startsWithDot", !BankAccount.isEmailValid(".abc@mail.com"));
    }

    private static void testIsEmailValid_containsHash() {
        assertTrue("isEmailValid_containsHash", !BankAccount.isEmailValid("abc#def@h.com"));
    }

    private static void testIsEmailValid_missingAt() {
        assertTrue("isEmailValid_missingAt", !BankAccount.isEmailValid("heywassup.com"));
    }

    private static void testIsEmailValid_doubleDotsMultiple() {
        assertTrue("isEmailValid_doubleDotsMultiple", !BankAccount.isEmailValid("abc..def@mail..com"));
    }

    // isAmountValid tests
    private static void testIsAmountValid_valid() {
        assertTrue("isAmountValid_valid", BankAccount.isAmountValid(100.0));
    }

    private static void testIsAmountValid_zero() {
        assertTrue("isAmountValid_zero", BankAccount.isAmountValid(0.0));
    }

    private static void testIsAmountValid_negative() {
        assertTrue("isAmountValid_negative", !BankAccount.isAmountValid(-50.0));
    }

    private static void testIsAmountValid_moreThan2Decimals() {
        assertTrue("isAmountValid_moreThan2Decimals", !BankAccount.isAmountValid(10.999));
    }

    // deposit tests
    private static void testDeposit_valid_increasesBalance() {
        BankAccount account = new BankAccount("test@example.com", 100.0);
        try {
            account.deposit(50.0);
            assertEquals("deposit_valid_increasesBalance", 150.0, account.getBalance());
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ deposit_valid_increasesBalance (" + e.getMessage() + ")");
        }
    }

    private static void testDeposit_moreThan2Decimals_throws() {
        BankAccount account = new BankAccount("test@example.com", 100.0);
        assertThrowsException("deposit_moreThan2Decimals_throws", () -> {
            account.deposit(10.999);
        }, IllegalArgumentException.class);
    }

    private static void testDeposit_negative_throws() {
        BankAccount account = new BankAccount("test@example.com", 100.0);
        assertThrowsException("deposit_negative_throws", () -> {
            account.deposit(-10.0);
        }, IllegalArgumentException.class);
    }

    // transfer tests
    private static void testTransfer_valid_movesMoney() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        try {
            accountA.transfer(accountB, 25.0);
            assertEquals("transfer_valid_movesMoney_A", 75.0, accountA.getBalance());
            assertEquals("transfer_valid_movesMoney_B", 75.0, accountB.getBalance());
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ transfer_valid_movesMoney (" + e.getMessage() + ")");
        }
    }

    private static void testTransfer_nullDestination_throws() {
        BankAccount account = new BankAccount("testA@example.com", 100.0);
        assertThrowsException("transfer_nullDestination_throws", () -> {
            account.transfer(null, 10.0);
        }, IllegalArgumentException.class);
    }

    private static void testTransfer_moreThan2Decimals_throws() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        assertThrowsException("transfer_moreThan2Decimals_throws", () -> {
            accountA.transfer(accountB, 15.999);
        }, IllegalArgumentException.class);
    }

    private static void testTransfer_negative_throws() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        assertThrowsException("transfer_negative_throws", () -> {
            accountA.transfer(accountB, -15.0);
        }, IllegalArgumentException.class);
    }

    private static void testTransfer_insufficientFunds_throws() {
        BankAccount accountA = new BankAccount("testA@example.com", 100.0);
        BankAccount accountB = new BankAccount("testB@example.com", 50.0);
        assertThrowsException("transfer_insufficientFunds_throws", () -> {
            accountA.transfer(accountB, 200.0);
        }, InsufficientFundsException.class);
    }

    // Functional interface for test runner
    @FunctionalInterface
    interface TestRunner {
        void run() throws Exception;
    }
}