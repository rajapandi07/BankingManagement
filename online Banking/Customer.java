public class Customer {
    private int id;
    private long phoneNo;
    private String email;
    private String name;
    private String address;
    private String accountNumber;
    private String account;

    // Constructors
    public Customer() {
    }

    public Customer(int id, long phoneNo, String email, String name, String address, String accountNumber,
            String account) {
        this.id = id;
        this.phoneNo = phoneNo;
        this.email = email;
        this.name = name;
        this.address = address;
        this.accountNumber = accountNumber;
        this.account = account;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "\t\t+=========================================+\n"
                + "\t\t" + String.format("|%-41s|", "User Details") + "\n"
                + "\t\t+=========================================+\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Id:", id) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Phone Number:", phoneNo) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "e-mail:", email) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Name:", name) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Address:", address) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Account Number:", accountNumber) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Account Type:", account) + "\n"
                + "\t\t+=========================================+\n\n\n";
    }
}
