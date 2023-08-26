import java.io.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        if (N == 1) return;
        else if (N < 4) ans.append(N);
        else
        {
            int root = (int) (Math.sqrt(N)) + 1;
            int init = N;
            for (int i = 2; i <= N; i++) {
                while (N != 1 && N % i == 0) {
                    N /= i;
                    ans.append(i).append('\n');
                }
            }
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}