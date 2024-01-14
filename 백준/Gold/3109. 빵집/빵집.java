import java.io.*;
import java.util.*;

/*
[3109]_빵집_안상준

첫 번째 열 = 근처 빵집의 가스관
마지막 열 = 원웅이 빵집

근처 빵집의 가스관과 빵집을 연결하는 파이프를 설치하려고 하는데
중간에 건물(X)이 있으면 파이프를 놓을 수 없고
파이프끼리는 겹칠 수도 없으면
오른쪽 [위/같은 행/아래 행]으로 총 3가지 방향으로 파이프를 놓을 수 있다.

가스관과 빵집을 연결하는 파이프 라인의 최대 개수를 구하여라

.xx..
..x..
.....
...x.
...x.

[처음 생각한 방법]
i - 1 번째에서 왼쪽 위부터 왼쪽 아래까지 3개 순서대로 보면서
o가 있으면 그걸 u로 바꾸고 자기를 o로 바꾸면서 바꿔가기
=> 최대 3의 500만개를 확인해야 됨 => 불가능!

uxx.o
uuxuo
uuuu.
uuux.
.oox.

[두 번째 생각한 방법]
한 줄 마다 오른쪽 위부터 아래 순서대로 가능한 길에 있는 걸 표시해가면서 끝까지 간 경우에는 갯수 세기
=> 반례가 존재해서 틀림 => 방식 자체를 dfs로 바꿀 것!

1xx.1
21x12
3212.
432x.
543x.

[정답인 방법]
최대한 들어가보고 안되면 다시 최대한 가능했던 데까지 빠져나와서 살펴봐야 함!


 */

public class Main {

    static boolean canGo(int[][] grid, int r, int c)
    {
        if (c == grid[0].length - 1) return true;
        if (r >= 1 && grid[r - 1][c + 1] == 0) {
            grid[r - 1][c + 1] = grid[r][c];
            if (canGo(grid, r - 1, c + 1)) return true;
        }
        if (grid[r][c + 1] == 0) {
            grid[r][c + 1] = grid[r][c];
            if (canGo(grid, r, c + 1)) return true;
        }
        if (r < grid.length - 1 && grid[r + 1][c + 1] == 0) {
            grid[r + 1][c + 1] = grid[r][c];
            if (canGo(grid, r + 1, c + 1)) return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        String str;
        int[][] findWay = new int[R][C];
        for (int r = 0; r < R; r++)
        {
            str = br.readLine();
        	for (int c = 0; c < C; c++) findWay[r][c] = str.charAt(c) == 'x' ? -1 : 0;
        }
        for (int r = 0; r < R; r++) findWay[r][0] = r + 1;
//        for (int r = 0; r < R; r++) System.out.println(Arrays.toString(findWay[r]));
//        System.out.println("---------------------------");
        int res = 0;
        for (int r = 0; r < R; r++)
        {
            if (canGo(findWay, r, 0)) res++;
        }
//        for (int r = 0; r < R; r++) System.out.println(Arrays.toString(findWay[r]));
        ans.append(res);
        System.out.println(ans.toString());
    	br.close();
    }
}

/*
15 15
.xxxxxxxxxx....
...x.......xxx.
...x.......x...
..xx.......xx..
...x........xx.
.x.x......x.x..
...x......xx...
.x.x....xxx....
.x....x.x......
.x.....xx.x....
.x..x.xx.......
.....xx........
....x..........
......x........
...............

4
 */