package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.pattern.handler.impl.WebsiteRedirectionList;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class WebsiteAccessController {

    @GetMapping("/website/redirection")
    public String getBlockingList(){
        WebsiteRedirectionList websiteRedirectionList = SpringUtils.getBean(WebsiteRedirectionList.class);
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<String> blockingList = new ArrayList<>();
        ArrayList<String> targetList = new ArrayList<>();

        for(String pair: websiteRedirectionList.getWebsiteRedirectionList()){
            blockingList.add(pair.split(" ")[0]);
            targetList.add(pair.split(" ")[1]);
        }

        res.add(blockingList);
        res.add(targetList);
        return new Gson().toJson(res);
    }

}
