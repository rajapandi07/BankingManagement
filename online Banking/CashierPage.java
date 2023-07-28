public class CashierPage {
    
    Cashier cashier = new Cashier();


    public void cashierRoles(User user,Input input) {
        boolean check = true;
        while (check) {
            System.out.println(
                      "\t\t+=======================================================================+\n"
                    + "\t\t|                        CASHIER PAGE                                   |\n"
                    + "\t\t+=======================================================================+\n"
                    + "\t\t|             1.     Deposit                                            |\n"
                    + "\t\t|             2.     Withdraw                                           |\n"
                    + "\t\t|             3.     Check Balance                                      |\n"
                    + "\t\t|             4.     View Customer Details                              |\n"
                    + "\t\t|             5.     Exit                                               |\n"
                    + "\t\t+=======================================================================+\n");
            int dec = input.getInt("Enter : ");
            switch (dec) {
                case 1:
                    cashier.deposit(user.getBranchId());
                    break;
                case 2:
                    cashier.withDraw(user.getBranchId());
                    break;
                case 3:
                    cashier.checkBalance();
                    break;
                case 4:
                    cashier.viewCustomer();
                    break;
                case 5:
                    check = false;
                    break;
                default:
                    System.out.print("Enter valid input...");
            }
        }
    }
}
