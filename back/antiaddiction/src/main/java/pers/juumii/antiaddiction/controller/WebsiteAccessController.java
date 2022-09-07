package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.handler.WebsiteRedirectionImpl;

import java.util.ArrayList;

@RestController
public class WebsiteAccessController extends CORSController{

    @GetMapping("/website/redirection")
    public String getBlockingList(){
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<String> blockingList = new ArrayList<>();
        ArrayList<String> targetList = new ArrayList<>();
        for(BehaviorHandler handler: PatternList.getInstance().getHandlers()){
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
