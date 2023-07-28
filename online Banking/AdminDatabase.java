import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDatabase extends ManagerDatabase {

	Connection connection = null;

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	{
		connection = (DatabaseConnection.getInstance()).getConnection();
	}

	public ResultSet getManagers() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT id, phone_no, password, email, name, address, date_of_birth, role, branchid FROM \"user\" WHERE role = 3 and status ='t';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void createBankTable() {
		try {
			st = connection.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS bank (id int PRIMARY KEY,name VARCHAR(50),code VARCHAR(5));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean hasRecords() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT id from bank");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void insertRecord() {
		try {
			ps = connection.prepareStatement("insert into bank(id)values(1);");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean bankCodeExists() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT id from bank where code is not null");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateBankCode(String bankName, String bankCode) {
		try {
			ps = connection.prepareStatement(
					"update bank set name='" + bankName + "',code='" + bankCode + "' where id=" + 1 + ";");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getBranchCount() {
		int branches = 0;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select count(id)from branch;");
			rs.next();
			branches = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branches;
	}

	public String getBankCode() {
		String code = "";
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT code from bank where id=1;");
			rs.next();
			code = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

	public void addBranch(String city, String address, String ifscCode) {
		try {
			ps = connection.prepareStatement("insert into branch(city,ifsc_code,address)values('" + city + "','"
					+ ifscCode + "','" + address + "');");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
