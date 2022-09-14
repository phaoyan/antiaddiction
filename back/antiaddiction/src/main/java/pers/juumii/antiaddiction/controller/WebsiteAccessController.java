package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.pattern.handler.impl.WebsiteRedirectionList;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class WebsiteAccessController {
    private static WebsiteAccessController first;
    public static boolean isFirst(WebsiteAccessController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(WebsiteAccessController target){
        target.websiteRedirectionList = first.websiteRedirectionList;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private WebsiteRedirectionList websiteRedirectionList;
    @GetMapping("/website/redirection")
    public String getBlockingList(){
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
