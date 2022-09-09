package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.handler.WebsiteRedirectionImpl;

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
        target.patternList = first.patternList;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private PatternList patternList;
    @GetMapping("/website/redirection")
    public String getBlockingList(){
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<String> blockingList = new ArrayList<>();
        ArrayList<String> targetList = new ArrayList<>();
        for(BehaviorHandler handler: patternList.getHandlers()){
            if(handler == null)
                continue;
//            System.out.println(handler.getClassName().equals(WebsiteRedirectionImpl.class.getName()));
//            System.out.println(handler.getClassName());
//            System.out.println(WebsiteRedirectionImpl.class.getName());
            if(handler.getClassName().equals(WebsiteRedirectionImpl.class.getName()) && ((WebsiteRedirectionImpl) handler).getTargetUrl() != null){
                blockingList.add(((WebsiteRedirectionImpl)handler).getSourceUrl());
                targetList.add(((WebsiteRedirectionImpl)handler).getTargetUrl());
            }


        }
//        System.out.println(new Gson().toJson(blockingList));

        res.add(blockingList);
        res.add(targetList);
        return new Gson().toJson(res);

    }

}
