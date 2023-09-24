import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] used;
    static StringBuilder ans = new StringBuilder();
    static void func(int picked, int[] used)
    {
        if (picked == M)
        {
            for (int i = 0; i < M; i++) {
                ans.append(used[i]).append(" ");
            }
            ans.append('\n');
            return ;
        }
        for (int i = 1; i <= N; i++) {
            used[picked] = i;
            func(picked + 1, used);
        }
    }
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
        used = new int[M];
        func(0, used);
        // picked = 고른 숫자 수 [0 ~ M]
        // used = 고른 숫자 확인용 배열
        // last = 전 재귀에서 살펴본 수 => 다음 재귀로 넘어갈 때 꼭 증가해야 함! [1 ~ N]
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}