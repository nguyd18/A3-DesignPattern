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

            if (!cmd.hasOption("i")) {
                logger.error("Maze file path is required (-i <file>)");
                return;
            }

            String file_path = cmd.getOptionValue("i");
            MazeLoader loader = new MazeLoader();
            Maze maze = loader.load(file_path);

            if (cmd.hasOption("p")) {
                String input_path = cmd.getOptionValue("p");

                if (input_path == null) {
                    logger.error("A path to the maze is required when using -p flag");
                    return;
                }

                logger.trace("**** Validating path...");
                PathValidator pv = new PathValidator(maze);
                String result = pv.validatePath(input_path);
                System.out.println(result);
            }
            else if (cmd.hasOption("i")) {
                logger.trace("**** Finding path...");
                PathFinder pf = new PathFinder(maze);
                pf.findPath();
            }
            else {
                throw new Exception();
            }
        } catch (ParseException e){
            logger.error("Failed to parse command line arguments");
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }
}
