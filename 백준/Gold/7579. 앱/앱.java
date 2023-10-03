import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 앱 갯수 최대 100개
        M = Integer.parseInt(st.nextToken()); // 확보해야 하는 메모리 최대 1천만
        int[][] app = new int[N][2];

        // n 번째 어플의 메모리 사용량 - 최대 1천만
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) app[i][0] = Integer.parseInt(st.nextToken());

        // n 번째 어플의 비활성화 비용 - 최대 100
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) app[i][1] = Integer.parseInt(st.nextToken());

        // 풀이 //
        /*
        비활성화 비용 최소화
        줄이는 메모리 사용량 최대화 till Over M

        비활성화 비용을 기준으로 오름차순 정렬 후
        dp 배열을 비활성화 비용 * 어플 갯수 만큼 만들어서 가능한 모든 비활성화 비용에서의 줄이는 메모리 비용을 메모해두고
        앞에서부터 탐색해서 M을 넘어가는 가장 작은 메모리 비용 찾아서 출력하기
        => 배낭문제 풀 듯이 풀기
         */
        Arrays.sort(app, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o2[0] - o1[0];
                return o1[1] - o2[1];
            }
        });
        int[][] dp = new int[N][10001]; // 최대 100개의 앱 && 최대 100의 개당 비활성화 비용 => 최대 10000의 비활성화 비용
        // 첫 번째 어플의 비활성화 비용(가장 작은 비활성화 비용)이상의 모든 비활성화 비용에 첫번째 어플의 메모리 사용량 표시
        for (int i = app[0][1]; i <= 10000; i++) dp[0][i] = app[0][0];

        // 두 번째 부터 마지막 어플까지 아래와 같은 방식으로 현재 어플의 사용량 반영 O/X
        for (int appIdx = 1; appIdx < N; appIdx++) // 비활성화 비용이 적은 어플부터 순회하면서
        {
            for (int dpIdx = 0; dpIdx < app[appIdx][1]; dpIdx++) {
                dp[appIdx][dpIdx] = dp[appIdx - 1][dpIdx];
            }
            for (int dpIdx = app[appIdx][1]; dpIdx <= 10000; dpIdx++)
            {
                // 현재 어플을 비활성화하는 것과 비활성화하지 않는 것을 비교해서 더 큰 값으로 리뉴얼 해두기
                dp[appIdx][dpIdx] = Math.max(dp[appIdx - 1][dpIdx - app[appIdx][1]] + app[appIdx][0], dp[appIdx - 1][dpIdx]);
            }
        }
        for (int i = 0; i <= 10000; i++)
        {
            if (dp[N - 1][i] >= M)
            {
                ans.append(i);
                break;
            }
        }
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}

/*
[case 1]
7 120
20 91 92 93 94 95 100
1 2 2 2 2 2 2
=> 3

[case 2]
19 20169
240 2560 434 6 31 577 500 2715 2916 952 2490 258 1983 1576 3460 933 1660 2804 2584
82 77 81 0 36 6 53 78 49 82 82 33 66 8 60 0 98 91 93
=> 484
 */