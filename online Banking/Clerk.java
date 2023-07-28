
public class Clerk extends SubStaff implements ClerkAccess {

    UserController userControl = new UserController();
    AdminDatabase userData = new AdminDatabase();
    CustomerDatabase CustomerData = new CustomerDatabase();

    User user = null;
    
    public int addUser(long aadharNo, int role, int branch) {
        long phoneNo = input.getPhoneNo("Enter phone Number : ");
        input.getString("");
        String email = input.getString("Enter e-mail : ");
        String name = input.getName("Enter a Name : ");
        String DateOfBirth = input.getDate("Enter DOB (yyyy-MM-dd) : ");
        input.getString("");
        String address = input.getString("Enter address : ");
        return userData.addUser(phoneNo, email, name, DateOfBirth, address, branch, aadharNo, role);
    }

    public void addCustomer(int branchId) {
        long aadharNo = input.getAadharNo("Enter Aadhar Number : ");
        int role = 0;
        if (userControl.aadharExists(aadharNo)) {
            role = userData.getRole(aadharNo);
            if (role == 2) {
                System.out.println("The given user is already a customer");
            }
        } else {
            int userId = addUser(aadharNo, 2, branchId);
            createAccount(userId, branchId);
        }
    }

    public void createAccount(int userId, int branchId) {
        String accountNumber = account.generateAccountNo(branchId);
        int age = date.getYears(date.getCurrentDate(), (userControl.getUser(userId)).getDOB());
        int accountType = 0;
        if (age >= 18) {
            map = account.getMajorSchemes();
            accountType = selectAccount(map);
        } else {
            map = account.getMinorSchemes();
            accountType = selectAccount(map);
        }
        accountData.createAccount(userId, accountNumber, accountType);
        System.out.println("User Id is : " + userId + "\nAccount number is : " + accountNumber);
    }

    public void createAccount(int branchId) {
        long aadharNo = input.getAadharNo("Enter Aadhar Number : ");
        if (userControl.aadharExists(aadharNo)) {
            if (CustomerData.isBankMember(aadharNo)) {
                user = userControl.getUser(aadharNo);
                int age = date.getYears(date.getCurrentDate(), (user.getDOB()));
                int accountType = 0;
                if (age >= 18) {
                    map = account.getMajorSchemes(user.getId());
                    accountType = selectAccount(map);
                } else {
                    map = account.getMajorSchemes(user.getId());
                    accountType = selectAccount(map);
                }
                String accountNumber = account.generateAccountNo(branchId);
                accountData.createAccount(user.getId(), accountNumber, accountType);
                System.out.println("New Account number is : " + accountNumber);
            } else {
                System.out.println("!!...Customer not Exists...!!");
            }
        } else {
            System.out.println("!!...Aadhar doesn't Exists...!!");
        }
    }
}
