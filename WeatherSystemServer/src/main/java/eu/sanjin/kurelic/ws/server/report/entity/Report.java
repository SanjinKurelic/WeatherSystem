package eu.sanjin.kurelic.ws.server.report.entity;

import java.math.BigDecimal;

public record Report(Long stationId, BigDecimal temperature, BigDecimal rainfall, BigDecimal windSpeed,
                     String windDirection, BigDecimal barometer) {
}
