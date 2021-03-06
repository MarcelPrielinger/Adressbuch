package controllerviewAdressbuch;

import controllerviewAdd.AddC;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Phonebook;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdressbuchC implements Initializable {

    private Stage stage;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_address;
    @FXML
    private TextField txt_telephone;
    @FXML
    private Label lab_error;
    @FXML
    private Label lab_site;
    private int index = 1;
    private Phonebook phonebook = new Phonebook();

    public static void show(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AdressbuchC.class.getResource("adressbuchV.fxml"));
            Parent root = fxmlLoader.load();

            Phonebook phonebook = new Phonebook();
            phonebook.load();

            //get controller which is connected to this fxml file
            AdressbuchC ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Adressbuch");
            stage.setScene(new Scene(root, 360, 310));
            stage.show();

            stage.setOnCloseRequest(new  EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    //phonebook.save(false);
                }
            });
        }
        catch (IOException e) {
            System.err.println("Something wrong with adressbuchV.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    @FXML
    public void add()
    {
        try {
            stage.close();
            AddC.show(new Stage());
        }
        catch (Exception e)
        {
            System.out.println("Fehler beim öffnen");
        }
    }

    public void delete()
    {
        try {
            index -= 1;
            if(index == 0)
            {
                index = 1;
                txt_name.setText("");
                txt_address.setText("");
                txt_telephone.setText("");
                lab_site.setText("0/0");
                phonebook.delete(0);
                phonebook.save(false);
            }
            else
            {
                update();
                phonebook.delete(index - 1);
            }
        }
        catch (Exception e)
        {
            lab_error.setText("Error beim Löschen!");
            e.printStackTrace();
        }
    }

    public void nextPerson()
    {
        try {
            if (index < phonebook.size()) {
                index++;
                update();
            } else
                lab_error.setText("Ungültige Seitenzahl!");
        }
        catch (Exception e)
        {
            lab_error.setText("Error!");
        }
    }

    public void previousPerson()
    {
        try {
            if (index > 1) {
                index--;
                update();
            } else
                lab_error.setText("Ungültige Seitenzahl!");
        }
        catch (Exception e)
        {
            lab_error.setText("Error!");
        }
    }

    public void update() {
        txt_name.setText(phonebook.getName(index - 1));
        txt_address.setText(phonebook.getAddress(index - 1));
        txt_telephone.setText(phonebook.getTelephone(index - 1));
        lab_site.setText(index + "/" + phonebook.size());
        phonebook.save(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            phonebook.load();
            update();
        }
        catch (IndexOutOfBoundsException e)
        {
            lab_error.setText("Keine Person vorhanden!");
            System.out.println("Keine Person im Telefonbuch!");
        }
        catch (Exception e)
        {
            System.out.println("Es ist ein Fehler beim starten des Programms aufgetreten!");
        }
    }
}


