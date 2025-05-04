package com.example.todo.services;

import com.example.todo.dto.request.TransportationRequestDTO;
import com.example.todo.dto.response.TransportationResponseDTO;
import com.example.todo.entities.Transportation;
import com.example.todo.entities.Trip;
import com.example.todo.repositories.TransportationRepository;
import com.example.todo.repositories.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportationService {

    private final TransportationRepository transportationRepository;
    private final TripRepository tripRepository;

    //create transportation
    public TransportationResponseDTO createTransportation(TransportationRequestDTO transportationDTO) {

        Trip trip = tripRepository.findById(transportationDTO.getTripId()).orElseThrow(() -> new IllegalArgumentException("Trip not found"));

       Transportation transportation = mapToEntity(transportationDTO, trip);

        transportation = transportationRepository.save(transportation);

        return mapToResponse(transportation);
    }



    //listAllTransportations by trip
    public List<TransportationResponseDTO> getAllTransportationsByTripId(Long tripId) {
        List<Transportation> transportations = transportationRepository.findByTripId(tripId);
        return transportations.stream().map(x -> mapToResponse(x)).collect(Collectors.toList());
    }





    //get transportation


    //delete transportation


    //patch transportation

private Transportation mapToEntity(TransportationRequestDTO dto, Trip trip) {
   return  Transportation.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .price(dto.getPrice())
            .date(dto.getDate())
            .trip(trip)
            .build();
}

    private TransportationResponseDTO mapToResponse(Transportation transportation) {
        return TransportationResponseDTO.builder()
                .id(transportation.getId())
                .name(transportation.getName())
                .description(transportation.getDescription())
                .price(transportation.getPrice())
                .date(transportation.getDate())
                .tripId(transportation.getTrip().getId())
                .build();
    }


}
