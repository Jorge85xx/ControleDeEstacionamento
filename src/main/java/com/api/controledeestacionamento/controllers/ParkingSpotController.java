package com.api.controledeestacionamento.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //apiRest
@CrossOrigin(origins = "*", maxAge = 3600) //
@RequestMapping("/parking-spot") //
public class ParkingSpotController {

}
