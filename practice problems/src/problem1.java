// problem 1
import java.util.*;

class problem1 {
    private Map<String, Integer> usernameToId = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    public problem1(Map<String, Integer> existingUsers) {
        usernameToId.putAll(existingUsers);
    }

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !usernameToId.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String candidate = username + i;
            if (!usernameToId.containsKey(candidate)) {
                suggestions.add(candidate);
            }
        }
        String modified = username.replace("_", ".");
        if (!usernameToId.containsKey(modified)) {
            suggestions.add(modified);
        }
        return suggestions;
    }

    public String getMostAttempted() {
        String result = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    public void register(String username, int userId) {
        usernameToId.put(username, userId);
    }

    public static void main(String[] args) {
        Map<String, Integer> users = new HashMap<>();
        users.put("john_doe", 1);
        users.put("admin", 2);

        problem1 checker = new problem1(users);

        System.out.println(checker.checkAvailability("john_doe"));
        System.out.println(checker.checkAvailability("jane_smith"));
        System.out.println(checker.suggestAlternatives("john_doe"));
        System.out.println(checker.getMostAttempted());
    }
}

