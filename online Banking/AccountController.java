import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class AccountController {

    AdminDatabase userData = new AdminDatabase();
    AccountDatabase accountData = new AccountDatabase();
    DateValidation date = new DateValidation();
    ResultSet rs = null;
    Map map = null;
    List list = null;
    Account account = null;
    Transaction transaction = null;

    public String generateAccountNo(int branchId) {
        String branchCode = String.format("%04d", branchId);
        String serialNumber = String.format("%06d", (accountData.getAccountCount(branchId) + 1));
        return branchCode + serialNumber;
    }

    public String generateIfscCode() {
        int branchCount = userData.getBranchCount();
        String code = String.format("%04d", branchCount);
        String bankCode = userData.getBankCode();
        return bankCode + code;
    }

    public Map getMajorSchemes() {
        map = new HashMap();
        try {
            rs = accountData.getMajorSchemes();
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map getMajorSchemes(int userId) {
        map = new HashMap();
        try {
            rs = accountData.getMajorSchemes(userId);
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map getMinorSchemes() {
        map = new HashMap();
        try {
            rs = accountData.getMajorSchemes();
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map getMinorSchemes(int userId) {
        map = new HashMap();
        try {
            rs = accountData.getMajorSchemes(userId);
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean accountTypeExists(int id, Map map) {
        Set s = map.entrySet();
        Iterator itr = s.iterator();
        while (itr.hasNext()) {
            Map.Entry m = (Map.Entry) itr.next();
            if ((Integer) (m.getKey()) == id) {
                return true;
            }
        }
        return false;
    }

    public Account getAccount(String accountNumber) {
        try {
            rs = accountData.getAccount(accountNumber);
            rs.next();
            account = new Account(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean isTransactionLimitExceeded(String accountNumber, int payment, float depositAmount) {
        float transactionLimit = accountData.getTransactionLimit(accountNumber, payment);
        String currentDate = date.getCurrentDate();
        float totalTransactionAmount = accountData.getTransactionAmount(accountNumber, payment, currentDate);
        float availableLimit = transactionLimit - totalTransactionAmount;
        if (depositAmount > availableLimit) {
            return true;
        }
        return false;
    }

    public boolean isTransactionLimitExceeded(int accountId, int payment, float depositAmount) {
        float transactionLimit = accountData.getTransactionLimit(accountId, payment);
        String currentDate = date.getCurrentDate();
        float totalTransactionAmount = accountData.getTransactionAmount(accountId, payment, currentDate);
        float availableLimit = transactionLimit - totalTransactionAmount;
        if (depositAmount > availableLimit) {
            return true;
        }
        return false;
    }

    public boolean hasBalance(String accountNumber, float amount) {
        float balance = accountData.getBalance(accountNumber);
        if (balance >= amount) {
            return true;
        }
        return false;
    }

    public boolean hasBalance(int accountId, float amount) {
        float balance = accountData.getBalance(accountId);
        if (balance >= amount) {
            return true;
        }
        return false;
    }

    public Map getAccounts(int userId) {
        map = new HashMap();
        try {
            rs = accountData.getAccounts(userId);
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean AccountExists(int id, Map map) {
        Set s = map.entrySet();
        Iterator itr = s.iterator();
        while (itr.hasNext()) {
            Map.Entry m = (Map.Entry) itr.next();
            if ((Integer) (m.getKey()) == id) {
                return true;
            }
        }
        return false;
    }

    public Map getPayments() {
        map = new HashMap();
        try {
            rs = accountData.getPayments();
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean PaymentExists(int paymentId, Map map) {
        Set s = map.entrySet();
        Iterator itr = s.iterator();
        while (itr.hasNext()) {
            Map.Entry m = (Map.Entry) itr.next();
            if ((Integer) (m.getKey()) == paymentId) {
                return true;
            }
        }
        return false;
    }

    public List getTransactions(int accountId) {
        try {
            rs = accountData.getTransactions(accountId);
            list = new ArrayList();
            while (rs.next()) {
                list.add(new Transaction(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(7),
                        rs.getString(5), rs.getString(3), rs.getFloat(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
