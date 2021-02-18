import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static List<String> names = new ArrayList<String>();
    public static PrintWriter printWriter;

    /**
     * Load files and read lines from input.
     * @param input
     * @param output
     * @return boolean if fails
     */
    public static boolean loadFiles(String input, String output) {
        // Read the content from file
        try (Scanner scannerInput = new Scanner(new File(input))){
            readLines(scannerInput);
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Fails
        } 

        // Create output file if not exists
        try {
            File outputFile = new File(output);
            printWriter = new PrintWriter (outputFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false; // Fails
        }
        
        return true;
        
    }

    /**
     * Read every line from file
     * @param scannerInput
     */
    public static void readLines(Scanner scannerInput) {
        while (scannerInput.hasNextLine()) {
            names.add(scannerInput.nextLine());
        }
    }

    /**
     * All the names list
     * @return 
     */
    public static List<String> getNames() {
        return names;
    }

    /**
     * The printWriter instsance
     */
    public static void write(String s) {
        printWriter.write(s);
    }

}
