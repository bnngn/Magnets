import java.nio.file.Paths;
import java.util.*;

public class Magnets {
    
    public static void main(String[] args) {
        TreeMap<String, String> links = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        try (Scanner fileScanner = new Scanner(Paths.get("magnets.txt"))) {
            while (fileScanner.hasNextLine()) {
                String link = fileScanner.nextLine();
                String[] splitLink = link.split("\\=");
                String searchLink = "";
                for (int i = 0; i < splitLink.length; i++) {
                    if (!(splitLink[i].contains("magnet") || splitLink[i].contains("urn"))) {
                        // System.out.println(splitLink[i]);
                        searchLink = splitLink[i].replace(".", " ");
                    }
                }
                links.put(searchLink, link);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    //    links.forEach(System.out::println);
       while (true) {
            System.out.print("\nSearch for? (exit, quit or q exits program): ");
            String query = scanner.nextLine();
            System.out.println("");
            if (query.toLowerCase().equals("quit") || query.toLowerCase().equals("exit") || query.toLowerCase().equals("q")) {
                break;
            } else {
                List<String> matchingValues = new ArrayList<>();
                for (Map.Entry<String,String> output:links.entrySet()) {
                    if (isPartialMatch(output.getKey().toLowerCase(), query)) {
                        matchingValues.add(output.getValue());
                    }
                }
                for (String output:matchingValues) {
                    System.out.println(output);
                }
            }
        }
    }

    public static boolean isPartialMatch(String key, String query) {
        String[] search = query.split(" ");
        for (String output:search) {
            if (!key.contains(output)) {
                return false;
            }
        }
        return true;
    }
}