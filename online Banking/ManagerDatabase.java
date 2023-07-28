import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDatabase extends SubStaffDatabase {

	Connection connection = null;

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	{
		connection = (DatabaseConnection.getInstance()).getConnection();
	}

	public ResultSet getNewCustomers(int branchId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select u.id,phone_no,email,u.name,address,account_number,r.name from account a join \"user\" u on a.userid=u.id join account_type r on a.account_type=r.id where u.branchid="
							+ branchId + " and a.status ='i';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getEmployees(int branchId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT id, phone_no, password, email, name, address, date_of_birth, role, branchid FROM \"user\" WHERE branchid = 1 AND role <> 2 AND role <> 3 and status ='t';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void transferEmployee(int employeeId, int transferBranch) {
		try {
			ps = connection.prepareStatement(
					"update \"user\" set branchid=" + transferBranch + " where id=" + employeeId + ";");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getBranches() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select * from branch;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
