package pers.juumii.antiaddiction.controller;

import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.util.SpringUtils;

@CrossOrigin
@RestController
public class TempController {

    @PostMapping("/temp")
    public void test(@RequestBody String json){
    }
}
