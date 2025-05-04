package com.example.todo.controllers;


import com.example.todo.dto.request.TransportationRequestDTO;
import com.example.todo.dto.response.TransportationResponseDTO;
import com.example.todo.services.TransportationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportations")
@RequiredArgsConstructor
public class TransportationController {

    private final TransportationService transportationService;


    @PostMapping
    public ResponseEntity<TransportationResponseDTO> createTransportation(@Valid  @RequestBody TransportationRequestDTO transportationRequestDTO) {
        TransportationResponseDTO response = transportationService.createTransportation(transportationRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransportationResponseDTO>> getAllTransportations(@RequestParam(name = "tripId") String tripId) {
        List<TransportationResponseDTO> response = transportationService.getAllTransportationsByTripId(Long.parseLong(tripId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
