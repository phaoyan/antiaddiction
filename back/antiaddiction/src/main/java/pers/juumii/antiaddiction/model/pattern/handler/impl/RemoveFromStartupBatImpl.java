package pers.juumii.antiaddiction.model.pattern.handler.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.juumii.antiaddiction.SpringConfig;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RemoveFromStartupBatImpl implements ImplUnit{


    private final String uri;
    public RemoveFromStartupBatImpl(String uri) {
        this.uri = uri;
    }
    @Override
    public void handle() {
        try {
            List<String> lines = FileUtils.readLines(new File(Paths.getStartupBatSrc()), StandardCharsets.UTF_8);
            lines.removeIf(e -> e.equals("start " + uri));
            FileUtils.writeLines(new File(Paths.getStartupBatSrc()),lines,false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpringConfig.class);
        new RemoveFromStartupBatImpl("https:\\\\cn.bing.com\\").handle();
    }
}
