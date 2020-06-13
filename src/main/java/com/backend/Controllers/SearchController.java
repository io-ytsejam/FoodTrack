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
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

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

    @GetMapping("/api/search")
    public ResponseEntity recipeSearch(@RequestHeader String name) throws ResourceNotFoundException
    {
        List<RecipeEntity> internalRecipes = new ArrayList<>(recipeEntityRepository.findAllByNameIsLikeIgnoreCase("%" + name + "%"));
        List <Object> externalRecipes = new ArrayList<>();
        Set<Object> allRecipes=new HashSet<>();
        for(int i=1;i<=20;i++) {
            String json = jsonGetRequest("https://api.spoonacular.com/recipes/search?query"+name+"&number=1&offset="+i+"&apiKey=c1393fe58e8741d9b59f20cb092a2a74");
            externalRecipes.add(json);
        }
        allRecipes.add(externalRecipes);
        allRecipes.add(internalRecipes);
        return ResponseEntity.ok(allRecipes);
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
