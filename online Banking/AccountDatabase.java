import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDatabase extends TransactionDatabase implements Transferable, AccountService {

	Connection connection = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	{
		connection = (DatabaseConnection.getInstance()).getConnection();
	}

	public int getAccountCount(int branchId) {
		int count = 0;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select count(a.id)from account a join \"user\" u on u.id=a.userid where u.branchid="
					+ branchId + ";");
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ResultSet getMajorSchemes() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id,name from account_type;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getMajorSchemes(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT a.id, a.name FROM account_type a JOIN account a1 ON a1.account_type <> a.id JOIN \"user\" u ON a1.userid = u.id WHERE u.id = "
							+ userId + " and a.for_minor<>true;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getMinorSchemes() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id,name from account_type where for_minor='t';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getMinorSchemes(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT a.id, a.name FROM account_type a JOIN account a1 ON a1.account_type <> a.id JOIN \"user\" u ON a1.userid = u.id WHERE u.id = "
							+ userId + " and a.for_minor=true;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void createAccount(int userId, String accountNumber, int accountType) {
		try {
			ps = connection.prepareStatement("insert into account(userid,account_number,account_type)values(" + userId
					+ ",'" + accountNumber + "'," + accountType + ");");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean accountNumberExist(String accountNumber) throws AccountNotFoundException {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from account where account_number='" + accountNumber + "';");
			if (!rs.next()) {
				throw new AccountNotFoundException("!!..Account Not Found..!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public ResultSet getAccount(String accountNumber) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select u.name,u.email,u.phone_no,u.date_of_birth,u.address,b.ifsc_code,a.account_number,a.account_type,a.balance from \"user\" u join branch b on b.id=u.branchid join account a on a.userid=u.id where a.account_number='"
							+ accountNumber + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean isActive(String accountNumber) throws AccountNotFoundException{
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from account where account_number='" + accountNumber + "' and status='a';");
			if (!rs.next()) {
				throw new AccountNotFoundException("The given account number is not activated or Blocked..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean isActive(int accountId) throws AccountNotFoundException {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from account where id=" + accountId + " and status='a';");
			if (!rs.next()) {
				throw new AccountNotFoundException("The given account number is not activated or Blocked..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public int getPaymentId(String paymentName) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from transaction_type where name='" + paymentName + "';");
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void updateAccountStatus(String accountNumber, char status) {
		try {
			ps = connection.prepareStatement(
					"update account set status='" + status + "' where account_number='" + accountNumber + "';");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public float getBalance(int accountId) {
		float balance = 0f;
		try {
			ps = connection.prepareStatement("select balance from account where id=" + accountId + ";");
			rs = ps.executeQuery();
			if(rs.next()){
			balance = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return balance;
	}

	public float getBalance(String accountNumber) {
		float balance = 0f;
		try {
			ps = connection
					.prepareStatement("select balance from account where account_number='" + accountNumber + "';");
			rs = ps.executeQuery();
			if(rs.next()){
			balance = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return balance;
	}

	public ResultSet getAccounts(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id,account_number from account where userid=" + userId + " and status='a';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean isBeneficiaryAccount(int userId, String accountNumber) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from beneficiery where userid=" + userId
					+ " and beneficieryid=(select id from account where account_number='" + accountNumber + "');");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getAccountId(String accountNumber, String ifscCode) {
		try {
			ps = connection.prepareStatement(
					"select a.id from account a join \"user\" u on u.id=a.userid where a.account_number='"
							+ accountNumber + "' and u.branchid=(select id from branch where ifsc_code='" + ifscCode
							+ "');");
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ResultSet getPayments() {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id,name from transaction_type where online_transfer ='t';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void addBeneficiery(int userId, String nickName, int accountId) {
		try {
			ps = connection.prepareStatement("insert into beneficiery(userid,nick_name,beneficieryid)values(" + userId
					+ ",'" + nickName + "'," + accountId + ");");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getBeneficieries(int userId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select b.id,u.name,b.nick_name,a.account_number,b.beneficieryid from beneficiery b join account a on a.id=b.beneficieryid join \"user\" u on u.id=a.userid where b.userid="
							+ userId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
