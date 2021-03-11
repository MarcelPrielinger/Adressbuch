package controllerviewAdd;

import controllerviewAdressbuch.AdressbuchC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    @FXML
    private Label lab_error;
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
            stage.setScene(new Scene(root, 375, 300));
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
        String[] character = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","ä","ö","ü"};
        try
        {
            for (int i = 0; i < character.length;i++) {
                if (txt_telephone.getText().contains(character[i]))
                    lab_error.setText("Ihre Telefonnummer darf keine Buchstaben enthalten!");
            }
            if (!txt_telephone.getText().contains("/") || txt_telephone.getText().equals("/"))
                lab_error.setText("Ihre Telefonnummer muss \"/\" und Zahlen enthalten!");

            else if(txt_name.getText().isEmpty())
                lab_error.setText("Geben Sie einen Namen ein!");

            else if (txt_address.getText().isEmpty())
                lab_error.setText("Geben Sie eine Adresse ein!");

            else
            {
                phonebook.addPerson(new Person(txt_name.getText(), txt_address.getText(), txt_telephone.getText()));
                System.out.println("Person hinzugefügt!");
                phonebook.save(true);
                stage.close();
                AdressbuchC.show(new Stage());
            }
        }
        catch (Exception e)
        {
            System.out.println("Fehler");
        }
    }
}
