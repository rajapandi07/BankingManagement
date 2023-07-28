public interface ClerkAccess extends SubStaffAccess {

    int addUser(long aadharNo, int role, int branch);

    void addCustomer(int branchId);

    void createAccount(int userId, int branchId);
}
