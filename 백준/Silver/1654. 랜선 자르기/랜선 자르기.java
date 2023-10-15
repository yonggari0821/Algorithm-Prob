import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static long maxLAN = 0, maxLen = 1;
    static void binarySearch(long left, long right, int targetCount, int[] targets)
    {
        while (left < right)
        {
            int count = 0;
            long mid = left + (right - left) / 2; // 잘라보려는 랜선 길이
            for (int i = 0; i < targets.length; i++) {
                count += (targets[i] / mid);
            }
            // 목표치와 같거나 더 많은 랜선을 만들 수 있는 경우 => 아직 길이를 더 늘려봐도 됨!
            // 그런데 이미 가지고 있는 랜선 중 가장 긴 랜선의 길이 이상으로는 못 늘림!
            // 그리고 늘릴 때 mid + 1로 하므로 원하는 값보다 하나 큰 값이 최종적으로 나오게 될 것!
            if (count >= targetCount)
            {
                left = mid + 1;
            }
            else // 목표치보다 더 적은 랜선을 만들 수 있는 경우 => 현재 길이를 줄여야 함 // 언제까지 ? => 1까지
            {
                right = mid;
            }
        }
        maxLen = left;
    }


    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maxLAN = 0;
        int[] lanCables = new int[K];
        for (int k = 0; k < K; k++) {
            lanCables[k] = Integer.parseInt(br.readLine());
            maxLAN = Math.max(maxLAN, lanCables[k]);
        }
        binarySearch(0, maxLAN + 1, N, lanCables);
        ans.append(maxLen - 1);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}