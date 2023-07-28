import java.sql.Connection;
import java.sql.SQLException;

public class OnlineBanking {

    public static void main(String[] args) {
        try {
            DatabaseConnection.getInstance().getConnection();
            UserView user = new UserView();
            user.userPage();
        } finally {
            DatabaseConnection.getInstance().closeConnection();
        }
    }

}