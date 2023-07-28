public class Cashier extends SubStaff implements CashierAccess {


    public void deposit(int branchId) {
        String accountNumber = validateAccount();
        if (accountNumber != null) {
            System.out.println(account.getAccount(accountNumber)
                    + "\n CHECK THE ACCOUNT INFORMATIONS\nif correct  :Press 1\n else  :Press any");
            int dec = input.getInt("Enter");
            if (dec == 1) {
                float amount = (float) input.getDouble("Enter amount to be deposited : ₹");
                int payment = accountData.getPaymentId("deposit");
                if (!account.isTransactionLimitExceeded(accountNumber, payment, amount)) {
                    dec = 0;
                    System.out.println("Press 1 to deposit\nPress 2 to Cancel");
                    dec = input.getInt("Enter : ");
                    input.getString("");
                    if (dec == 1) {
                        int accountId = accountData.transferAmount(accountNumber, amount, "+");
                        accountData.addTransaction(accountId, payment, amount, date.getCurrentDate(), branchId);
                        System.out.println("Money deposited successfully...");
                    }
                } else {
                    System.out.println("!!..Transaction limit exceeded...!!");
                }
            }
        }
    }

    public void withDraw(int branchId) {
        String accountNumber = validateAccount();
        if (accountNumber != null) {
            System.out.println(account.getAccount(accountNumber)
                    + "\n CHECK THE ACCOUNT INFORMATIONS\nif correct  :Press 1\n else  :Press any");
            int dec = input.getInt("Enter");
            if (dec == 1) {
                float amount = (float) input.getDouble("Enter amount to be deposited : ₹");
                int payment = accountData.getPaymentId("withdraw");
                if (!account.isTransactionLimitExceeded(accountNumber, payment, amount)) {
                    if (account.hasBalance(accountNumber, amount)) {
                        dec = 0;
                        System.out.println("Press 1 to Withdraw\nPress 2 to Cancel");
                        dec = input.getInt("Enter : ");
                        input.getString("");
                        if (dec == 1) {
                            int accountId = accountData.transferAmount(accountNumber, amount, "-");
                            accountData.addTransaction(accountId, payment, amount, date.getCurrentDate(), branchId);
                            System.out.println("Money withdrawal completed successfully....");
                        }
                    } else {
                        System.out.println("!!...Insufficient Balance....!!");
                    }
                } else {
                    System.out.println("!!..Transaction limit exceeded...!!");
                }
            }
        }
    }
}
