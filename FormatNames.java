import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormatNames {
    protected static boolean allUpperCase = false;
    public static void main(String[] args) {
        if (!loadArguments(args)) {
            System.exit(0); // Exit if fails
        }
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
            // - flags
            if (com.startsWith("-") && com.equals("-u")) {
                allUpperCase = true;
                listArgs.remove(com);
            }
        }

        // Check for both input and output Arguments and for them to exists
        return checkRequiredArguments(listArgs) && FilesInOut.loadFiles(listArgs.get(0), listArgs.get(1));
        
    }

    /**
     * Format names and data
     */
    private static void format() {
        String formatted = "";
        String names;

        for (String line: FilesInOut.getNames()) {
            // Upper case
            names = upper(line);
            
            // Date
            String date = line.split(" ")[line.split(" ").length-1];
            String day = date.substring(0, 2);
            String month = date.substring(2, 4);
            String year = date.substring(4, 8);

            formatted = names + day + "/" + month + "/" + year + "\n";

            FilesInOut.write(formatted);

        }

        FilesInOut.close();

        System.out.println("Done.");
    }

    /**
     * Make it upper
     * @param line
     * @return
     */
    private static String upper(String line) {
        StringBuilder capLine = new StringBuilder();
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
            capLine.append(capPart + " ");
        }

        return capLine.toString();
    }


}
