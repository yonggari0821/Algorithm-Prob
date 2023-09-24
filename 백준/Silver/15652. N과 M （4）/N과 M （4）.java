import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] used;
    static StringBuilder ans = new StringBuilder();
    static void func(int picked, int last)
    {
        if (picked == M)
        {
            for (int i = 1; i <= N; i++) {
                if (used[i] != 0) {
                    int cnt = used[i];
                    while (cnt > 0)
                    {
                        ans.append(i).append(" ");
                        cnt--;
                    }
                }
            }
            ans.append('\n');
            return ;
        }
        for (int i = last; i <= N; i++) {
            used[i]++;
            func(picked + 1, i);
            used[i]--;
        }
    }
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
        used = new int[N + 1];
        func(0,  1);
        // picked = 고른 숫자 수 [0 ~ M] // used 배열의 인덱스로 활용
        // used = 고른 숫자 확인용 배열
        // last = 마지막으로 살펴본 수
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}