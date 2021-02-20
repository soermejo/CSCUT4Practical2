import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    private static List<String> names = new ArrayList<>();
    private static PrintWriter printWriter;
    private static String content;


    private static final String TEMPLATE_PATH = "html/";
    private static final Charset charset = StandardCharsets.UTF_8;

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
            System.out.println(e.getMessage());
            return false; // Fails
        } 

        // Create output file if not exists
        try {
            File outputFile = new File(output);
            printWriter = new PrintWriter (outputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    /**
     * Close printWriter instance
     */
    public static void close() {
        printWriter.close();
    }
    
    
    /**
     * Load html template
     * @return
     */
    public static boolean loadHTMLTemplate() {
        try {
            Path path = Paths.get(TEMPLATE_PATH + "template.html");
            content = new String(Files.readAllBytes(path), charset);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Fails
        }
        return true;

    }
    
    /**
     * Replace html and save in content
     * @param styleLink
     * @param tableHTML
     */
    public static void replaceHTML(String styleName, String tableHTML) {
        
        content = content.replace("{#STYLE_CSS#}", TEMPLATE_PATH + styleName);
        content = content.replace("{#TABLE_HTML#}", tableHTML);
        write(content);
        close();
    }
    

}
