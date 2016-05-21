package AdventOfCode_9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

// handles verifying the existence of, reading from, and copying data from a 
// file to an object

public class FileParser 
{
    // makes sure the input file exists and can be read from
    public static boolean verifyInputFile(String inputFileName){
        if (inputFileName == null)
            inputFileName = "";
        
        Path fileName = Paths.get(inputFileName);
        boolean fileExists = Files.isRegularFile(fileName) & Files.isReadable(fileName);        
        return fileExists;
    }  

    // copies a file to an arraylist then returns the list
    public static ArrayList<String> getStrings(String fileName)
    {
        ArrayList<String> stringList = new ArrayList<>();
        Scanner inputFile = null;
        
        try
        {
            inputFile = new Scanner(new BufferedReader(new FileReader(fileName)));
            inputFile.useDelimiter("\n");
            
            while (inputFile.hasNext())
                stringList.add(inputFile.next());
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (inputFile != null)
                inputFile.close();
        }

        return stringList;
    }
}