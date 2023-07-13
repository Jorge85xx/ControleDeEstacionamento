package com.api.controledeestacionamento.services;

import org.springframework.stereotype.Service;

import com.api.controledeestacionamento.repositorys.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	
	//@Autowired //injeção de dependencia pode ser pelo comentario ou pelo construtor
	ParkingSpotRepository parkingRepository;
	
	public void setParkingRepository(ParkingSpotRepository parkingRepository) {//construtor
		this.parkingRepository = parkingRepository;
	}
	
}
