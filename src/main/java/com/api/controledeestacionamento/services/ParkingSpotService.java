package com.api.controledeestacionamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.controledeestacionamento.models.ParkingSpotModel;
import com.api.controledeestacionamento.repositorys.ParkingSpotRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingSpotService {

	@Autowired // injeção de dependencia pode ser pelo comentario ou pelo construtor
	private final ParkingSpotRepository parkingSpotRepository;

	public ParkingSpotService(ParkingSpotRepository parkingRepository) {// construtor
		this.parkingSpotRepository = parkingRepository;
	}

	@Transactional
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}

	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}

	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}

	public boolean existsByApartmentAndBlock(String apartment, String block) {
		return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
	}
	
}
