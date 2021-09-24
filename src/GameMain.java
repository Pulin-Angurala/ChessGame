import java.io.IOException;
import java.sql.SQLException;

public class GameMain {
    public static void main(String[] args) throws IOException, SQLException {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        model.addObserver(view);
    }    
}