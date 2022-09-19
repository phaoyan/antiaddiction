package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.JsonElement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.juumii.antiaddiction.SpringConfig;
import pers.juumii.antiaddiction.model.util.CMD;
import pers.juumii.antiaddiction.model.util.Paths;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import java.io.IOException;

public class AddToStartupBatImpl implements ImplUnit{

    private String uri;
    public AddToStartupBatImpl(String uri) {
        this.uri = uri;
    }

    @Override
    public void handle() {
        CMD cmd = SpringUtils.getBean(CMD.class);
        try {
            cmd.removeLastLineFromBat(Paths.getStartupBatSrc());
            cmd.addLastLineToBat(Paths.getStartupBatSrc(), "start "+ uri);
            cmd.addLastLineToBat(Paths.getStartupBatSrc(),"exit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        new AddToStartupBatImpl("https:\\\\cn.bing.com\\").handle();
    }
}
