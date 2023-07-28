import java.util.List;

public class SeniorStaff implements SeniorStaffAccess {

    Input input = new Input();
    AdminDatabase userData = new AdminDatabase();
    UserController userControl = new UserController();

    List list = null;

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

    public void addEmployee(User user) {
        long aadharNo = input.getAadharNo("Enter Aadhar Number : ");
        int branch = 0;
        int role = 0;
        if (userControl.aadharExists(aadharNo)) {
            role = userData.getRole(aadharNo);
            if (role == 2) {
                System.out.println("The given user is already a customer");
                if (!(userData.isBankMember(aadharNo))) {
                    user = userControl.getUser(userControl.getId(aadharNo));
                    branch = user.getBranchId();
                    role = rolesUnderManager();
                    int id = userData.addUser(user.getPhoneNo(), user.getEmail(), user.getName(), user.getDOB(),
                            user.getAddress(), branch, aadharNo, role);
                    System.out.println("The user Id is : " + id);
                } else {
                    System.out.println("and also a staff in the bank..");
                }
            } else if (role != 2) {
                System.out.println("Given user is already a Staff in the bank");
            }
        } else {
            branch = user.getBranchId();
            role = rolesUnderManager();
            System.out.println("Staff Id is : " + addUser(aadharNo, role, branch));
        }
    }

    public int rolesUnderManager() {
        list = userControl.rolesUnderManager();
        System.out.println("\t\t=============================");
        System.out.println("\t\t" + String.format("|%-6s|%-20s|", "Id", "Name"));
        System.out.println("\t\t=============================");
        for (Object object : list) {
            System.out.println(object);
        }
        System.out.println("\t\t=============================\n\n");
        boolean check = false;
        int role = 0;
        while (!check) {
            role = input.getInt("Enter role Id : ");
            if (userControl.roleExists(role, list)) {
                check = true;
            } else {
                System.out.println("Role Id doesn't Exists..");
            }
        }
        return role;
    }

    public void transferEmployee(int branchId) {
        list = userControl.getEmployees(branchId);
        int employeeId = selectEmployee(list);
        int transferBranch = selectBranch();
        int dec = input.getInt("Press 1 To Transfer \nElse Press Any to cancel");
        if (dec == 1) {
            userData.transferEmployee(employeeId, transferBranch);
            System.out.println("Employee Transfered Successfully");
        }
    }

    public void dismissEmployee(int branchId) {
        list = userControl.getEmployees(branchId);
        int employeeId = selectEmployee(list);
        int dec = input.getInt("Press 1 To Dismiss \nElse Press Any to cancel");
        if (dec == 1) {
            userData.updateEmployee(employeeId, 'f');
            System.out.println("Employee Dismissed");
        }
    }

    public int selectEmployee(List list) {
        int employeeId = 0;
        System.out.println(
                "\t\t================================================================================================================");
        System.out.println("\t\t" + String.format("|%-6s|%-20s|%-20s|%-20s|%-20s|%-20s|", "Id", "phone Number",
                "E-mail", "Name", "Address", "DOB"));
        System.out.println(
                "\t\t================================================================================================================");
        for (Object object : list) {
            System.out.println(object);
        }
        System.out.println(
                "\t\t================================================================================================================\n\n");
        boolean check = false;
        while (!check) {
            employeeId = input.getInt("Enter Employee Id : ");
            if (userControl.employeeIdExist(employeeId, list)) {
                check = true;
            } else {
                System.out.println("!!..Enter correct Employee Id.. !!");
            }
        }
        return employeeId;
    }

    public int selectBranch() {
        list = userControl.getBranches();
        int branchId = 0;
        System.out.println(
                "\t\t==========================================================================================");
        System.out.println("\t\t" + String.format("|%-6s|%-20s|%-20s|%-40s|", "Id", "City", "IFSC Code", "Address"));
        System.out.println(
                "\t\t==========================================================================================");
        for (Object object : list) {
            System.out.println(object);
        }
        System.out.println(
                "\t\t==========================================================================================");
        boolean check = false;
        while (!check) {
            branchId = input.getInt("Enter Branch Id : ");
            if (userControl.branchExists(branchId, list)) {
                check = true;
            } else {
                System.out.println("!!..Enter correct branch Id.. !!");
            }
        }
        return branchId;
    }
}
