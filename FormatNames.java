import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FormatNames {
    protected static boolean allUpperCase = false;
    protected static boolean htmlFlag = false;
    protected static Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
    public static void main(String[] args) {
        while (!loadArguments(args));
        sc.close();
        if (htmlFlag)
            formatHTML();
        else 
            format(); // format everything
    }

    /**
     * Check for both input and output required arguments
     * @param listArgs
     * @return boolean
     */
    private static boolean checkRequiredArguments(List<String> listArgs) {
        if (listArgs.size() != 2) {
            System.out.println("You need to enter both input and output files.");
            return false;
        }
        return true;
    }

    /**
     * Load Arguments and files.
     * @param args
     * @return
     */
    private static boolean loadArguments(String[] args) {
        List<String> listArgs = new ArrayList<>(Arrays.asList(args));
        for (String com: args) {
            // flags
            if (com.startsWith("-")) {
                if (com.equals("-u")) {
                    allUpperCase = true;
                } else if (com.equals("-h")) {
                    FilesInOut.loadHTMLTemplate();
                    System.out.println("Template loaded");
                    htmlFlag = true;
                } else {
                    System.out.println("Cannot find flag: " + com);
                }
                listArgs.remove(com);
            }
        }


        if (listArgs.isEmpty()) {
            
            System.out.print("Enter input file: ");  
            String input = sc.nextLine(); //reads string  
            listArgs.add(input);

            System.out.print("Enter output file: ");  
            String output = sc.nextLine(); //reads string  
            listArgs.add(output);
            
            System.out.println();
        } else if (!checkRequiredArguments(listArgs)) {
            listArgs.clear();
            return loadArguments(listArgs.toArray(new String[listArgs.size()]));
        }
        

        // Check for both input and output Arguments and for them to exists
        return FilesInOut.loadFiles(listArgs.get(0), listArgs.get(1));
        
    }

    /**
     * Format names and data
     */
    private static void format() {
        String formatted = "";
        List<String> upperLine;
        for (String line: FilesInOut.getNames()) {
            // Upper case
            upperLine = upper(line);
            String names = String.join(" ", upperLine.subList(0, upperLine.size()));            
            // Date
            String date = line.split(" ")[line.split(" ").length-1];
            String day = date.substring(0, 2);
            String month = date.substring(2, 4);
            String year = date.substring(4, 8);

            formatted = names + " " + day + "/" + month + "/" + year + "\n";

            FilesInOut.write(formatted);

        }

        FilesInOut.close();

        System.out.println("Done.");
    }

    /**
     * Format names and data as html
     */
    private static void formatHTML() { 
        List<String> upperLine; 
        StringBuilder tableHTML = new StringBuilder();
        for (String line: FilesInOut.getNames()) {
            // Upper case
             
            upperLine = upper(line);

            String surname = upperLine.get(upperLine.size() - 1);
            System.out.println(surname);
            String firstName = String.join(" ", upperLine.subList(0, upperLine.size() - 1));
            System.out.println(firstName);

            // Date
            String date = line.split(" ")[line.split(" ").length-1];
            String day = date.substring(0, 2);
            String month = date.substring(2, 4);
            String year = date.substring(4, 8);

            String formattedHTMLline =  "<div class=\"row\"><div class=\"column\"><p>" + firstName + "</p></div><div class=\"column\"><p>" + surname + "</p></div><div class=\"column\"><p>" + day + "/" + month + "/" + year + "</p></div></div>";
            
            tableHTML.append(formattedHTMLline);

        }

        
        FilesInOut.replaceHTML("style.css", tableHTML.toString());
        System.out.println("Done.");
    }

    /**
     * Make it upper
     * @param line
     * @return
     */
    private static List<String> upper(String line) {
        List<String> capLine = new ArrayList<>();
        String[] partsLine = line.split(" ");
        String capPart;


        for (int i=0; i<partsLine.length-1;i++) {

            if (allUpperCase) { // Check if all upper case enabled
                capPart = partsLine[i].toUpperCase();
            } else {
                capPart = partsLine[i].substring(0,1).toUpperCase() + partsLine[i].substring(1).toLowerCase();
            }

            if (partsLine[i].length() == 1) // Add . if length 1
                capPart += ".";

            capLine.add(capPart);
        }

        return capLine;
    }


}
