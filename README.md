# 3480.-Maximize-Subarrays-After-Removing-One-Conflicting-Pair

[![LeetCode - Maximum Score From Removing Substrings](https://img.shields.io/badge/LeetCode-1717-blue.svg)](https://leetcode.com/problems/maximum-score-from-removing-substrings/)
![Difficulty: Medium](https://img.shields.io/badge/Difficulty-Medium-yellow)
![Tag: Greedy](https://img.shields.io/badge/Tag-Greedy-brightgreen)
![Tag: Stack](https://img.shields.io/badge/Tag-Stack-lightgrey)

---

### 🧠 Problem Statement

You are given a string `s` and two integers `x` and `y`. You can perform two types of operations any number of times:

1. Remove substring `"ab"` and gain `x` points.
2. Remove substring `"ba"` and gain `y` points.

**Goal:** Return the **maximum points** you can gain after applying the above operations optimally.

---

### 🚀 Solution Idea

- Use a **greedy approach**: always prioritize removing the substring with the **higher score** first.
- Use a helper function `func(...)` that processes one type of substring and accumulates points.
- Traverse the string once, using two counters to track how many valid pairs you can form and resolve them accordingly.
- Once the first (more profitable) pattern is removed, process the remaining string for the second pattern.

---

### ✅ Code (Java)

```java
class Solution {
    public int func(StringBuilder sb, char f, char s, int x, int y){
        int i = 0, count = 0, a = 0, b = 0;
        while(i < sb.length()){
            if(sb.charAt(i) == f) a++;
            else if(sb.charAt(i) == s){
                if(a > 0){
                    count += x;
                    a--;
                } else b++;
            } else {
                count += Math.min(a, b) * y;
                a = 0; b = 0;
            }
            i++;
        }
        count += Math.min(a, b) * y;
        return count;
    }

    public int maximumGain(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        return x > y ? func(sb, 'a', 'b', x, y) : func(sb, 'b', 'a', y, x);
    }
}
