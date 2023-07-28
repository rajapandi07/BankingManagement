public class CustomerPage {
    
    CustomerView customerView = new CustomerView();

    public void customerRoles(User user,Input input) {
        boolean check = true;
        while (check) {
            System.out.println("\t\t+=======================================================================+\n"
                    + "\t\t|                             CUSTOMER PAGE                             |\n"
                    + "\t\t+=======================================================================+\n"
                    + "\t\t|              1.    Check balance                                      |\n"
                    + "\t\t|              2.    Fund Transfer                                      |\n"
                    + "\t\t|              3.    Add beneficiery                                    |\n"
                    + "\t\t|              4.    View Statement                                     |\n"
                    + "\t\t|              5.    Set or Change TransactionPin                       |\n"
                    + "\t\t|              6.    Exit                                               |\n"
                    + "\t\t+=======================================================================+\n");
            int dec = input.getInt("Enter : ");
            switch (dec) {
                case 1:
                    customerView.checkBalance(user.getId());
                    break;
                case 2:
                    customerView.fundTransfer(user.getId());
                    break;
                case 3:
                    customerView.addBeneficiery(user.getId());
                    break;
                case 4:
                    customerView.getStatement(user.getId());
                    break;
                case 5:
                    customerView.updateTransactionPin(user.getId());
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    System.out.println("Enter Valid Input...");
            }
        }
    }
}
