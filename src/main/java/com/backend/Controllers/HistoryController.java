package com.backend.Controllers;

import com.backend.Models.HistoryEntity;
import com.backend.Models.PersonEntity;
import com.backend.Models.RecommendationEntity;
import com.backend.Repositories.HistoryEntityRepository;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Security.JwtTokenUtil;
import com.backend.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.Controllers.RecommendationController.jsonGetRequest;

@RestController
public class HistoryController {

    @Autowired
    HistoryEntityRepository historyRepository;

    @Autowired
    PersonEntityRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/api/history")
    public ResponseEntity getRecipeHistory(@RequestHeader String token) throws ResourceNotFoundException
    {
        String username=jwtTokenUtil.getUsernameFromToken(token);
        PersonEntity personEntity=userRepository.findByNickname(username);
        List<HistoryEntity> historyEntityList=new ArrayList<>();
        historyEntityList.addAll(personEntity.getHistoryEntities());

        Set<String> jsons_bournes=new HashSet<>();
        for (HistoryEntity h: historyEntityList)
        {
            String json=jsonGetRequest("https://api.spoonacular.com/recipes/"+h.getHistory_id()+"/information?apiKey=c1393fe58e8741d9b59f20cb092a2a74");
            jsons_bournes.add(json);
        }
        return ResponseEntity.ok().body(jsons_bournes);
    }
}
