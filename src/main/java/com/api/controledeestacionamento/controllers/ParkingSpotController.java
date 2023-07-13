package com.api.controledeestacionamento.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.controledeestacionamento.dtos.ParkingSpotDto;
import com.api.controledeestacionamento.models.ParkingSpotModel;
import com.api.controledeestacionamento.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: este número de placa já está em uso");
        }
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga já está sendo usada");
        }
        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: apartamento/bloco já está registrado");
        }
        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    public ParkingSpotService getParkingSpotService() {
        return parkingSpotService;
    }
    
    @GetMapping //buscar por todos
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSports(){
    	return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }
    
    @GetMapping("/{id}") //buscar pelo id
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id")UUID id){
    	Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
    	if(!parkingSpotModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não existe");
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }
    
    
    
}
