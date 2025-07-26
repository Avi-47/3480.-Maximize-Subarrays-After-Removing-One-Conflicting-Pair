class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        List<List<Integer>> li = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            li.add(new ArrayList<>());
        }
        for (int[] con : conflictingPairs) {
            int a = Math.max(con[0], con[1]);
            int b = Math.min(con[0], con[1]);
            li.get(a).add(b);
        }

        long valid = 0;
        long[] add = new long[n + 1];
        long x = 0;
        int max = 0;
        int secmax = 0;
        for (int i = 1; i <= n; i++) {
            for (int a : li.get(i)) {
                if (a >= max) {
                    secmax = max;
                    max = a;
                } else if (a > secmax) {
                    secmax = a;
                }
            }
            valid += i - max;
            add[max] += max - secmax;
            x = Math.max(x, add[max]);
        }
        return valid + x;
    }
}
