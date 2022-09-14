package pers.juumii.antiaddiction.model.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.SpringConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class CMD {

    @Autowired
    private Paths paths;

    public void exec(String cmd){
        try {
            File file = new File(paths.getBatSrc());
            if(!file.exists())
                file.createNewFile();
            FileUtils.writeStringToFile(file, cmd, StandardCharsets.UTF_8);
            Runtime.getRuntime().exec(paths.getBatSrc());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String path){
        if(path.contains(" "))
            exec("start \" \" \""+path+"\"");
        else exec("start "+path);
    }

    public void runBat(String path, String... arg){}



    public void taskKill(String processName){
        exec("cmd /c wmic process where name='" + processName + "'  delete");
    }

    public void createFile(String path){

    }

    public void deleteFile(){

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        CMD cmd = ctx.getBean(CMD.class);
        cmd.run("D:\\games\\Genshin Impact\\launcher.exe");
    }
}
