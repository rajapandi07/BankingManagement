import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubStaffDatabase extends CustomerDatabase {

	Connection connection = null;

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	{
		connection = (DatabaseConnection.getInstance()).getConnection();
	}

	public int addUser(long phoneNo, String email, String name, String dob, String address, int branch, long aadharNo,
			int role) {
		int id = 0;
		try {
			ps = connection.prepareStatement(
					"insert into \"user\"(phone_no,email,name,date_of_birth,address,branchid,aadhar_no,role)values("
							+ phoneNo + ",'" + email + "','" + name + "','" + dob + "','" + address + "'," + branch
							+ "," + aadharNo + "," + role + ")returning id;");
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void updateEmployee(int employeeId, char status) {
		try {
			ps = connection
					.prepareStatement("update \"user\" set status='" + status + "' where id=" + employeeId + ";");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getUser(long aadharNumber) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select id,phone_no,password,email,name,address,date_of_birth,role,branchid from \"user\" where aadhar_no="
							+ aadharNumber + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getRoles() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select * from roles;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean isBankMember(long aadharNo) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select *from \"user\" u join roles r on r.id=u.role where u.aadhar_no=" + aadharNo
					+ " and r.name<>'customer';");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
