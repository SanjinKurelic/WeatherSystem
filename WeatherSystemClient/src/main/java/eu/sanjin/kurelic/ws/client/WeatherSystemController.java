package eu.sanjin.kurelic.ws.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class WeatherSystemController {

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button editButton;

    @FXML
    private TableView stationGrid;

    @FXML
    private TextField nameField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField versionField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField postalField;

    @FXML
    protected void onAddNewClick() {
        editMode(true);
    }

    @FXML
    protected void onRefreshClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onCancelClick() {
        editMode(false);
    }

    @FXML
    protected void onSaveClick() {
        editMode(false);
    }

    @FXML
    protected void onEditClick() {
        editMode(true);
    }

    private void editMode(boolean enabled) {
        editButton.setVisible(!enabled);
        saveButton.setVisible(enabled);
        cancelButton.setVisible(enabled);
        nameField.setEditable(enabled);
        modelField.setEditable(enabled);
        versionField.setEditable(enabled);
        cityField.setEditable(enabled);
        postalField.setEditable(enabled);
    }
}
