package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathFormatterTests {
    
    /**
     * Test to see if the canonical path converts to a factorized path
     */
    @Test
    public void canonicalToFactorized() {
        String canonicalPath = "F F F F F F F F F F F";
        String expectedFactorizedPath = "11F";
        String actualFactorizedPath = PathFormatter.factorizedPath(canonicalPath);
        assertEquals(expectedFactorizedPath, actualFactorizedPath, "The path factorization is incorrect!");
    }

    /**
     * Test to see if the factorized path converts to a canonical path
     */
    @Test
    public void factorizedToCanonical() {
        String factorizedPath = "11F";
        String expectedCanonicalPath = "FFFFFFFFFFF";
        String actualCanonicalPath = PathFormatter.convertToCanonical(factorizedPath);
        assertEquals(expectedCanonicalPath, actualCanonicalPath, "The path conversion is incorrect!");
    }


}
