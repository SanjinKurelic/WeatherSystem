module eu.sanjin.kurelic.ws.client.weathersystemclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.sanjin.kurelic.ws.client.weathersystemclient to javafx.fxml;
    exports eu.sanjin.kurelic.ws.client.weathersystemclient;
}
