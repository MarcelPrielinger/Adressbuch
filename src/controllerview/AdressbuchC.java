package controllerview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdressbuchC {

    private Stage stage;

    public static void show(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AdressbuchC.class.getResource("adressbuchV.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            AdressbuchC ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Adressbuch");
            stage.setScene(new Scene(root, 360, 370));
            stage.show();
        }
        catch (IOException e) {
            System.err.println("Something wrong with adressbuchV.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
