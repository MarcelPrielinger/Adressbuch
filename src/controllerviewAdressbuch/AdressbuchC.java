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
    private Phonebook phonebook;

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

    @FXML
    public void add()
    {
        stage.close();
        AddC.show(new Stage());
    }

    @FXML
    public void loadCSV()
    {
        try
        {
                phonebook.load();
                index = 1;
                paste();
        }
        catch (NullPointerException e)
        {
            lab_error.setText("Adressbuch ist leer!");
        }

        catch (Exception e)
        {
            lab_error.setText("Error!");
        }
    }

    public void delete()
    {
        try {
            txt_name.setText("");
            txt_address.setText("");
            txt_telephone.setText("");

            Phonebook.people.remove(index - 1);
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
        phonebook.save();
    }

    public void saveChanges()
    {
        Phonebook.people.get(index-1).setName(txt_name.getText());
        Phonebook.people.get(index-1).setAddress(txt_address.getText());
        Phonebook.people.get(index-1).setTelephone(Integer.parseInt(txt_telephone.getText()));
        System.out.println("Werte geändert!");
    }

    public void paste()
    {
        txt_name.setText(Phonebook.people.get(index-1).getName());
        txt_address.setText(Phonebook.people.get(index-1).getAddress());
        txt_telephone.setText(String.valueOf(Phonebook.people.get(index-1).getTelephone()));
        lab_site.setText(index + "/" + phonebook.size());
    }

}


