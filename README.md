## 💥 3480. Maximize Subarrays After Removing One Conflicting Pair

[![LeetCode](https://img.shields.io/badge/LeetCode-3480-blue)](https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair/)
![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)
![Status](https://img.shields.io/badge/Status-Solved-brightgreen)
![Language](https://img.shields.io/badge/Language-Java-blue)
---

### 📘 Problem

You're given an integer `n`, which represents the array `nums = [1, 2, ..., n]`, and an array `conflictingPairs`, where `conflictingPairs[i] = [a, b]` indicates that `a` and `b` cannot coexist in the same subarray.

You're allowed to **remove exactly one conflicting pair**. Your task is to find the **maximum number of non-empty subarrays** of `nums` that do not contain **any** remaining conflicting pair.

---

### 🧠 Key Insight

- We need to maximize the total number of valid subarrays after removing one conflicting pair.
- For each valid subarray `[i, j]`, it must **not** contain both elements of any remaining conflicting pair.

---

### 🚀 Solution Approach

#### 💡 Strategy

- For each `i` from 1 to `n`, we calculate how many subarrays ending at `i` are valid.
- Maintain a list `li[i]` of all `b` such that `(b, i)` is a conflicting pair with `b < i`.
- Track the **maximum and second maximum** of all such `b` values to determine the earliest invalid subarray start.
- Use prefix addition to account for possible subarrays regained by removing one pair.

#### 🧮 Subarray Count

Let’s define:

- `valid`: current valid subarray count.
- `add[max]`: potential gain if we remove the pair with `max` as right endpoint.
- `x`: best possible gain among all such removals.

---

### ✅ Java Code

```java
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
```
Example 2:
```
Input: n = 5, conflictingPairs = [[1,2],[2,5],[3,5]]
Output: 12
```
Remove [1,2], remaining = [[2,5], [3,5]], gives 12 valid subarrays.

📊 Complexity Analysis
Time: O(n + m), where m = conflictingPairs.length

Space: O(n + m), for adjacency list and prefix tracking.

