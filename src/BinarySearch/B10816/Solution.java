package B10816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static int LBS(int[] numArr, int start, int end, int target)
    {
        while (start <= end)
        {
            int mid = start + ((end - start) / 2);

            if (numArr[mid] >= target) end = mid - 1;
            // 만약 target이 탐색범위의 중앙값보다 작거나 같다면 탐색범위의 왼쪽에 즉 더 낮은 인덱스를 갖는 target이 있을 수 있음(없을 수도 있음!!)
            // 따라서 end값을 mid - 1 로 설정하고 다시 살펴봐야 함!
            else start = mid + 1;
            // target이 탐색범위의 중앙값보다 크다면 탐색범위의 왼쪽에는 더 이상 target이 있을 수 없음
            // 따라서 시작점을 하나 올려서 오른쪽을 살펴봐야함!
        }
        return start;
    }

    public static int HBS(int[] numArr, int start, int end, int target)
    {
        while (start <= end)
        {
            int mid = start + ((end - start) / 2);

            if (numArr[mid] > target) end = mid - 1;
            // 만약  target이 탐색범위의 중앙값보다 작다면 탐색범위의 왼쪽에 아직 target이 있을 수 있음(없을 수도 있음!!)
            // 따라서 end값을 mid - 1 로 설정하고 다시 살펴봐야 함!
            else start = mid + 1;
            // target이 탐색범위의 중앙값보다 크거나 같다면 탐색범위의 오른쪽에 즉 더 높은 인덱스를 갖는 target이 있을 수 있음(없을 수도 있음!!)
            // 따라서 시작점을 하나 올려서 오른쪽을 살펴봐야함!
        }
        return start;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numArr);

        int M = Integer.parseInt(br.readLine());
        int[] Howmany = new int[N];

        StringTokenizer sta = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
        {
            Howmany[i] = Integer.parseInt(sta.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++)
        {
            sb.append(HBS(numArr,0,N-1,Howmany[i]) - LBS(numArr,0,N-1,Howmany[i])).append(" ");
        }

        System.out.println(sb.toString());
    }
}
