package 피보나치수;

import java.util.ArrayList;

public class Solution {
    public static int solution(int n) {
        int answer = 0;

        ArrayList<Integer> fibonacci = new ArrayList<>();

        for (int i = 0; i <= n; i++)
        {
            if (i == 0 || i == 1)
                fibonacci.add(i % 1234567);
            else
                fibonacci.add((fibonacci.get(i-1) + fibonacci.get(i-2)) % 1234567);
        }

        answer = fibonacci.get(n);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(100000));
    }
}
