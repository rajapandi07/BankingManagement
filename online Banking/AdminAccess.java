public interface AdminAccess extends SeniorStaffAccess {

    void addManager(User user);

    void transfer();

    void dismiss();

    void updateBankDetails();

    void addBranch();
}
