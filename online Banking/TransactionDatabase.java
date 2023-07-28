import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TransactionDatabase implements Transferable {

	Connection connection = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	{
		connection = (DatabaseConnection.getInstance()).getConnection();
	}

	public int transferAmount(String accountNumber, float amount, String operand) {
		int id = 0;
		try {
			ps = connection.prepareStatement("update account set balance=balance" + operand + "" + amount
					+ " where account_number='" + accountNumber + "' returning id;");
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public int transferAmount(int accountId, float amount, String operand) {
		int id = 0;
		try {
			ps = connection.prepareStatement("update account set balance=balance" + operand + "" + amount + " where id="
					+ accountId + " returning id;");
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public float getTransactionLimit(String accountNumber, int payment) {
		float limit = 0f;
		try {
			ps = connection.prepareStatement(
					"select transaction_limit from transaction_limit where account=(select account_type from account where account_number='"
							+ accountNumber + "') and transaction=" + payment + ";");
			rs = ps.executeQuery();
			rs.next();
			limit = rs.getFloat(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return limit;
	}

	public float getTransactionLimit(int accountId, int payment) {
		float limit = 0f;
		try {
			ps = connection.prepareStatement(
					"select transaction_limit from transaction_limit where account=(select account_type from account where id="
							+ accountId + ") and transaction=" + payment + ";");
			rs = ps.executeQuery();
			rs.next();
			limit = rs.getFloat(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return limit;
	}

	public float getTransactionAmount(String accountNumber, int payment, String date) {
		float transactionAmount = 0f;
		try {
			ps = connection.prepareStatement(
					"select sum(t.amount)from transaction t join account a on a.id=t.accountid where a.account_number='"
							+ accountNumber + "' and t.transaction_type=" + payment + " and t.transaction_date='" + date
							+ "';");
			rs = ps.executeQuery();
			rs.next();
			transactionAmount = rs.getFloat(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionAmount;
	}

	public float getTransactionAmount(int accountId, int payment, String date) {
		float transactionAmount = 0f;
		try {
			ps = connection.prepareStatement("select sum(t.amount)from transaction t where t.accountid=" + accountId
					+ " and t.transaction_type=" + payment + " and t.transaction_date='" + date + "';");
			rs = ps.executeQuery();
			rs.next();
			transactionAmount = rs.getFloat(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionAmount;
	}

	public void addTransaction(int accountId, int payment, float amount, String date, int branchid) {
		try {
			ps = connection.prepareStatement(
					"insert into transaction(transferid,amount,transaction_date,transaction_type,branchid)values("
							+ accountId + "," + amount + ",'" + date + "'," + payment + "," + branchid + ");");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addTransaction(int accountId, int payment, float amount, int transferId, String date) {
		try {
			ps = connection.prepareStatement(
					"insert into transaction(accountid,amount,transaction_date,transaction_type,transferid)values("
							+ accountId + "," + amount + ",'" + date + "'," + payment + "," + transferId + ");");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean transactionPinExists(int accountId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from account where id=" + accountId + " and transaction_pin is not null;");
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateTransactionPin(int accountId, String pinNumber) {
		try {
			ps = connection.prepareStatement(
					"update account set transaction_pin='" + pinNumber + "' where id=" + accountId + ";");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getTransferId(int beneficiaryId) {
		int transferId = 0;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select beneficieryid from beneficiery where id=" + beneficiaryId + ";");
			if (rs.next()) {
				transferId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transferId;
	}

	public ResultSet getTransactions(int accountId) {
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"SELECT t.id, a.account_number, r.name, t.amount, t.transaction_date, a1.account_number,b.city FROM transaction t LEFT JOIN account a ON a.id = t.accountid LEFT JOIN account a1 ON a1.id = t.transferid left join branch b on b.id=t.branchid JOIN transaction_type r ON r.id = t.transaction_type WHERE t.accountid = "
							+ accountId + " OR t.transferid = " + accountId + " order by transaction_date;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
