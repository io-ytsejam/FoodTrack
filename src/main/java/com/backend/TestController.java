package backend.main.java.com.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/")
public class TestController {

    private static final int[] Summaries = new int[10];


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView conferenceWeather() {
        for(int i=0;i<Summaries.length;i++)
        {
            Summaries[i]=i;
        }
        Map<String, Object> model = new LinkedHashMap<>();
        model.put("summary", getSummary());
        return new ModelAndView("summary", model);
    }

    private Object getSummary(){
        Random rng=new Random();
        int a= rng.nextInt(9);
        return Summaries[a];
    }
}
