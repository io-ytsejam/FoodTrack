package com.backend.Controllers;

import com.backend.Models.PersonEntity;
import com.backend.Models.PersonHistoryEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Models.RecommendationEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Repositories.PersonHistoryEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Repositories.RecommendationEntityRepository;
import com.backend.Security.JwtTokenUtil;
import com.backend.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class SearchController {
    @Autowired
    RecommendationEntityRepository recommnedationRepository;

    @Autowired
    PersonEntityRepository userRepository;

    @Autowired
    PersonHistoryEntityRepository historyEntityRepository;

    @Autowired
    RecipeEntityRepository recipeEntityRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/search")
    public ResponseEntity recipeSearch(@RequestHeader Long id, @RequestHeader String token, @RequestHeader Boolean isExternal) throws ResourceNotFoundException
    {
        String username=jwtTokenUtil.getUsernameFromToken(token);
        PersonEntity personEntity=userRepository.findByNickname(username);
        if(recommnedationRepository.findById(id).isEmpty()) {
            RecommendationEntity recommendationEntity=new RecommendationEntity(id);
            personEntity.getRecommendationEntities().add(recommendationEntity);
        }
        userRepository.save(personEntity);
        Optional<RecipeEntity> recipeEntity=recipeEntityRepository.findById(id);
        PersonHistoryEntity historyEntity=new PersonHistoryEntity();
        historyEntity.setRecipe_id(id);
        personEntity.addPersonHistory(historyEntity);
        if(isExternal)
        {
            historyEntity.setIsExternal('T');
        }
        else {
            historyEntity.setIsExternal('F');
        }
        userRepository.save(personEntity);
        if(!isExternal)
        {
            return ResponseEntity.ok(recipeEntity);
        }
        else
        {
            String json=jsonGetRequest("https://api.spoonacular.com/recipes/"+id+"/information?apiKey=c1393fe58e8741d9b59f20cb092a2a74");
            return ResponseEntity.ok(json);
        }
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
