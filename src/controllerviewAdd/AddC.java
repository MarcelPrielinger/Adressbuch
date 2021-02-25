package controllerviewAdd;

import controllerviewAdressbuch.AdressbuchC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;
import model.Phonebook;

public class AddC
{
    private Stage stage;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_address;
    @FXML
    private TextField txt_telephone;
    private Phonebook phonebook = new Phonebook();

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(AddC.class.getResource("addV.fxml"));
            Parent root = fxmlLoader.load();

            Phonebook phonebook = new Phonebook();
            phonebook.load();

            //send data to MainController
            AddC ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Add");
            stage.setScene(new Scene(root, 375, 250));
            stage.show();
        }

        catch (Exception e)
        {
            System.err.println("Something wrong with addV.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public void add()
    {
        try
        {
            phonebook.addPerson(new Person(txt_name.getText(), txt_address.getText(), Integer.parseInt(txt_telephone.getText())));
            System.out.println("Person hinzugefügt!");
            phonebook.save(true);
            stage.close();
            AdressbuchC.show(new Stage());
        }
        catch (Exception e)
        {
            System.out.println("Fehler");
        }
    }
}
