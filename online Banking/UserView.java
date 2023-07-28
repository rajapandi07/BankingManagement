public class UserView implements AuthenticationService {

    Input input = new Input();
    UserController userControl = new UserController();
    AdminDatabase userData = new AdminDatabase();

    public void userPage() {
        boolean check = true;
        while (check) {
            System.out.print(
                      "\t\t+============================+\n"
                    + "\t\t|        LOG IN PAGE         |\n"
                    + "\t\t+============================+\n"
                    + "\t\t|      1.   Login            |\n"
                    + "\t\t|      2.   Sign up          |\n"
                    + "\t\t|      3.   Exit             |\n"
                    + "\t\t+============================+\n");
            int dec = input.getInt("Enter : ");
            switch (dec) {
                case 1:
                    try {
                        logIn();
                    } catch (UserNotFoundException | PasswordMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        signUp();
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    check = false;
                    break;
                default:
                    System.out.println("Enter valid input...");
            }
        }
    }

    public void logIn() throws UserNotFoundException, PasswordMismatchException {
        int userId = input.getInt("Enter User ID : ");
        input.getString("");
        String password = input.getPassword("Enter password : ");
        if (userData.userExists(userId)) {
            if (userData.userIsRegistered(userId)) {
                if (userData.userExists(userId, password)) {
                    User user = userControl.getUser(userId);
                    switch (user.getRole()) {
                        case 1:
                            new AdminPage().adminRoles(user,input,userControl,userData);
                            break;
                        case 2:
                            new CustomerPage().customerRoles(user,input);
                            break;
                        case 3:
                            new ManagerPage().managerRoles(user,input);
                            break;
                        case 4:
                            new CashierPage().cashierRoles(user,input);
                            break;
                        case 5:
                            new ClerkPage().clerkeRoles(user,input);
                            break;
                        default:
                            System.out.println("Enter Valid Input.....");
                    }
                } else {
                    // System.out.println("Incorrect Password..");
                    throw new PasswordMismatchException("Incorrect Password..");
                }
            } else {
                throw new UserNotFoundException("User not Registered...");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public void signUp() {
        int userId = input.getInt("Enter User ID : ");
        if (userData.userExists(userId)) {
            if (!(userData.userIsRegistered(userId))) {
                input.getString("");
                String password = input.getPassword("Enter password : ");
                userData.updatePassword(password, userId);
                System.out.println("Password created successfully....");
            } else {
                throw new UserNotFoundException("User Is Already Registered....");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

}
