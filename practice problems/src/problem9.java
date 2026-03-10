import java.util.*;

public class problem9 {

    static class Transaction {
        int id;
        int amount;
        String merchant;
        String account;
        long time;

        Transaction(int id, int amount, String merchant, String account, long time) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.account = account;
            this.time = time;
        }
    }

    List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void findTwoSum(int target) {
        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : transactions) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);
                System.out.println("Pair → (id:" + other.id + ", id:" + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    public void findTwoSumWithTimeWindow(int target, long windowMillis) {
        HashMap<Integer, List<Transaction>> map = new HashMap<>();

        for (Transaction t : transactions) {
            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                for (Transaction other : map.get(complement)) {
                    if (Math.abs(t.time - other.time) <= windowMillis) {
                        System.out.println("TimeWindow Pair → (id:" + other.id + ", id:" + t.id + ")");
                    }
                }
            }

            map.putIfAbsent(t.amount, new ArrayList<>());
            map.get(t.amount).add(t);
        }
    }

    public void detectDuplicates() {
        HashMap<String, List<Transaction>> map = new HashMap<>();

        for (Transaction t : transactions) {
            String key = t.amount + "_" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t);
        }

        for (String key : map.keySet()) {
            List<Transaction> list = map.get(key);

            if (list.size() > 1) {
                System.out.print("Duplicate → {amount:" + list.get(0).amount + ", merchant:" + list.get(0).merchant + ", accounts:[");
                for (Transaction t : list) {
                    System.out.print(t.account + " ");
                }
                System.out.println("]}");
            }
        }
    }

    public void findKSum(int k, int target) {
        List<Integer> nums = new ArrayList<>();
        for (Transaction t : transactions) nums.add(t.amount);

        List<List<Integer>> result = new ArrayList<>();
        kSumHelper(nums, 0, k, target, new ArrayList<>(), result);

        for (List<Integer> r : result) {
            System.out.println("KSum → " + r);
        }
    }

    private void kSumHelper(List<Integer> nums, int start, int k, int target, List<Integer> path, List<List<Integer>> res) {
        if (k == 0 && target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (k == 0 || start >= nums.size()) return;

        for (int i = start; i < nums.size(); i++) {
            path.add(nums.get(i));
            kSumHelper(nums, i + 1, k - 1, target - nums.get(i), path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {

        problem9 system = new problem9();

        system.addTransaction(new Transaction(1, 500, "Store A", "acc1", 0));
        system.addTransaction(new Transaction(2, 300, "Store B", "acc2", 900000));
        system.addTransaction(new Transaction(3, 200, "Store C", "acc3", 1800000));
        system.addTransaction(new Transaction(4, 500, "Store A", "acc4", 2000000));

        system.findTwoSum(500);

        system.findTwoSumWithTimeWindow(500, 3600000);

        system.detectDuplicates();

        system.findKSum(3, 1000);
    }
}