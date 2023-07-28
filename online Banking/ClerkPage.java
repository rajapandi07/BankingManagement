public class ClerkPage {
    
    Clerk clerk = new Clerk();


    public void clerkeRoles(User user,Input input) {
        boolean check = true;
        while (check) {
            System.out.println(
                      "\t\t+=======================================================================+\n"
                    + "\t\t|                             CLERK PAGE                                |\n"
                    + "\t\t+=======================================================================+\n"
                    + "\t\t|          1.    Create New Account                                     |\n"
                    + "\t\t|          2.    Create Account for Existing Customer                   |\n"
                    + "\t\t|          3.    View Customer Details                                  |\n"
                    + "\t\t|          4.    Check Customer Balance                                 |\n"
                    + "\t\t|          5.    Exit                                                   |\n"
                    + "\t\t+=======================================================================+");
            int dec = input.getInt("Enter : ");
            switch (dec) {
                case 1:
                    clerk.addCustomer(user.getBranchId());
                    break;
                case 2:
                    clerk.createAccount(user.getBranchId());
                    break;
                case 3:
                    clerk.viewCustomer();
                    break;
                case 4:
                    clerk.checkBalance();
                    break;
                case 5:
                    check = false;
                    break;
                default:
                    System.out.println("Enter valid input...");
            }
        }
    }
}
