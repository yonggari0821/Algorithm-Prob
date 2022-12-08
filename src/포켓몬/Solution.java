package 포켓몬;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static int solution(int[] nums) {
        int answer = 0;

        Set picked = new HashSet(); // 해시셋은 순서와 중복을 허용하지 않으므로 같은 게 있으면 자동으로 필터!
        for (int i = 0; i < nums.length; i++)
            picked.add(nums[i]);

        // 필터링 된게 포켓몬 수 절반보다 적으면 겹치는 포켓몬이 그만큼 많아서
        // 고를 수 있는 최대값이 곧 picked.size()가 됨!
        if (picked.size() < nums.length / 2)
            answer = picked.size();
        else
            answer = nums.length / 2;
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,2};
        System.out.println(solution(nums));
    }
}