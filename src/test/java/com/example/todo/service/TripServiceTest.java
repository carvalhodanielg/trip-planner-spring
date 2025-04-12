package com.example.todo.service;

import com.example.todo.dto.request.TripRequestDTO;
import com.example.todo.dto.response.TripResponseDTO;
import com.example.todo.entities.Trip;
import com.example.todo.enums.TripStatus;
import com.example.todo.repositories.TripRepository;
import com.example.todo.services.TripService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @Test
    void testCreateTrip() {
        TripRequestDTO dto = new TripRequestDTO();

        dto.setName("Test");
        dto.setDescription("Test");
        dto.setStatus("PLANNING");

        Trip savedTrip = Trip.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .status(TripStatus.PLANNING)
                .build();

        Mockito.when(tripRepository.save(Mockito.any(Trip.class))).thenReturn(savedTrip);

        TripResponseDTO responseDTO = tripService.createTrip(dto);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals("Test", responseDTO.getName());

    }

}
