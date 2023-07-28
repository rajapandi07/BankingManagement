public class User {
    private int id;
    private long phoneNo;
    private String password;
    private String email;
    private String name;
    private String address;
    private int role;
    private int branchId;
    private String DOB;

    // Constructor
    public User(int id, long phoneNo, String password, String email, String name, String address, String DOB, int role,
            int branchId) {
        this.id = id;
        this.phoneNo = phoneNo;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        this.role = role;
        this.branchId = branchId;
        this.DOB = DOB;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String toString() {
        return "\t\t" + String.format("|%-6s|%-20s|%-20s|%-20s|%-20s|%-20s|", id, phoneNo, email, name, address, DOB);
    }
}
