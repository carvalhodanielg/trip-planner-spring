package com.example.todo.services;

import com.example.todo.dto.request.TripRequestDTO;
import com.example.todo.dto.response.TripResponseDTO;
import com.example.todo.entities.Trip;
import com.example.todo.enums.TripStatus;
import com.example.todo.repositories.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public TripResponseDTO createTrip(TripRequestDTO request){
        System.out.println("Recebido trip: " + request);

        Trip trip = mapToEntity(request);
        System.out.println("Recebido trip: " + trip);

        trip = tripRepository.save(trip);
        System.out.println("Recebido trip: " + trip);


        System.out.println("Recebido trip: " + mapToResponse(trip));

        return mapToResponse(trip);
    }

    public List<TripResponseDTO> listAllTrips(){

        return tripRepository.findAllWithTransportations().stream().map(this::mapToResponse).collect(Collectors.toList());
//        return tripRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public TripResponseDTO getTripById(Long id){
        return tripRepository.findById(id).map(this::mapToResponse).orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public TripResponseDTO updateTrip(Long id, TripRequestDTO request){
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
        updateEntity(trip, request);

        return mapToResponse(tripRepository.save(trip));
    }

    public void deleteTrip(Long id){
        tripRepository.deleteById(id);
    }


    private Trip mapToEntity(TripRequestDTO dto){
        return Trip.builder()
                .id(null)
                .name(dto.getName())
                .description(dto.getDescription())
                .destination(dto.getDestination())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(TripStatus.valueOf(dto.getStatus()))
                .isPrivate(dto.getIsPrivate())
                .budget(dto.getBudget())
                .spending(dto.getSpending())
                .coverImageUrl(dto.getCoverImageUrl())
                .review(dto.getReview())
                .rating(dto.getRating())
                .build();
    }

    private TripResponseDTO mapToResponse(Trip trip){
        return TripResponseDTO.builder()
                .name(trip.getName())
                .description(trip.getDescription())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .status(trip.getStatus().name())
                .isPrivate(trip.getIsPrivate())
                .budget(trip.getBudget())
                .spending(trip.getSpending())
                .coverImageUrl(trip.getCoverImageUrl())
                .review(trip.getReview())
                .rating(trip.getRating())
                .transportations(trip.getTransportations())
                .build();
    }

    private void updateEntity(Trip trip, TripRequestDTO dto){
        trip.setName(dto.getName());
        trip.setDescription(dto.getDescription());
        trip.setDestination(dto.getDestination());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setStatus(TripStatus.valueOf(dto.getStatus()));
        trip.setIsPrivate(dto.getIsPrivate());
        trip.setBudget(dto.getBudget());
        trip.setSpending(dto.getSpending());
        trip.setCoverImageUrl(dto.getCoverImageUrl());
        trip.setReview(dto.getReview());
        trip.setRating(dto.getRating());
    }


}
