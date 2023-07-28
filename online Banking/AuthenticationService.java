public interface AuthenticationService {
    public void signUp();

    public void logIn() throws UserNotFoundException, PasswordMismatchException;
}
