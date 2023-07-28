import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class CustomerView implements CustomerAccess{

    List list = null;
    Map map = null;
    Input input = new Input();
    AccountController account = new AccountController();
    AccountDatabase accountData = new AccountDatabase();
    CustomerDatabase userData = new CustomerDatabase();
    UserController userControl = new UserController();
    DateValidation date = new DateValidation();

    public void updateTransactionPin(int userId) {
        int accountId = selectAccountId(userId);
        if (accountData.transactionPinExists(accountId)) {
            System.out.println("Change Transaction Pin ");
            String pinNumber = input.getPinNumber("Enter 6 digit pin : ");
            accountData.updateTransactionPin(accountId, pinNumber);
            System.out.println("Transaction Pin changed successfully....");
        } else {
            System.out.println("Create Transaction Pin ");
            String pinNumber = input.getPinNumber("Enter 6 digit pin : ");
            accountData.updateTransactionPin(accountId, pinNumber);
            System.out.println("Transaction Pin created successfully....");
        }
    }

    public void viewAccounts(Map map) {
        Set<Map.Entry> entries = map.entrySet();
        Iterator<Map.Entry> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public int selectAccountId(int userId) {
        map = account.getAccounts(userId);
        viewAccounts(map);
        boolean check = false;
        int accountId = 0;
        while (!check) {
            accountId = input.getInt("Entet Account Id : ");
            if (account.AccountExists(accountId, map)) {
                check = true;
            } else {
                System.out.println("Account Id doesn't Exists");
            }
        }
        return accountId;
    }

    public void addBeneficiery(int userId) {
        input.getString("");
        String accountNumber = input.getAccountNumber("Enter Account Number : ");
        String ifscCode = input.getString("Enter IFSC code : ");
        if (!(accountData.isBeneficiaryAccount(userId, accountNumber))) {
            int accountId = accountData.getAccountId(accountNumber, ifscCode);
            if (accountId != -1) {
                String nickName = input.getName("Enter Nick Name : ");
                accountData.addBeneficiery(userId, nickName, accountId);
                System.out.println("!!...Beneficiery Added Successfully...!!");
            } else {
                System.out.println("!!...Enter Correct details to Add Beneficiery...!!");
            }
        } else {
            System.out.println("!!...This Account Already in your Beneficiery List...!!");
        }
    }

    public void fundTransfer(int userId) {
        System.out.print("1.Direct pay\n2.select from Beneficiery\nPress Any to Back\n");
        int dec = input.getInt("Enter : ");
        int transferId = 0;
        if (dec == 1) {
            transferId = getTransferId();
            makePayment(userId, transferId);
        } else if (dec == 2) {
            int beneficiaryId = getBeneficieryId(userId);
            if (beneficiaryId != -1) {
                transferId = accountData.getTransferId(beneficiaryId);
                makePayment(userId, transferId);
            }
        }
    }

    public void makePayment(int userId, int transferId) {
        if (transferId != -1) {
            int accountId = selectAccountId(userId);
            int paymentId = selectPaymentId();
            float amount = (float) input.getDouble("Enter amount to be deposited : ₹");
            try{
                if (isValidTransaction(accountId, paymentId, transferId, amount)) {
                    accountData.transferAmount(accountId, amount, "-");
                    accountData.transferAmount(transferId, amount, "+");
                    accountData.addTransaction(accountId, paymentId, amount, transferId, date.getCurrentDate());
                    System.out.println("!!...Transaction Successfull...!!");
                 }
            }
            catch(InvalidTransactionException | AccountNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean isValidTransaction(int accountId, int paymentId, int transferId, float amount) throws InvalidTransactionException, AccountNotFoundException {
        if (!account.isTransactionLimitExceeded(accountId, paymentId, amount)) {
            if (account.hasBalance(accountId, amount)) {
                if (accountData.isActive(transferId)) {
                
                } 
                
                else {
                    throw new InvalidTransactionException("!!!...Transaction Failed Due to receivor End...!!!");
                }
            } 
            
            else {
                throw new InvalidTransactionException("!!...Insufficient Balance....!!");
            }
        } 
        
        else {
            throw new InvalidTransactionException("!!..Transaction limit exceeded...!!");
        }
        return true;
    }

    public int getBeneficieryId(int userId) {
        list = userControl.getBeneficieries(userId);
        int beneficieryId = -1;
        if (!list.isEmpty()) {
            System.out.println("\t\t===============================================================================+");
            System.out.println(
                    "\t\t" + String.format("|%-6s|%-30s|%-20s|%-20s|", "Id", "Name", "Nick Name", "Account Number"));
            System.out.println("\t\t===============================================================================+");
            userControl.showBeneficiery(list);
            System.out
                    .println("\t\t===============================================================================\n\n");
            boolean check = false;
            while (!check) {
                beneficieryId = input.getInt("Enter Beneficiery Id : ");
                if (userControl.beneficieryIdExist(beneficieryId, list)) {
                    check = true;
                } else {
                    System.out.println("Id Doesn't exist.Please Re-enter..");
                }
            }
        } else {
            System.out.println("Your Beneficiery List is Empty");
        }
        return beneficieryId;
    }

    public int getTransferId() {
        input.getString("");
        String accountNumber = input.getAccountNumber("Enter Account Number : ");
        String ifscCode = input.getString("Enter IFSC code : ");
        int accountId = accountData.getAccountId(accountNumber, ifscCode);
        if (accountId != -1) {
            return accountId;
        } else {
            System.out.println("!!Enter Correct details...!!");
        }
        return -1;
    }

    public int selectPaymentId() {
        map = account.getPayments();
        viewPayments(map);
        boolean check = false;
        int paymentId = 0;
        while (!check) {
            paymentId = input.getInt("Entet Account Id : ");
            if (account.PaymentExists(paymentId, map)) {
                check = true;
            } else {
                System.out.println("Payment Id doesn't Exists");
            }
        }
        return paymentId;

    }

    public void viewPayments(Map map) {
        Set<Map.Entry> entries = map.entrySet();
        Iterator<Map.Entry> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void checkBalance(int userId) {
        int accountId = selectAccountId(userId);
        float balance = accountData.getBalance(accountId);
        System.out.println("\t\t+=========================================================+\n"
                + "\t\t                   BALANCE   -  ₹ " + balance + "\n"
                + "\t\t+=========================================================+\n");
    }

    public void getStatement(int userId) {
        int accountId = selectAccountId(userId);
        list = account.getTransactions(accountId);
        System.out.println(
                "\t\t+==============================================================================================================================================+");
        System.out.println("\t\t" + String.format("|%-6s|%-20s|%-20s|%-30s|%-20s|%-20s|%-20s|", "Id", "From Account",
                "To Account", "Branch", "Date", "Transaction", "Amount"));
        System.out.println(
                "\t\t+==============================================================================================================================================+");
        for (Object object : list) {
            System.out.println(object);
        }
        System.out.println(
                "\t\t+==============================================================================================================================================+\n\n\n");
    }
}
