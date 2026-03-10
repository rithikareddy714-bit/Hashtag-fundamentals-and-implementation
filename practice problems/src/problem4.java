import java.util.*;

public class problem4 {

    private HashMap<String, Set<String>> index = new HashMap<>();
    private HashMap<String, Set<String>> documentNgrams = new HashMap<>();
    private int N = 5;

    public void addDocument(String docId, String text) {
        List<String> words = Arrays.asList(text.toLowerCase().split("\\s+"));
        Set<String> ngrams = new HashSet<>();

        for (int i = 0; i <= words.size() - N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(words.get(i + j)).append(" ");
            }
            String gram = sb.toString().trim();
            ngrams.add(gram);

            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }

        documentNgrams.put(docId, ngrams);
    }

    public void analyzeDocument(String docId) {

        Set<String> ngrams = documentNgrams.get(docId);
        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {
            if (index.containsKey(gram)) {
                for (String otherDoc : index.get(gram)) {
                    if (!otherDoc.equals(docId)) {
                        matchCount.put(otherDoc, matchCount.getOrDefault(otherDoc, 0) + 1);
                    }
                }
            }
        }

        System.out.println("Extracted " + ngrams.size() + " n-grams");

        for (String otherDoc : matchCount.keySet()) {
            int matches = matchCount.get(otherDoc);
            double similarity = (matches * 100.0) / ngrams.size();

            System.out.println("Found " + matches + " matching n-grams with \"" + otherDoc + "\"");
            System.out.println("Similarity: " + similarity + "% " + (similarity > 50 ? "(PLAGIARISM DETECTED)" : "(suspicious)"));
        }
    }

    public static void main(String[] args) {

        problem4 detector = new problem4();

        String essay1 = "artificial intelligence is transforming the world with advanced machine learning techniques and data analysis";
        String essay2 = "machine learning techniques and data analysis are part of artificial intelligence transforming the world";
        String essay3 = "the history of ancient civilizations and cultural evolution is fascinating";

        detector.addDocument("essay_123.txt", essay1);
        detector.addDocument("essay_089.txt", essay2);
        detector.addDocument("essay_092.txt", essay3);

        detector.analyzeDocument("essay_123.txt");
    }
}
