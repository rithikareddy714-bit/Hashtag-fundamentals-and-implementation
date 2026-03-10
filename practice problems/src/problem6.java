import java.util.*;
import java.util.concurrent.*;

public class problem6 {

    class TokenBucket {
        int tokens;
        int maxTokens;
        long lastRefillTime;
        int refillRate;

        TokenBucket(int maxTokens, int refillRate) {
            this.maxTokens = maxTokens;
            this.tokens = maxTokens;
            this.refillRate = refillRate;
            this.lastRefillTime = System.currentTimeMillis();
        }
    }

    private ConcurrentHashMap<String, TokenBucket> clients = new ConcurrentHashMap<>();
    private final int LIMIT = 1000;
    private final int REFILL_RATE = 1000;
    private final long HOUR = 3600000;

    private void refill(TokenBucket bucket) {
        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefillTime;

        if (elapsed > 0) {
            int tokensToAdd = (int) (elapsed * bucket.refillRate / HOUR);
            if (tokensToAdd > 0) {
                bucket.tokens = Math.min(bucket.maxTokens, bucket.tokens + tokensToAdd);
                bucket.lastRefillTime = now;
            }
        }
    }

    public synchronized String checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT, REFILL_RATE));
        TokenBucket bucket = clients.get(clientId);

        refill(bucket);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return "Allowed (" + bucket.tokens + " requests remaining)";
        } else {
            long retry = (HOUR - (System.currentTimeMillis() - bucket.lastRefillTime)) / 1000;
            return "Denied (0 requests remaining, retry after " + retry + "s)";
        }
    }

    public void getRateLimitStatus(String clientId) {
        TokenBucket bucket = clients.get(clientId);
        if (bucket == null) {
            System.out.println("Client not found");
            return;
        }

        int used = bucket.maxTokens - bucket.tokens;
        long reset = bucket.lastRefillTime + HOUR;

        System.out.println("{used: " + used + ", limit: " + bucket.maxTokens + ", reset: " + reset + "}");
    }

    public static void main(String[] args) {

        problem6 limiter = new problem6();

        System.out.println(limiter.checkRateLimit("abc123"));
        System.out.println(limiter.checkRateLimit("abc123"));

        for (int i = 0; i < 1000; i++) {
            limiter.checkRateLimit("abc123");
        }

        System.out.println(limiter.checkRateLimit("abc123"));

        limiter.getRateLimitStatus("abc123");
    }
}
