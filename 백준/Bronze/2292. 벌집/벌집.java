import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int num = 1;
        int step = 1;
        if (N != 1) while (num < N) num += (6 * step++);
        ans.append(step);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}