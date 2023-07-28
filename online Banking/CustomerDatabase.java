import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDatabase {

	Connection connection = null;

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	{
		connection = (DatabaseConnection.getInstance()).getConnection();
	}

	public boolean userExists(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from \"user\" where id=" + userId + ";");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean userExists(int userId, String phoneNo) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from \"user\" where id=" + userId + " and password='" + phoneNo + "';");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean userIsRegistered(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from \"user\" where id=" + userId + " and password is not null;");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updatePassword(String password, int userId) {
		try {
			ps = connection.prepareStatement("update \"user\" set password='" + password + "'where id=" + userId + ";");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getUser(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select id,phone_no,password,email,name,address,date_of_birth,role,branchid from \"user\" where id="
							+ userId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet aadharExists(long aadharNo) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from \"user\" where aadhar_no=" + aadharNo + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int getRole(long aadharNo) {
		int role = 0;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select role from \"user\" where aadhar_no=" + aadharNo + ";");
			if (rs.next())
				role = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	public boolean isBankMember(long aadharNumber) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT id FROM \"user\" WHERE aadhar_no = " + aadharNumber + " AND role = 2;");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
