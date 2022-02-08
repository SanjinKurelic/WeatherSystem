package eu.sanjin.kurelic.ws.server.report.service;

import eu.sanjin.kurelic.ws.server.report.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class WeatherReportService {

    private final JmsTemplate jmsTemplate;
    private final Random random = new Random();

    // For real situations probably async fixedRate is better option, or cron pattern
    @Scheduled(fixedDelay = 20000)
    public void processWeatherReport() {
        jmsTemplate.convertAndSend(fetchWeatherReportFromSensorAPI().toString());
    }

    private Report fetchWeatherReportFromSensorAPI() {
        // Random generated report
        return new Report(
            random.nextLong(10), // station id
            BigDecimal.valueOf(random.nextDouble(30)), // temperature in celsius
            BigDecimal.valueOf(random.nextDouble(50)), // rainfall in mm/day
            BigDecimal.valueOf(random.nextDouble(45)), // wind speed in m/s
            List.of("W", "N", "E", "S", "NW", "NE", "SE", "SW").get(random.nextInt(8)), // wind direction
            BigDecimal.valueOf(random.nextDouble(5) + 1020) // barometer in kPa
        );
    }
}
