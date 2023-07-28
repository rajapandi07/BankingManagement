public class AdminPage {
    
    Admin admin = new Admin();


    public void adminRoles(User user,Input input,UserController userControl,AdminDatabase userData) {
        userControl.createBankTable();
        if (!(userData.bankCodeExists())) {
            admin.updateBankDetails();
        }
        boolean check = true;
        while (check) {
            System.out.println(
                      "\t\t+=======================================================================+\n"
                    + "\t\t|                             ADMIN PAGE                                |\n"
                    + "\t\t+=======================================================================+\n"
                    + "\t\t|                 1.    Add Manager                                     |\n"
                    + "\t\t|                 2.    Transfer Manager                                |\n"
                    + "\t\t|                 3.    Dismiss Manager                                 |\n"
                    + "\t\t|                 4.    Add Branch                                      |\n"
                    + "\t\t|                 5.    Add Employee                                    |\n"
                    + "\t\t|                 6.    Transfer Employee                               |\n"
                    + "\t\t|                 7.    Dismiss Employee                                |\n"
                    + "\t\t|                 8.    Exit                                            |\n"
                    + "\t\t+=======================================================================+\n");
            int dec = input.getInt("Enter : ");
            switch (dec) {
                case 1:
                    admin.addManager(user);
                    break;
                case 2:
                    admin.transfer();
                    break;
                case 3:
                    admin.dismiss();
                    break;
                case 4:
                    admin.addBranch();
                    break;
                case 5:
                    admin.addEmployee(user);
                    break;
                case 6:
                    admin.transferEmployee(user.getBranchId());
                    break;
                case 7:
                    admin.dismissEmployee(user.getBranchId());
                    break;
                case 8:
                    check = false;
                    break;
                default:
                    System.out.println("Enter Valid Input...");
            }
        }
    }
}
