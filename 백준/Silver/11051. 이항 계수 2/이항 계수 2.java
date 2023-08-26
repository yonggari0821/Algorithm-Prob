import java.io.*;
import java.util.StringTokenizer;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] PascalTriangle = new int[N+1][N+1];
        for (int i = 0; i <= N; i++)
        {
            for (int j = 0; j <= i/2 ; j++)
            {
                if (j == 0) PascalTriangle[i][j] = 1;
                else
                    PascalTriangle[i][j] = (PascalTriangle[i-1][j-1] + PascalTriangle[i-1][j]) % 10007;
                PascalTriangle[i][i-j] = PascalTriangle[i][j];
            }
        }
        ans.append(PascalTriangle[N][K]);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}