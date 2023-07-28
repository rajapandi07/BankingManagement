public class ManagerPage {
    
    Manager manager = new Manager();


    public void managerRoles(User user,Input input) {
        boolean check = true;
        while (check) {
            System.out.println(
                      "\t\t+=======================================================================+\n"
                    + "\t\t|                             MANAGER PAGE                              |\n"
                    + "\t\t+=======================================================================+\n"
                    + "\t\t|                1.    Add Employee                                     |\n"
                    + "\t\t|                2.    Transfer Employee                                |\n"
                    + "\t\t|                3.    Dismiss Employee                                 |\n"
                    + "\t\t|                4.    Approve Customer                                 |\n"
                    + "\t\t|                5.    Create New Account                               |\n"
                    + "\t\t|                6.    Create Account for Existing Customer             |\n"
                    + "\t\t|                7.    View Customer Details                            |\n"
                    + "\t\t|                8.    Check Customer Balance                           |\n"
                    + "\t\t|                9.    LOG OUT                                          |\n"
                    + "\t\t+=======================================================================+\n");
            int dec = input.getInt("Enter : ");
            switch (dec) {
                case 1:
                    manager.addEmployee(user);
                    break;
                case 2:
                    manager.transferEmployee(user.getBranchId());
                    break;
                case 3:
                    manager.dismissEmployee(user.getBranchId());
                    break;
                case 4:
                    manager.ApproveCustomer(user.getBranchId());
                    break;
                case 5:
                    manager.accessClerk.addCustomer(user.getBranchId());
                    break;
                case 6:
                    manager.accessClerk.createAccount(user.getBranchId());
                    break;
                case 7:
                    manager.accessClerk.viewCustomer();
                    break;
                case 8:
                    manager.accessClerk.checkBalance();
                    break;
                case 9:
                    check = false;
                    break;
                default:
                    System.out.println("Enter valid Input...");
            }
        }
    }
}
