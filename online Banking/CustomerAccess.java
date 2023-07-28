import java.util.Map;

public interface CustomerAccess {
    

    void fundTransfer(int userId);

    void addBeneficiery(int userId) ;

    void makePayment(int userId, int transferId);

    void viewPayments(Map map);

    void checkBalance(int userId);

    void getStatement(int userId) ;

    void viewAccounts(Map map);

    void updateTransactionPin(int userId);
}
