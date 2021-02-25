package controllerviewAdressbuch;

import controllerviewAdd.AddC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Phonebook;

import java.io.IOException;

public class AdressbuchC {

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
            stage.setScene(new Scene(root, 360, 370));
            stage.show();
        }
        catch (IOException e) {
            System.err.println("Something wrong with adressbuchV.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    @FXML
    public void add()
    {
        stage.close();
        AddC.show(new Stage());
    }

    @FXML
    public void loadCSV()
    {
        try {
            phonebook.load();
            index = 1;
            paste();
        }
        catch (Exception e)
        {
            System.out.println("Fehler beim laden!");
        }
    }

    public void delete()
    {
        try {
            txt_name.setText("");
            txt_address.setText("");
            txt_telephone.setText("");

            phonebook.getPeople().remove(index - 1);
            index -= 1;
            lab_site.setText(index + "/" + phonebook.size());
        }
        catch (Exception e)
        {
            lab_error.setText("Error!");
        }
    }

    public void nextPerson()
    {
        try {
            if (index < phonebook.size()) {
                index++;
                paste();
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
                paste();
            } else
                lab_error.setText("Ungültige Seitenzahl!");
        }
        catch (Exception e)
        {
            lab_error.setText("Error!");
        }
    }

    public void saveCSV()
    {
        try {
            phonebook.save(false);
        }
        catch (Exception e)
        {
            System.out.println("Fehler beim Speichern!");
        }
    }

    public void saveChanges()
    {
        try {
            phonebook.getPeople().get(index - 1).setName(txt_name.getText());
            phonebook.getPeople().get(index - 1).setAddress(txt_address.getText());
            phonebook.getPeople().get(index - 1).setTelephone(Integer.parseInt(txt_telephone.getText()));
            System.out.println("Werte geändert!");
        }
        catch (Exception e)
        {
            System.out.println("Werte konnten nicht geändert werden!");
        }
    }

    public void paste() {
        txt_name.setText(phonebook.getName(index - 1));
        txt_address.setText(phonebook.getAddress(index - 1));
        txt_telephone.setText(String.valueOf(phonebook.getTelephone(index - 1)));
        lab_site.setText(index + "/" + phonebook.size());
    }

}


