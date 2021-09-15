package io.github.hurynovich.violajones;

import io.github.hurynovich.base.Detector;
import io.github.hurynovich.base.OpenCvCascadeLoader;
import io.github.hurynovich.base.Rect;
import io.github.hurynovich.base.Utils;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import static io.github.hurynovich.violajones.OutputFormat.TEXT;
import static java.lang.System.Logger.Level.INFO;

@CommandLine.Command(
        name = "vj4j-detect",
        description = "Detects objects on given image."
)
public class DetectorCliApp implements Callable<Integer> {
    private static System.Logger log = System.getLogger(DetectorCliApp.class.getCanonicalName());

    @Parameters(
            paramLabel = "image-file",
            arity = "0..1",
            description = "Path to image file where detection is performed.")
    private File imageFile;

    //TODO find better name for option
    @Option(
            names = {"--cascade-file"},
            description = "Path to file which contains data to initialize detector."
    )
    private Path cascadeFile;

    @Option(names = {"--min-window-x"})
    private Integer minWindowSizeX;

    @Option(names = {"--max-window-x"})
    private Integer maxWindowSizeX;

    @Option(names = {"--min-window-y"})
    private Integer minWindowSizeY;

    @Option(names = {"--max-window-y"})
    private Integer maxWindowSizeY;

    @Option(names = {"--output-format"})
    private OutputFormat outputFormat = TEXT;

    @Option(
            names = {"-d", "--output-directory"},
            description = "Path to directory where results of detection will be stored. " +
                    "If it is empty then results will be printed to program standard output."
    )
    private File outputDir;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Display a help message.")
    private boolean helpRequested = false;

    @Option(names = { "--log-level" }, description = "Display execution information. ")
    private Level logLevel;

    @Override
    public Integer call() throws Exception {
        //TODO use services to load detector
        log.log(INFO, "!!!!!   Start program   !!!!!");

        log.log(INFO, "Loading detector.");
        Detector d = new OpenCvCascadeLoader().load(cascadeFile);

        log.log(INFO, "Detecting objects.");
        var result = d.detect(loadImage(imageFile));

        if(result.isEmpty()) {
            log.log(INFO, "Objects were not detected.");
            return 0;
        }

        log.log(INFO, "Printing result.");
        for (Rect rect: result) {
            System.out.println(rect);
        }

        log.log(INFO, "Preparing result image.");
        BufferedImage img = loadImage(imageFile);
        for (Rect rect: result) {
            Utils.drawRectangle(img, rect, Color.RED);
        }

        storeImage(img, new File(
                imageFile.getParent(),
                "detected_" + imageFile.getName()
                )
        );


        return 0;
    }

    private static BufferedImage loadImage(File path) {
        if(!path.exists()) throw new RuntimeException("Image file '" + path.getAbsolutePath() + "' was not found.");
        //TODO
        try {
            return ImageIO.read(path);
        } catch (IOException e) {
            //TODO
            throw new RuntimeException();
        }
    }

    private static void storeImage(BufferedImage img, File path) {

        //TODO
        try {
            ImageIO.write(img, "jpg", path);
        } catch (IOException e) {
            //TODO
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new DetectorCliApp()).execute(args);
        System.exit(exitCode);
    }
}
