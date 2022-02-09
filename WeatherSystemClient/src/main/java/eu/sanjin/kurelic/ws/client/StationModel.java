package eu.sanjin.kurelic.ws.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationModel {

    private Integer id;

    private String name;

    private String model;

    private String modelVersion;

    private Integer postalCode;

    private String city;
}
