public class Account {
    private String name;
    private String email;
    private long mobileNo;
    private String dateOfBirth;
    private String address;
    private String ifscCode;
    private String accountNumber;
    private String accountType;
    private float balance;

    // Constructor
    public Account(String name, String email, long mobileNo, String dateOfBirth, String address,
            String ifscCode, String accountNumber, String accountType, float balance) {
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.ifscCode = ifscCode;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and Setters (omitted for brevity)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "\t\t+=========================================+\n"
                + "\t\t" + String.format("|%-41s|", "Account Details") + "\n"
                + "\t\t+=========================================+\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Name:", name) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Email:", email) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Mobile No:", mobileNo) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Date of Birth:", dateOfBirth) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Address:", address) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "IFSC Code:", ifscCode) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Account Number:", accountNumber) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Account Type:", accountType) + "\n"
                + "\t\t" + String.format("|%-20s|%-20s|", "Balance:", balance) + "\n"
                + "\t\t+=========================================+\n\n\n";

    }

}
