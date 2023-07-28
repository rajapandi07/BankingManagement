import java.sql.ResultSet;

public interface AccountService {

    ResultSet getMajorSchemes();

    ResultSet getMinorSchemes();

    void createAccount(int userId, String accountNumber, int accountType);

    ResultSet getAccount(String accountNumber);

    boolean isActive(String accountNumber)throws AccountNotFoundException;

    float getBalance(String accountNumber);

    void addBeneficiery(int userId, String nickName, int accountId);

    ResultSet getBeneficieries(int userId);
}
