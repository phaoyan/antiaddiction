package pers.juumii.antiaddiction.model.util.external;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CMD {

    public static void exec(String cmd){
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

    public static void exec(List<String> cmdLines){
        StringBuilder cmd = new StringBuilder();
        for(String line: cmdLines)
            cmd.append(line).append("\n");
        exec(cmd.toString());
    }

    public static void execAsAdmin(List<String> cmdLines){
        cmdLines.add(0,"@cd/d\"%~dp0\"&(cacls \"%SystemDrive%\\System Volume Information\" >nul 2>nul)||(start \"\" mshta vbscript:CreateObject^(\"Shell.Application\"^).ShellExecute^(\"%~nx0\",\" %*\",\"\",\"runas\",1^)^(window.close^)&exit /b)\n");
        exec(cmdLines);
    }

    public static void run(String path){
        if(path.contains(" "))
            exec("start \" \" \""+path+"\"");
        else exec("start "+path);
    }

    public static void runBat(String path, String... arg){}



    public static void taskKill(String processName){
        exec("cmd /c wmic process where name='" + processName + "'  delete");
    }

    public static void createFile(String path){

    }

    public static void deleteFile(){

    }

    public static void addLastLineToBat(String path, String line) throws IOException {
        List<String> lines = FileUtils.readLines(new File(path),StandardCharsets.UTF_8);
        lines.add(line);
        FileUtils.writeLines(new File(path), lines);
    }

    public static void removeLastLineFromBat(String path) throws IOException {
        List<String> lines = FileUtils.readLines(new File(path),StandardCharsets.UTF_8);
        lines.remove(lines.size()-1);
        FileUtils.writeLines(new File(path), lines);
    }

    public static void temporallyAddLineAndRun(String src, String line){
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
        CMD.run("D:\\coding\\projects\\applicationProjects\\antiaddiction\\back\\antiaddiction\\src\\main\\resources\\static\\bat\\test.bat");
    }
}
