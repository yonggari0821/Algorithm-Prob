class Solution {
    public int solution(int n) {
        int bc = Integer.bitCount(n);
        while (true) {
            n++;
            if (Integer.bitCount(n) == bc) break;
        }
        return n;
    }
}