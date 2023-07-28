
public class Admin extends SeniorStaff implements AdminAccess {

    AccountController account = new AccountController();

    public void addManager(User user) {
        long aadharNo = input.getAadharNo("Enter Aadhar Number : ");
        int branch = 0;
        if (userControl.aadharExists(aadharNo)) {
            int role = userData.getRole(aadharNo);
            if (role == 2) {
                System.out.println("The given user is already a customer");
                if (!(userData.isBankMember(aadharNo))) {
                    user = userControl.getUser(userControl.getId(aadharNo));
                    branch = selectBranch();
                    int id = userData.addUser(user.getPhoneNo(), user.getEmail(), user.getName(), user.getDOB(),
                            user.getAddress(), branch, aadharNo, 3);
                    System.out.println("The user Id is : " + id);
                } else {
                    System.out.println("and also a staff in the bank..");
                }
            } else if (role != 2) {
                System.out.println("Given user is already a Staff in the bank");
            }
        } else {
            branch = selectBranch();
            System.out.println("Staff Id is : " + addUser(aadharNo, 3, branch));
        }
    }

    public void transfer() {
        list = userControl.getManagers();
        int employeeId = selectEmployee(list);
        int transferBranch = selectBranch();
        int dec = input.getInt("Press 1 To Transfer \nElse Press Any to cancel");
        if (dec == 1) {
            userData.transferEmployee(employeeId, transferBranch);
            System.out.println("Employee Transfered Successfully");
        }
    }

    public void dismiss() {
        list = userControl.getManagers();
        int employeeId = selectEmployee(list);
        int dec = input.getInt("Press 1 To Dismiss \nElse Press Any to cancel");
        if (dec == 1) {
            userData.updateEmployee(employeeId, 'f');
            System.out.println("Employee Dismissed");
        }
    }

    public void updateBankDetails() {
        String bankName = input.getName("Enter bank name : ");
        String bankCode = input.getString("Enter bank code : ");
        userData.updateBankCode(bankName, bankCode);
    }

    public void addBranch() {
        String address = input.getString("Enter Branch Address : ");
        String city = input.getString("Enter City Name : ");
        String ifscCode = account.generateIfscCode();
        userData.addBranch(city, address, ifscCode);
        System.out.println("Branch Added Successfully");
    }

    public void ChangeAccount() {

    }
}
