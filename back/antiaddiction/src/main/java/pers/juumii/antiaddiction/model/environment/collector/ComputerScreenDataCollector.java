package pers.juumii.antiaddiction.model.environment.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerScreenData;
import pers.juumii.antiaddiction.model.util.Paths;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
@Service
public class ComputerScreenDataCollector implements ComputerDataCollector{

    @Value("${computerScreenShotScale}")
    private double scale;
    private ComputerScreenDataCollector(){}
    @Override
    public Environment collect() {
        try {
            ComputerEnvironment res = new ComputerEnvironment();
            File src = new File(Paths.getScreenSrc() + "/" + LocalDateTime.now().toString().split("\\.")[0].replace(':','-') + ".jpg");
            if(!src.exists())
                src.createNewFile();
            ComputerScreenData data = new ComputerScreenData(src.toString());
            Dimension size = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenShot = (new Robot()).createScreenCapture(new Rectangle(size));
            int scaledHeight = (int) (size.getHeight() * scale);
            int scaledWidth = (int) (size.getWidth() * scale);
            Image zipped = screenShot.getScaledInstance(scaledWidth,scaledHeight,Image.SCALE_AREA_AVERAGING);
            BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(zipped, 0, 0, null);
            ImageIO.write(outputImage, "jpg", src);

            res.mount(data);
            return res;
        } catch (AWTException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
