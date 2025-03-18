import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] ans = new int[2];
        int k = 1;
        while (k * k < yellow) k++;
        while (k > 0) {
            if (yellow % k == 0) {
                int max = Math.max((yellow / k), k);
                int min = Math.min((yellow / k), k);
                System.out.println(max + " , " + min);
                if ((max + min) * 2 + 4 == brown) {
                    ans[0] = max + 2;
                    ans[1] = min + 2;
                    break;
                }
            }
            k--;
        }
        return ans;
    }
}