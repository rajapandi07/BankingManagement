public interface CashierAccess extends SubStaffAccess {

    void deposit(int branchId);

    void withDraw(int branchId);
}
