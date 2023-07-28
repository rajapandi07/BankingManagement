import java.util.Map;

public class SubStaff implements SubStaffAccess {

    Input input = new Input();
    AccountController account = new AccountController();
    AccountDatabase accountData = new AccountDatabase();
    DateValidation date = new DateValidation();

    Map map = null;

    public void checkBalance() {
        String accountNumber = validateAccount();
        if(accountNumber!=null){
        float balance = accountData.getBalance(accountNumber);
        System.out.println(
                  "\t\t+=========================================================+\n"
                + "\t\t                   BALANCE   -  ₹ " + balance + "\n"
                + "\t\t+=========================================================+\n");
        }
    }

    public void viewCustomer() {
        String accountNumber = validateAccount();
        if (accountNumber != null) {
            System.out.println(account.getAccount(accountNumber));
        }
    }

    public int selectAccount(Map map) {
        boolean check = false;
        int accountType = 0;
        System.out.println(map);
        while (!check) {
            accountType = input.getInt("Enter AccountType Id : ");
            if (account.accountTypeExists(accountType, map)) {
                check = true;
            }
        }
        return accountType;
    }

    public String validateAccount() {
        String accountNumber = input.getAccountNumber("Enter accountNumber Number : ");
        try{
            if (accountData.accountNumberExist(accountNumber)) {
                if (accountData.isActive(accountNumber)) {
                 return accountNumber;
                 } 
            } 
         }
         catch(AccountNotFoundException e){
            System.out.println(e.getMessage());
         }
        return null;
    }
}
