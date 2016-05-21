package AdventOfCode_9;

import org.junit.Test;
import static org.junit.Assert.*;

public class FileParserTest {
    
    public FileParserTest() {
    }
    
    @Test
    public void testVerifyInputFile() {
        assertTrue("failed to find an existing file", FileParser.verifyInputFile("input.txt"));
        assertFalse("improperly identified a null file as existing", FileParser.verifyInputFile(null));
        assertFalse("improperly identified a fake file as existing", FileParser.verifyInputFile("fakefile.txt"));
        assertFalse("improperly identified no file as a file", FileParser.verifyInputFile(""));
    }    
}
