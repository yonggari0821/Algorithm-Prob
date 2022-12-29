package B3020개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static int N, H; // N = 장애물의 갯수, H = 동굴의 높이
    public static int[] Bottom; // 석순들의 높이 배열
    public static int[] Top; // 종유석들의 길이 배열

    public static int BS(int[] Obs, int start, int end, int fly)
    {
        while (start <= end)
        {
            int mid = start + (end - start) / 2; // 습관성 오버플로우 방지

            if (fly <= Obs[mid]) end = mid - 1;
            // 벌레가 나는 높이 <= 장애물의 길이 중간값 => 부딪힘
            // => 안 부딪힐 때까지 장애물의 길이 ▼
            else start = mid + 1;
            // 벌레가 나는 높이 > 장애물의 길이 => 안부딪힘
            // => 부딪힐 때까지 장애물의 길이 ▲

            // 결국 end = 부딪히지 않는 최대 높이의 인덱스 = 안 부딪히는 장애물 갯수 - 1
        }
        return Obs.length - end - 1;
        // Obs.length = 전체 장애물의 갯수
        // end = 안 부딪힞는 장애물 갯수 - 1
        // ∴ return 값 = 전체 장애물의 갯수 - (안 부딪히는 장애물의 갯수 - 1) - 1
        // = 부딪히는 장애물의 갯수
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 장애물(석순 + 종유석)의 갯수
        H = Integer.parseInt(st.nextToken()); // 동굴의 높이

        Bottom = new int[N/2 + 1];
        Top = new int[N/2 + 1];

        for (int i = 0; i < N; i++)
        {
            if (i % 2 == 0) // 0을 포함한 짝수번째 => 석순
                Bottom[i/2] = Integer.parseInt(br.readLine());
            else // 홀수번째 => 종유석
                Top[i/2] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(Bottom); // 이분탐색은 항상
        Arrays.sort(Top); // 정렬된 배열에서!

        int min = N; // 디폴트값은 모두 부딪혔을 때의 값 = 장애물의 총 개수 = N
        int cnt = 0;
        for (int height = 1; height < H + 1; height++)
        {
            int tmp = BS(Bottom, 0, N/2, height) + BS(Top, 0, N/2, H - height + 1);
            if (tmp < min) // 여태까지의 최소 부딪힘 횟수보다 더 낮은 부딪힘 횟수 발생시 => 최소 부딪힘 및 cnt 리뉴얼
            {
                min = tmp;
                cnt = 1;
            }
            else if (tmp == min) // 최소 부딪힘 횟수와 같은 부딪힘 횟수 발생 => cnt만 ++
                cnt++;
        }

        System.out.println(min + " " + cnt);
        br.close();
    }
}

// 문제 주의 사항! 한 칸 한 칸 칸 단위로 생각할 것! (선 단위 X)
