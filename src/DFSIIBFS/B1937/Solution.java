package B1937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int[][] bambooMap;
    public static int[][] possible;
    public static int maxlen = 1;

    public static int n;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static int eat(int r, int c)
    {
        if (possible[r][c] != 0) return possible[r][c];
        possible[r][c] = 1;

        for (int i = 0; i < 4; i++)
        {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n)
                continue;
            if (bambooMap[r][c] < bambooMap[nr][nc])
                possible[r][c] = (possible[r][c] < (eat(nr, nc) + 1)) ? (eat(nr, nc) + 1) : possible[r][c];
                // eat 함수가 또 들어가기 때문에 더 이상 주변에 큰 것이 없는 항까지 탐색해서 possible[r][c]가 결정
                // 테케를 예로 들면 첫 번째 줄 9에서 possible[r][c] = 14, 11, 12 와 비교해서 2에서 그치는 것이 아니라
                // 11이 15와 비교되서 먼저 2로 설정된 후 9는 그것과 비교해서 3으로 설정!
                // 마찬가지로 둘 째줄의 4는 4(4) -> 5(3) <- 11(2) <- 15(1)로 4가 됨!
        }
        return possible[r][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        bambooMap = new int[n][n]; // 전체 맵
        possible = new int[n][n]; // DP기법의 메모제이션을 위한 배열 (각 지점에서 갈 수 있는 최대한의 횟수를 나타내게 됨!)

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
            {
                bambooMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (maxlen < eat(i, j))
                    maxlen = eat(i,j);
            }
        }

        /* DP 값 출력용 코드 부분
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.printf("%d ",possible[i][j]);
            }
            System.out.println("");
        }
        */

        System.out.println(maxlen);
    }
}
