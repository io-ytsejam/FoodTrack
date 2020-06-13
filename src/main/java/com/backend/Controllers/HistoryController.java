package com.backend.Controllers;

import com.backend.Models.PersonEntity;
import com.backend.Models.PersonHistoryEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Repositories.PersonHistoryEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Security.JwtTokenUtil;
import com.backend.Services.UserServiceImpl;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.Controllers.SearchController.jsonGetRequest;


@RestController
public class HistoryController {

    @Autowired
    PersonHistoryEntityRepository historyRepository;

    @Autowired
    PersonEntityRepository userRepository;

    @Autowired
    RecipeEntityRepository recipeRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/api/history")
    public ResponseEntity getRecipeHistory(@RequestHeader String token) throws ResourceNotFoundException
    {
        String username=jwtTokenUtil.getUsernameFromToken(token);
        PersonEntity personEntity=userRepository.findByNickname(username);
        //List<PersonHistoryEntity> historyEntityList = new ArrayList<>(personEntity.getPersonHistoryEntities());
        Set<Object> jsons_bournes=new HashSet<>();
        /*for (PersonHistoryEntity h: historyEntityList)
        {
            if(h.getIsExternal()=='T')
            {
                String json=jsonGetRequest("https://api.spoonacular.com/recipes/"+h.getRecipe_id()+"/information?apiKey=c1393fe58e8741d9b59f20cb092a2a74");
                jsons_bournes.add(json);
            }
            else
            {
                RecipeEntity json=recipeRepository.getOne(h.getRecipe_id());
                jsons_bournes.add(json);
            }
        }*/
        return ResponseEntity.ok().body(jsons_bournes);
    }
}
