package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14889 {

    public static int N, min; // 총 축구 인원 // 팀 스탯 최소값
    public static int[][] stats; // 시너지 스탯
    public static int[] start; // 스타트팀 번호
    public static int[] link; // 링크팀 번호

    public static void DFS (int last, int pick) // 매개변수 last = 마지막으로 고른 번호, pick 고른 수
    {
        if (pick == N / 2) // N / 2 = 전체 인원의 반이므로 start 팀 완성)
        {
            int li = 0; // link 팀 인덱스 (link팀의 li + 1번째 선수라는 뜻)
            for (int a = 1; a <= N; a++) // 전체 번호 중에
            {
                boolean isin = false; // start팀에 있는 지 여부 판단 위한 임시 불리언 값
                for (int b = 0; b < N / 2; b++) // start 팀 인덱스
                {
                    if (start[b] == a) // start 팀에 있는 번호라면
                    {
                        isin = true; // 불리언 값 참으로 바꿔주고 나가기
                        break;
                    }
                }
                if (isin == false) // start 팀에 없는 번호라면
                {
                    link[li] = a; // link팀에 넣어주기
                    li++;
                }
            }

            int ss = 0; // start 팀 stats합
            int ls = 0; // link 팀 stats합
            // 아래 2중 for문에서는 각 팀의 stats들을 가져와 더해줌
            for (int c = 0; c < N / 2; c++)
            {
                for (int d = c + 1; d < N / 2; d++)
                {
                    int tmpcs = start[c];
                    int tmpds = start[d];
                    int tmpcl = link[c];
                    int tmpdl = link[d];
                    ss += (stats[tmpcs][tmpds] + stats[tmpds][tmpcs]);
                    ls += (stats[tmpcl][tmpdl] + stats[tmpdl][tmpcl]);
                }
            }

            int tmpval = Math.abs(ss - ls); // 양 팀 시너지 스탯 차이값
            min = min < tmpval ? min : tmpval; // 기존 최소값과 비교하여 리뉴얼

            return ;
        }
        for (int i = last + 1; i <= N; i++) // i는 마지막으로 고른 번호보다 큰 번호부터 시작
        {
            start[pick] = i; // 고른 횟수가 인덱스가 되고 i가 값으로 들어감
            DFS(i, pick + 1); // till 고른 횟수 == 전체의 반
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stats = new int[N+1][N+1];
        min = Integer.MAX_VALUE;
        start = new int[N];
        link = new int[N];

        for (int i = 1; i <= N; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
            {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0);

        System.out.println(min);
    }
}
