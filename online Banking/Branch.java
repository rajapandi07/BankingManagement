public class Branch {
    private int id;
    private String city;
    private String ifscCode;
    private String address;

    // Constructor
    public Branch(int id, String city, String ifscCode, String address) {
        this.id = id;
        this.city = city;
        this.ifscCode = ifscCode;
        this.address = address;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method
    @Override
    public String toString() {
        return "\t\t" + String.format("|%-6s|%-20s|%-20s|%-40s|", id, city, ifscCode, address);
    }
}
