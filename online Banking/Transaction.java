public class Transaction {
    private int id;
    private String fromAccount;
    private String toAccount;
    private String branch;
    private String date;
    private String transaction;
    private float amount;

    // Constructor without fromAccount
    public Transaction(int id, String fromAccount, String toAccount, String branch, String date, String transaction,
            float amount) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.branch = branch;
        this.date = date;
        this.transaction = transaction;
        this.amount = amount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\t\t" + String.format("|%-6s|%-20s|%-20s|%-30s|%-20s|%-20s|%-20s|", id, fromAccount, toAccount, branch,
                date, transaction, "₹" + amount);
    }
}
