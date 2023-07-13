package com.api.controledeestacionamento.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.controledeestacionamento.services.ParkingSpotService;

@RestController //apiRest, vai trazer solicitações http e retorna diretamente o corpo da resposta em formatos especificos
@CrossOrigin(origins = "*", maxAge = 3600) //políticas de compartilhamento de recursos entre origens diferentes.
//permite que todas as origens (*) acessem os recursos desse controlador e define um tempo de cache máximo de 3600 segundos (1 hora).
@RequestMapping("/parking-spot") // Ela define o caminho do URL que será usado para acessar o método
public class ParkingSpotController {
	
	 ParkingSpotService parkingSpotService;
	 
	 public void ParkingSpotService(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}

}
