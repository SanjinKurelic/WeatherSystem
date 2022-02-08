package eu.sanjin.kurelic.ws.server.station.rest;

import eu.sanjin.kurelic.ws.server.station.entity.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "station")
public interface StationRestRepository extends CrudRepository<Station, Long> {
}
