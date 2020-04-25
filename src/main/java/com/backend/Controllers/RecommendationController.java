package com.backend.Controllers;

import com.backend.Models.PersonEntity;
import com.backend.Models.RecommendationEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Repositories.RecommendationEntityRepository;
import com.backend.Security.JwtTokenUtil;
import com.backend.Services.UserServiceImpl;
import net.minidev.json.JSONObject;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
public class RecommendationController {

    @Autowired
    RecommendationEntityRepository recommnedationRepository;

    @Autowired
    PersonEntityRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/index/recs")
    public ResponseEntity getRecs(@RequestHeader String token) throws ResourceNotFoundException
    {
        String username=jwtTokenUtil.getUsernameFromToken(token);
        List<RecommendationEntity> array=new ArrayList<>();
        PersonEntity personEntity=userRepository.findByNickname(username);

        array.addAll(personEntity.getRecommendationEntities());

        Set<String> jsons_bournes=new HashSet<>();
        for (RecommendationEntity r: array)
        {
            jsons_bournes.add(jsonGetRequest("https://api.spoonacular.com/recipes/"+r.getRecommendation_id()+"/similar?apiKey=APIKEY&number=3"));
        }

        return ResponseEntity.ok().body(jsons_bournes);
    }

    @PostMapping("/index/search")
    public ResponseEntity recipeSearch(@RequestHeader Long id, @RequestHeader String token) throws ResourceNotFoundException
    {
        String username=jwtTokenUtil.getUsernameFromToken(token);
        PersonEntity personEntity=userRepository.findByNickname(username);
        RecommendationEntity recommendationEntity=new RecommendationEntity(id);
        if(!recommnedationRepository.findById(id).equals(recommendationEntity))
        {
            recommnedationRepository.save(recommendationEntity);
        }
        if(!personEntity.getRecommendationEntities().contains(recommendationEntity))
        {
            personEntity.getRecommendationEntities().add(recommendationEntity);
        }

        userRepository.save(personEntity);

        String json=jsonGetRequest("https://api.spoonacular.com/recipes/"+id+"/information?apiKey=APIKEY&number=10");
        return ResponseEntity.ok(json);
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
