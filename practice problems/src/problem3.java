import java.util.*;

public class problem3 {

    class DNSEntry {
        String domain;
        String ip;
        long expiry;

        DNSEntry(String d, String i, long ttl) {
            domain = d;
            ip = i;
            expiry = System.currentTimeMillis() + ttl * 1000;
        }
    }

    private final int MAX_CACHE_SIZE = 5;

    private LinkedHashMap<String, DNSEntry> cache =
            new LinkedHashMap<String, DNSEntry>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> e) {
                    return size() > MAX_CACHE_SIZE;
                }
            };

    private int hits = 0;
    private int misses = 0;
    private long totalLookupTime = 0;
    private int requests = 0;

    public synchronized String resolve(String domain) {

        long start = System.nanoTime();

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);
            if (System.currentTimeMillis() < entry.expiry) {
                hits++;
                requests++;
                totalLookupTime += (System.nanoTime() - start);
                return "Cache HIT → " + entry.ip;
            } else {
                cache.remove(domain);
            }
        }

        misses++;
        String ip = queryUpstreamDNS(domain);
        cache.put(domain, new DNSEntry(domain, ip, 300));
        requests++;
        totalLookupTime += (System.nanoTime() - start);

        return "Cache MISS → Query upstream → " + ip + " (TTL: 300s)";
    }

    private String queryUpstreamDNS(String domain) {
        Random r = new Random();
        return "172.217.14." + (200 + r.nextInt(50));
    }

    public void getCacheStats() {
        double hitRate = (requests == 0) ? 0 : (hits * 100.0 / requests);
        double avgTime = (requests == 0) ? 0 : (totalLookupTime / 1000000.0 / requests);

        System.out.println("Hit Rate: " + hitRate + "%");
        System.out.println("Avg Lookup Time: " + avgTime + " ms");
    }

    public static void main(String[] args) throws Exception {

        problem3 dns = new problem3();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));

        Thread.sleep(2000);

        System.out.println(dns.resolve("google.com"));

        dns.getCacheStats();
    }
}