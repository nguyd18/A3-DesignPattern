package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFormatter {
    
    private static final Logger logger = LogManager.getLogger();

    /**
     * Converts path to canonical
     * 
     * @param input_path
     * @return
     */
    public static String convertToCanonical(String input_path) {
        logger.trace("**** Checking if path is canonical");
        boolean isFactorized = false;
        for (char c : input_path.toCharArray()) {
            if (Character.isDigit(c)) {
                logger.trace("**** Path is factorized");
                isFactorized = true;
                break;
            }
        }
        if (isFactorized) {
            StringBuffer canonical_path = new StringBuffer();
            int count = 0;
            for (int i = 0; i < input_path.length(); i++) {
                char current_char = input_path.charAt(i);
                if (Character.isDigit(current_char)) {
                    count = count * 10 + (current_char - '0');
                    logger.trace("**** Read in a " + count);
                }
                else {
                    if (count == 0) {
                        count = 1;
                    }

                    for (int j = 0; j < count; j++) {
                        canonical_path.append(current_char);
                    }
                    count = 0;
                }
            }
            logger.trace("**** Path is now canonical");
            return canonical_path.toString();
        }
        else {
            logger.trace("**** Path is already canonical");
            return input_path;
        }
    }

    /**
     * Converts the canonical path into a factorized path
     * 
     * @param input_path the input canonical path
     * @return the factorized path
     */
    public static String factorizedPath(String input_path) {
        if (input_path.length() == 0) {
            return "";
        }

        String path = input_path.replaceAll("\\s", "");
        logger.trace("**** Path without whitespaces: " + path);
        StringBuffer factorized = new StringBuffer();
        char current_char = path.charAt(0);
        int count = 1;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == current_char) {
                count++;
            }
            else {
                if (count > 1) {
                    factorized.append(count);
                }
                factorized.append(current_char);
                current_char = path.charAt(i);
                count = 1;
                factorized.append(" ");
            }
        }
        
        if (count > 1) {
            factorized.append(count);
        }
        factorized.append(current_char);
        return factorized.toString();
    }
}
