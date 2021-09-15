package io.github.hurynovich.vj4j.detector.cli;

import io.github.hurynovich.vj4j.base.Detector;
import io.github.hurynovich.vj4j.base.DetectorLoader;
import io.github.hurynovich.vj4j.base.Rect;
import io.github.hurynovich.vj4j.base.Utils;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;

import static java.lang.System.Logger.Level.INFO;

@CommandLine.Command(
        name = "vj4j-detect",
        versionProvider = VersionProvider.class
)
public class DetectorApp implements Callable<Integer> {
    private static final System.Logger log = System.getLogger(DetectorApp.class.getCanonicalName());

    @Parameters(
            paramLabel = "image-file",
            arity = "0..1",
            description = "Path to image file where detection is performed.")
    private File imageFile;

    //TODO find better name for option
    @Option(
            names = {"--cascade-file"},
            descriptionKey = "description.cascade-file"
    )
    private Path cascadeFile;

/*
    TODO implement
    @Option(names = {"--min-object-width"})
    private Integer minObjectWidth;

    @Option(names = {"--max-object-width"})
    private Integer maxObjectWidth;

    @Option(names = {"--min-object-height"})
    private Integer minObjectHeight;

    @Option(names = {"--max-object-height"})
    private Integer maxObjectHeight;
*/

    @Option(names = {"--output-format"})
    private OutputFormat outputFormat = OutputFormat.TEXT;

    @Option(names = {"--output-directory"}, descriptionKey =  "description.output-directory" )
    private File outputDir;

//    @Option(names = { "--log-level" }, descriptionKey = "description.log-level")
//    private Level logLevel;

    @Option(names = { "--help" }, usageHelp = true)
    private boolean helpRequested;

    @Option(names = { "--version" }, versionHelp = true)
    private boolean versionRequested;

    @Override
    public Integer call() throws Exception {
        //TODO use services to load detector
        log.log(INFO, "!!!!!   Start program   !!!!!");

        log.log(INFO, "Loading detector.");
        Detector d = loadDetector();

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

    private Detector loadDetector() {
        //TODO implement finding certain detector instead of first
        var loader = ServiceLoader.load(DetectorLoader.class);
        return loader.findFirst().orElseThrow().load(cascadeFile);
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
        CommandLine cmd = new CommandLine(new DetectorApp());
        cmd.setResourceBundle(ResourceBundle.getBundle("/Messages"));
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }


}
