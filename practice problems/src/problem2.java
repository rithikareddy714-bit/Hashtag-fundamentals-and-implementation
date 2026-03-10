import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class problem2 {

    private ConcurrentHashMap<String, AtomicInteger> stock = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, LinkedHashMap<Integer, Integer>> waitingList = new ConcurrentHashMap<>();

    public problem2() {
        stock.put("IPHONE15_256GB", new AtomicInteger(100));
        waitingList.put("IPHONE15_256GB", new LinkedHashMap<>());
    }

    public int checkStock(String productId) {
        if (stock.containsKey(productId)) {
            return stock.get(productId).get();
        }
        return 0;
    }

    public synchronized String purchaseItem(String productId, int userId) {
        if (!stock.containsKey(productId)) return "Product not found";

        AtomicInteger count = stock.get(productId);

        if (count.get() > 0) {
            int remaining = count.decrementAndGet();
            return "Success, " + remaining + " units remaining";
        } else {
            LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
            queue.put(userId, queue.size() + 1);
            return "Added to waiting list, position #" + queue.size();
        }
    }

    public static void main(String[] args) {

        problem2 manager = new problem2();

        System.out.println("checkStock(\"IPHONE15_256GB\") → " + manager.checkStock("IPHONE15_256GB") + " units available");

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));

        for (int i = 0; i < 100; i++) {
            manager.purchaseItem("IPHONE15_256GB", i);
        }

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
    }
}
