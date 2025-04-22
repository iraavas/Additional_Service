package ru.hpclab.hl.module1.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hpclab.hl.module1.dto.AppointmentDTO;

import java.util.List;

@Service
public class AppointmentClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081/appointments"; // Имя сервиса из docker-compose

    public AppointmentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AppointmentDTO> getAppointments() {
        ResponseEntity<List<AppointmentDTO>> response = restTemplate.exchange(
                baseUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
