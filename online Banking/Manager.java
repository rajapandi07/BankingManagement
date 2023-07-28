public class Manager extends SeniorStaff implements ManagerAccess {

    Clerk accessClerk=new Clerk();
    AccountDatabase accountData = new AccountDatabase();
    
    Customer customer = null;

    public void ApproveCustomer(int branchId) {
        list = userControl.getNewCustomers(branchId);
        if (!(list.isEmpty())) {
            for (Object object : list) {
                System.out.println(object);
                customer = (Customer) object;
                System.out.println("Press 1 for approve customer\nPress 2 for Exit\nPress Any Number for Next....");
                int dec = input.getInt("Enter : ");
                if (dec == 1) {
                    accountData.updateAccountStatus(customer.getAccountNumber(), 'a');
                } else if (dec == 2) {
                    break;
                }
            }
        } else {
            System.out.println("Customer Approval list is Empty");
        }
    }
}
