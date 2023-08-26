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
        int k = 0;
        boolean[] eratosthenes = new boolean[N+1];
        for (int i = 2; i <= N; i++)
        {
            if (!eratosthenes[i])
            {
                for (int j = i; j <= N; j += i)
                {
                    if (!eratosthenes[j])
                    {
                        eratosthenes[j] = true;
                        k++;
                        if (k == K)
                        {
                            ans.append(j);
                            bw.write(ans.toString());
                            br.close();
                            bw.flush();
                            bw.close();
                        }
                    }
                }
            }
        }
    }
}
