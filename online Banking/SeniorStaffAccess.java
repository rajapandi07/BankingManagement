public interface SeniorStaffAccess {

    int addUser(long aadharNo, int role, int branch);

    void addEmployee(User user);

    void transferEmployee(int branchId);

    void dismissEmployee(int branchId);
}
