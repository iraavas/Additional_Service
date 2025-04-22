package ru.hpclab.hl.module1.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hpclab.hl.module1.dto.DoctorDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081/doctors"; // Имя сервиса из docker-compose

    public DoctorClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DoctorDTO> getDoctorsBySpecialization(String specialization) {
        ResponseEntity<List<DoctorDTO>> response = restTemplate.exchange(
                baseUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody().stream()
                .filter(d -> d.getSpecialization().equalsIgnoreCase(specialization))
                .collect(Collectors.toList());
    }
}
