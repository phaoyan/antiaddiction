package pers.juumii.antiaddiction.model.pattern.handler.impl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.juumii.antiaddiction.SpringConfig;
import pers.juumii.antiaddiction.model.util.external.CMD;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.IOException;

public class AddToStartupBatImpl implements ImplUnit{

    private final String uri;
    public AddToStartupBatImpl(String uri) {
        this.uri = uri;
    }

    @Override
    public void handle() {
        try {
            CMD.removeLastLineFromBat(Paths.getStartupBatSrc());
            CMD.addLastLineToBat(Paths.getStartupBatSrc(), "start "+ uri);
            CMD.addLastLineToBat(Paths.getStartupBatSrc(),"exit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        new AddToStartupBatImpl("https:\\\\cn.bing.com\\").handle();
    }
}
