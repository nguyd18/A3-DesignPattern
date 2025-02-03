package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        options.addOption("i", "flag", true, "load maze file");
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                logger.trace("**** Reading the maze from file " + args[1]);
                PathFinder pf = new PathFinder(args[1]);
                // BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                // String line;
                // while ((line = reader.readLine()) != null) {
                //     for (int idx = 0; idx < line.length(); idx++) {
                //         if (line.charAt(idx) == '#') {
                //             logger.debug("WALL ");
                //         } else if (line.charAt(idx) == ' ') {
                //             logger.debug("PASS ");
                //         }
                //     }
                //     logger.debug(System.lineSeparator());
                // }
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.trace("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
