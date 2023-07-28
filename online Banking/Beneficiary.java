public class Beneficiary {
    private int id;
    private String name;
    private String nickName;
    private String accountNumber;
    private int accountId;

    // Constructors
    public Beneficiary() {
    }

    public Beneficiary(int id, String name, String nickName, String accountNumber, int accountId) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.accountNumber = accountNumber;
        this.accountId = accountId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "\t\t" + String.format("|%-6s|%-30s|%-20s|%-20s|", id, name, nickName, accountNumber);
    }
}
