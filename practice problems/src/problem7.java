import java.util.*;

public class problem7 {

    static HashMap<String, Integer> queryFreq = new HashMap<>();

    public static void addQuery(String query) {
        queryFreq.put(query, queryFreq.getOrDefault(query, 0) + 1);
    }

    public static List<String> search(String prefix) {

        List<String> result = new ArrayList<>();

        for (String query : queryFreq.keySet()) {

            if (query.startsWith(prefix)) {
                result.add(query);
            }
        }

        result.sort((a, b) -> queryFreq.get(b) - queryFreq.get(a));

        return result.subList(0, Math.min(10, result.size()));
    }

    public static void main(String[] args) {

        addQuery("java tutorial");
        addQuery("javascript guide");
        addQuery("java download");
        addQuery("java tutorial");

        System.out.println(search("jav"));
    }
}