package eu.sanjin.kurelic.ws.client;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class WeatherSystemService {

    private static final String STATION_API_URL = "http://localhost:8081/station";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<StationModel> fetchStations() {
        return restTemplate.getForEntity(STATION_API_URL, StationModelList.class).getBody();
    }

    public void saveStation(StationModel stationModel) {
        restTemplate.postForObject(STATION_API_URL, stationModel, StationModel.class);
    }

    public void updateStation(StationModel stationModel) {
        restTemplate.put(String.format("%s/%s", STATION_API_URL, stationModel.getId()), stationModel);
    }

    public void deleteStation(StationModel stationModel) {
        restTemplate.delete(String.format("%s/%s", STATION_API_URL, stationModel.getId()));
    }
}
