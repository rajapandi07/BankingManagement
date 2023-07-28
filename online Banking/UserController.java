import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UserController {

    AdminDatabase userData = new AdminDatabase();
    AccountDatabase accountData = new AccountDatabase();
    ResultSet rs = null;
    List list = null;
    User user = null;

    public User getUser(int userId) {
        try {
            rs = userData.getUser(userId);
            rs.next();
            user = new User(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser(long aadharNumber) {
        try {
            rs = userData.getUser(aadharNumber);
            rs.next();
            user = new User(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean aadharExists(long aadharNo) {
        try {
            rs = userData.aadharExists(aadharNo);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getId(long aadharNo) {
        try {
            rs = userData.aadharExists(aadharNo);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List getBranches() {
        list = new ArrayList();
        try {
            rs = userData.getBranches();
            while (rs.next()) {
                list.add(new Branch(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean branchExists(int branchId, List list) {
        for (Object al : list) {
            Branch branch = (Branch) al;
            if (branch.getId() == branchId) {
                return true;
            }
        }
        return false;
    }

    public List rolesUnderManager() {
        list = new ArrayList();
        try {
            rs = userData.getRoles();
            while (rs.next()) {
                if (!(rs.getString(2).equals("admin") || rs.getString(2).equals("manager")
                        || rs.getString(2).equals("customer"))) {
                    list.add(new Role(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean roleExists(int roleId, List list) {
        for (Object al : list) {
            Role role = (Role) al;
            if (role.getId() == roleId) {
                return true;
            }
        }
        return false;
    }

    public List getNewCustomers(int branchId) {
        list = new ArrayList();
        try {
            rs = userData.getNewCustomers(branchId);
            while (rs.next()) {
                list.add(new Customer(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getBeneficieries(int userId) {
        list = new ArrayList();
        try {
            rs = accountData.getBeneficieries(userId);
            while (rs.next()) {
                list.add(
                        new Beneficiary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void showBeneficiery(List list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public boolean beneficieryIdExist(int id, List<Beneficiary> list) {
        for (Beneficiary beneficiary : list) {
            if (beneficiary.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List getEmployees(int branchId) {
        try {
            list = new ArrayList();
            rs = userData.getEmployees(branchId);
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean employeeIdExist(int employeeId, List list) {
        for (Object object : list) {
            User user = (User) object;
            if (user.getId() == employeeId) {
                return true;
            }
        }
        return false;
    }

    public List getManagers() {
        try {
            list = new ArrayList();
            rs = userData.getManagers();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createBankTable() {
        userData.createBankTable();
        if (!(userData.hasRecords())) {
            userData.insertRecord();
        }
    }
}
