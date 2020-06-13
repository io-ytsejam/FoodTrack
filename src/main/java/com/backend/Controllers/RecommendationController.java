package com.backend.Controllers;

import com.backend.Models.PersonEntity;
import com.backend.Models.RecommendationEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Repositories.RecommendationEntityRepository;
import com.backend.Security.JwtTokenUtil;
import com.backend.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.Controllers.SearchController.jsonGetRequest;

@RestController
public class RecommendationController {

    @Autowired
    RecommendationEntityRepository recommnedationRepository;

    @Autowired
    PersonEntityRepository userRepository;

    @Autowired
    RecipeEntityRepository recipeEntityRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/api/recs")
    public ResponseEntity getRecs(@RequestHeader String token) throws ResourceNotFoundException
    {
        String username=jwtTokenUtil.getUsernameFromToken(token);
        List<RecommendationEntity> array=new ArrayList<>();
        PersonEntity personEntity=userRepository.findByNickname(username);

        array.addAll(personEntity.getRecommendationEntities());

        Set<String> jsons_bournes=new HashSet<>();
        for (RecommendationEntity r: array)
        {
            jsons_bournes.add(jsonGetRequest("https://api.spoonacular.com/recipes/"+r.getRecommendation_id()+"/similar?apiKey=c1393fe58e8741d9b59f20cb092a2a74&number=3"));
        }

        return ResponseEntity.ok().body(jsons_bournes);
    }



}
