package pers.juumii.antiaddiction;



import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.handler.WebsiteRedirectionImpl;
import pers.juumii.antiaddiction.model.util.IOCContainer;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;


public class Main {



    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
//        OverallEnvironment environment = new OverallEnvironment();
//        MomentaryBehavior behavior = new MomentaryBehavior();
//        behavior.setName("testName");
//        behavior.setDescription("testDescription");
//        behavior.setMoment(LocalDateTime.now());
//        behavior.setOverallEnvironment(environment);
//        System.out.println(JSON.toJSONString(behavior));

//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        service.scheduleAtFixedRate(()->{
//            System.out.println("test");
//        }, 0,10, TimeUnit.SECONDS);

//        System.out.println("90".split(",")[0]);

//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        System.out.println(JSON.toJSONString(now));
//        System.out.println(JSON.parseObject(JSON.toJSONString(now), LocalDateTime.class));

//        System.out.println(LocalDateTime.parse(LocalDateTime.parse(JSON.parseObject(JSON.toJSONString(now),LocalDateTime.class).toString()).toString()));

//        System.out.println(MathRelated.getRandomString(4));;

//        try {
//            new File(Paths.HISTORY_PATH + "/" + LocalDate.now() + ".json").createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        File src = new File("D:\\coding\\javaProject\\springProject\\demo\\src\\main\\resources\\static\\json\\test.json");
//        JSONObject object = new JSONObject();
//        object.put("name", "hello");
//        FileUtils.writeStringToFile(src, object.toJSONString(), StandardCharsets.UTF_8);
//        JSONObject object2 = JSON.parseObject(FileUtils.readFileToString(src, StandardCharsets.UTF_8));
//        System.out.println(object2);

//        System.out.println(LocalDateTime.now());
//
//
//        System.out.println(JSON.toJSONString(LocalDateTime.now()));


//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString());


//        ComputerProcess process = new ComputerProcess("msedge.exe", 10);
//        EnvironmentData data = process;
//        String json = new Gson().toJson(data);
//        System.out.println(json);
//        ComputerProcess copy = new Gson().fromJson(json, ComputerProcess.class);
//        System.out.println(copy.getProcessName() + copy.getMemoryUsage() + copy.getIdCode());
//        EnvironmentData copyData = new Gson().fromJson(json, ComputerProcess.class);
//        System.out.println(copyData.getIdCode() + copyData.getClassName());

//        System.out.println(String.class);
//        ComputerProcess computerProcess = new ComputerProcess("msedge.exe", 10);
//        System.out.println(computerProcess.getClass().getMethods()[4].getName());
//        System.out.println(computerProcess.getProcessName());
//        ComputerProcess.class.getMethods()[4].invoke(computerProcess, "idea.exe");
//        System.out.println(computerProcess.getProcessName());

//        System.out.println("hello git");

//        IOCContainer.initialize();
//
//        WebsiteRedirectionImpl impl = new WebsiteRedirectionImpl();
//        impl.setSourceUrl("http://www.jyeoo.com/");
//        impl.setTargetUrl("https://cn.bing.com/");
//        PatternList.getInstance().setHandler(0,impl);

        File file = new File(Paths.DATA_LIST_PATH);
        System.out.println(file.getAbsolutePath());

    }


}
