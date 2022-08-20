package be.ehb.kennypassenier.herexamen.controllers;

import be.ehb.kennypassenier.herexamen.entities.JwtRequest;
import be.ehb.kennypassenier.herexamen.entities.JwtResponse;
import be.ehb.kennypassenier.herexamen.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtService jwtService;
    @CrossOrigin(origins = "*")
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }
}
