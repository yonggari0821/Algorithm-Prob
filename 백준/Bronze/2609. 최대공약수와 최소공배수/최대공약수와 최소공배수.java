import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int gcd(int o1, int o2)
    {
        // gcd by 유클리드 호제법
        // gcd (o1, o2) == gcd (o2, o1 % o2)
        while (o2 != 0)
        {
            int tmp = o1 % o2;
            o1 = o2;
            o2 = tmp;
        }
        return o1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, K;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int gcdNum = gcd(N, K);
        ans.append(gcdNum).append('\n');
        int n = N / gcdNum;
        int k = K / gcdNum;
        ans.append(gcdNum * n * k);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}