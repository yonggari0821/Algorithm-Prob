package BinarySearch.B1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int BS (int[] numArr, int target)
    {
        int[]dp = new int[numArr.length]; // 더한 값을 저장할 배열 생성
        dp[0] = numArr[0]; // 더한 값의 첫 값은 그냥 배열의 첫 값과 같음!!
        for (int i = 1; i < numArr.length; i++)
            dp[i] = dp[i - 1] + numArr[i]; // DP 방식으로 더한 값들 저장!!
        // DP 배열의 마지막 값 즉, 다 더한 값이 target보다 작으면 부분합으로 S 이상 만들기 불가!!
        if (dp[numArr.length - 1] < target)
            return 0;

        int start = 0; // 부분합 시작점
        int end = 0; // 부분합 끝점
        int minlen = Integer.MAX_VALUE; // 부분의 길이 // 디폴트 값은 Integer Max 값

        // 처음엔 end만 옮겨주면서 부분합이 target보다 커질때까지 옮기기 (단, end는 배열 전체 길이보단 작아야 함!)
        while (end < dp.length && dp[end] < target)
            end++;
        if (dp[end] >= target && end + 1 < minlen) // end + 1이 부분합의 길이
            minlen = end + 1;

        // end값이 부분합이 target보다 커질 때 까지 옮겨놨으면
        // 그 이후로는 부분합이 target 이상을 유지 못 할때까지 start 옮겨주기 (단, start < end 이어야 함)
        // 그리고 나서 end - start + 1 즉, 새로운 부분합의 길이를 minlen과 비교해서 리뉴얼 or 패스
        // start 값을 유지한 채 이번엔 end 값을 다시 옮겨서 부분합이 target보다 커질때를 만들어 줌
        // 그리고 나서 end - start + 1 즉, 새로운 부분합의 길이를 minlen과 비교해서 리뉴얼 or 패스
        // if 부분합의 길이 >= minlen : 패스 // else 부분합의 길이 < minlen : 리뉴얼
        while (end < dp.length)
        {
            while (end < dp.length && start < end && dp[end] - dp[start] >= target)
                start++;
            if (end - start + 1 < minlen)
                minlen = end - start + 1;
            while (end < dp.length && dp[end] - dp[start] < target)
                end++;
            if (end - start + 1 < minlen)
                minlen = end - start + 1;
        }

        return minlen;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수들의 갯수
        int S = Integer.parseInt(st.nextToken()); // 기준 값

        int[] numArr = new int[N];
        StringTokenizer sta = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numArr[i] = Integer.parseInt(sta.nextToken());

        System.out.println(BS(numArr, S));
    }

}
