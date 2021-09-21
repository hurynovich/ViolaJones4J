package io.github.hurynovich.vj4j.detector.cli;

import io.github.hurynovich.vj4j.detector.api.Detector;
import io.github.hurynovich.vj4j.detector.api.Image;
import io.github.hurynovich.vj4j.detector.api.Settings;
import io.github.hurynovich.vj4j.detector.spi.DetectorLoader;
import io.github.hurynovich.vj4j.detector.api.Rect;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;

import static io.github.hurynovich.vj4j.detector.cli.OutputFormat.IMAGE;
import static io.github.hurynovich.vj4j.detector.cli.OutputFormat.TEXT;

import static java.lang.System.Logger.Level.DEBUG;

@CommandLine.Command(
        name = "vj4j-detect",
        versionProvider = VersionProvider.class,
        resourceBundle = "Messages"
)
public class DetectorApp implements Callable<Integer> {
    private static final System.Logger log = System.getLogger(DetectorApp.class.getCanonicalName());

    @Parameters(
            paramLabel = "image-file",
            arity = "0..1",
            descriptionKey = "description.image-file")
    private File imageFile;

    //TODO find better name for option
    @Option(names = {"--cascade-file"}, descriptionKey = "description.cascade-file")
    private Path cascadeFile;

    //TODO implement
    @Option(names = {"--min-object-width"})
    private Integer minObjectWidth;

    @Option(names = {"--max-object-width"})
    private Integer maxObjectWidth;

    @Option(names = {"--min-object-height"})
    private Integer minObjectHeight;

    @Option(names = {"--max-object-height"})
    private Integer maxObjectHeight;

    @Option(names = {"--output-format"})
    private OutputFormat outputFormat = OutputFormat.TEXT;

    @Option(names = {"--output-directory"}, descriptionKey =  "description.output-directory" )
    private File outputDir;

    @Option(names = { "--help" }, usageHelp = true)
    private boolean helpRequested;

    @Option(names = { "--version" }, versionHelp = true)
    private boolean versionRequested;

    @Override
    public Integer call() throws Exception {
        log.log(DEBUG, "Start loading detector.");
        Detector d = loadDetector();
        log.log(DEBUG, "Detector '{}' was loaded.", d.getClass());

        log.log(DEBUG, "Start detecting objects.");

        Image srcImg = readImage(imageFile);
        final var result = d.detect(srcImg, new Settings(){});
        if(!result.isEmpty()) {
            log.log(DEBUG, "{} objects were detected.", result.size());
        } else {
            log.log(DEBUG, "Objects were not detected.");
            return 0;
        }

        if(outputFormat == TEXT) {
            log.log(DEBUG, "Storing {} result.", TEXT);
            for (Rect rect : result) {
                System.out.println(rect);
            }
        } else if(outputFormat == IMAGE) {
            log.log(DEBUG, "Storing {} result.", IMAGE);
            for (Rect rect : result) {
                drawRectangle(srcImg, rect);
            }
            File targetFile = new File(imageFile.getParent(), "detect_" + imageFile.getName());
            writeImage(srcImg, targetFile);
        }
        log.log(DEBUG, "Result was stored.");

        return 0;
    }

    private Detector loadDetector() {
        //TODO implement finding certain detector instead of first
        var loader = ServiceLoader.load(DetectorLoader.class);
        return loader.findFirst().orElseThrow().load(cascadeFile);
    }

    private static Image readImage(File path) {
        if(!path.exists()) throw new RuntimeException("Image file '" + path.getAbsolutePath() + "' was not found.");
        //TODO implement
        return null;
    }

    private static void writeImage(Image img, File path) {
        //TODO implement
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new DetectorApp());
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }

    public static void drawRectangle(Image img, Rect rectangle) {
        //TODO implement
//        Graphics g = img.getGraphics();
//        g.setColor(drawColor);
//
//        int x1 = rectangle.getA().x;
//        int y1 = rectangle.getA().y;
//        int x2 = rectangle.getB().x;
//        int y2 = rectangle.getB().y;
//
//        g.drawLine(x1, y1, x2, y1);
//        g.drawLine(x2, y1, x2, y2);
//        g.drawLine(x2, y2, x1, y2);
//        g.drawLine(x1, y2, x1, y1);
    }
}
