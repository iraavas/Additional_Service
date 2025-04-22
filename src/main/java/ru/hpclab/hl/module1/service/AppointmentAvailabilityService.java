package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.client.AppointmentClient;
import ru.hpclab.hl.module1.client.DoctorClient;
import ru.hpclab.hl.module1.dto.AppointmentDTO;
import ru.hpclab.hl.module1.dto.DoctorDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentAvailabilityService {

    private final DoctorClient doctorClient;
    private final AppointmentClient appointmentClient;

    public AppointmentAvailabilityService(DoctorClient doctorClient, AppointmentClient appointmentClient) {
        this.doctorClient = doctorClient;
        this.appointmentClient = appointmentClient;
    }

    public List<DoctorDTO> getAvailableDoctors(String specialization, LocalDateTime dateTime) {
        List<DoctorDTO> doctors = doctorClient.getDoctorsBySpecialization(specialization);
        List<AppointmentDTO> appointments = appointmentClient.getAppointments();

        return doctors.stream()
                .filter(doctor -> appointments.stream().noneMatch(app ->
                        app.getDoctorId().equals(doctor.getId()) &&
                                app.getAppointmentDate().equals(dateTime)
                ))
                .collect(Collectors.toList());
    }
}
