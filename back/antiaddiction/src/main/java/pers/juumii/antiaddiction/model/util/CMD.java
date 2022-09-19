package pers.juumii.antiaddiction.model.util;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.SpringConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CMD {

    public void exec(String cmd){
        try {
            File file = new File(Paths.getBatSrc());
            if(!file.exists())
                file.createNewFile();
            FileUtils.writeStringToFile(file, cmd, StandardCharsets.UTF_8);
            Runtime.getRuntime().exec(Paths.getBatSrc());
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

    public void addLastLineToBat(String path, String line) throws IOException {
        List<String> lines = FileUtils.readLines(new File(path),StandardCharsets.UTF_8);
        lines.add(line);
        FileUtils.writeLines(new File(path), lines);
    }

    public void removeLastLineFromBat(String path) throws IOException {
        List<String> lines = FileUtils.readLines(new File(path),StandardCharsets.UTF_8);
        lines.remove(lines.size()-1);
        FileUtils.writeLines(new File(path), lines);
    }

    public void temporallyAddLineAndRun(String src, String line){
        try {
            addLastLineToBat(src, line);
            Thread.sleep(3000);
            exec(src);
            Thread.sleep(3000);
            removeLastLineFromBat(src);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        CMD cmd = ctx.getBean(CMD.class);
        String path = "D:\\coding\\projects\\applicationProjects\\antiaddiction\\back\\antiaddiction\\src\\main\\resources\\static\\bat\\startup.bat";
        cmd.addLastLineToBat(path, "test");
        cmd.removeLastLineFromBat(path);

    }
}
