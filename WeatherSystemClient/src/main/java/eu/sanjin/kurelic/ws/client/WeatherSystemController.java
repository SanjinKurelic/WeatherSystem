package eu.sanjin.kurelic.ws.client;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class WeatherSystemController implements Initializable {

    private final WeatherSystemService service = new WeatherSystemService();
    private List<StationModel> stationList;
    private int currentStationId = 0;
    private int selectedStation = 0;

    // Buttons
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    // Main table and refresh label
    @FXML
    private TableView<StationModel> stationGrid;
    @FXML
    private Label refreshTime;

    // Right info dialog
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
    }

    @FXML
    protected void onAddNewClick() {
        editMode(true);
        setStationInfo(new StationModel());
        selectedStation = -1;
    }

    @FXML
    protected void onRefreshClick() {
        refreshTable();
    }

    @FXML
    protected void onCancelClick() {
        editMode(false);
        // If adding new selectedStation will be -1
        var index = Math.max(selectedStation, 0);
        setStationInfo(stationList.get(index));
    }

    @FXML
    protected void onSaveClick() {
        editMode(false);
        var station = StationModel.builder()
            .id(selectedStation >= 0 ? currentStationId : null)
            .name(nameField.getText())
            .model(modelField.getText())
            .modelVersion(versionField.getText())
            .city(cityField.getText())
            .postalCode(Integer.valueOf(postalField.getText()))
            .build();

        if (selectedStation < 0) {
            service.saveStation(station);
        } else {
            service.updateStation(station);
        }
        refreshTable();
    }

    @FXML
    protected void onEditClick() {
        editMode(true);
    }

    @FXML
    protected void onDeleteClick() {
        service.deleteStation(stationList.get(selectedStation));
        refreshTable();
    }

    @SuppressWarnings("rawtypes")
    private void refreshTable() {
        stationList = service.fetchStations();
        stationGrid.setItems(FXCollections.observableList(stationList));
        // Select first
        selectedStation = 0;
        stationGrid.getSelectionModel().select(selectedStation);
        currentStationId = stationList.get(selectedStation).getId();
        setStationInfo(stationList.get(selectedStation));
        // Add listener
        stationGrid.getSelectionModel().getSelectedCells().addListener((ListChangeListener<TablePosition>) change -> {
            if (Objects.nonNull(stationGrid.getSelectionModel().getSelectedItem())) {
                selectedStation = stationGrid.getSelectionModel().getSelectedCells().get(0).getRow();
                currentStationId = stationList.get(selectedStation).getId();
                setStationInfo(stationList.get(selectedStation));
            }
        });
        // Set refresh time
        refreshTime.setText(
            String.format("Refreshed at: %s", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(LocalDateTime.now()))
        );
    }

    private void setStationInfo(StationModel stationModel) {
        nameField.setText(stationModel.getName());
        modelField.setText(stationModel.getModel());
        versionField.setText(stationModel.getModelVersion());
        cityField.setText(stationModel.getCity());
        postalField.setText(Optional.ofNullable(stationModel.getPostalCode()).map(String::valueOf).orElse(""));
    }

    private void editMode(boolean enabled) {
        editButton.setVisible(!enabled);
        saveButton.setVisible(enabled);
        deleteButton.setVisible(!enabled);
        cancelButton.setVisible(enabled);
        nameField.setEditable(enabled);
        modelField.setEditable(enabled);
        versionField.setEditable(enabled);
        cityField.setEditable(enabled);
        postalField.setEditable(enabled);
    }
}
