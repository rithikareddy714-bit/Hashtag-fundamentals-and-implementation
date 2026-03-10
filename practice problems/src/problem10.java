import java.util.*;

public class problem10 {

    static LinkedHashMap<String, String> L1 =
            new LinkedHashMap<String, String>(5, 0.75f, true) {

                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > 5;
                }
            };

    static HashMap<String, String> L2 = new HashMap<>();

    public static String getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            return "L1 Cache HIT";
        }

        if (L2.containsKey(videoId)) {

            String data = L2.get(videoId);
            L1.put(videoId, data);

            return "L2 Cache HIT → promoted to L1";
        }

        String data = "VideoData";

        L2.put(videoId, data);

        return "L3 Database HIT → added to L2";
    }

    public static void main(String[] args) {

        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video2"));
        System.out.println(getVideo("video1"));
    }
}
