package pers.juumii.antiaddiction.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TempController {

    @PostMapping("/temp")
    public void test(@RequestBody String json){
        System.out.println(json);
    }
}
