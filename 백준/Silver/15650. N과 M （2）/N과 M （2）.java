import java.io.*;
import java.util.*;

public class Main {
    static int N, M, a;
    static StringBuilder ans = new StringBuilder();
    static void func(int picked, int used, int last)
    {
        if (picked == M)
        {
            for (int i = 0; i <= N; i++) {
                if ((used & (1 << i)) != 0) ans.append(i).append(" ");
            }
            ans.append('\n');
            return ;
        }
        for (int i = last + 1; i <= N; i++) {
            func(picked + 1, (used | (1 << i)), i);
        }

    }
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
        func(0, 0, 0);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}