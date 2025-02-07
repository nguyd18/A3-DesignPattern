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
        options.addOption("p", "flag", true, "validates path in a given maze");
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            MazeLoader loader = new MazeLoader();
            Maze maze = loader.load(args[1]);
            if (cmd.hasOption("i") && cmd.hasOption("p")) {
                logger.trace("**** Reading the maze from file " + args[1]);
                logger.trace("**** Validating path...");
                PathValidator pv = new PathValidator(maze);
                String result = pv.validatePath(args[3]);
                System.out.println(result);
            }
            else if (cmd.hasOption("i")) {
                logger.trace("**** Reading the maze from file " + args[1]);
                logger.trace("**** Finding path...");
                PathFinder pf = new PathFinder(maze);
                pf.findPath();
            }
            else {
                throw new Exception();
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }
}
