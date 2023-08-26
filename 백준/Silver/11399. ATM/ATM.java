import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] minute = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) minute[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(minute);
        for (int i = 1; i < minute.length; i++) minute[i] += minute[i - 1];
        int sum = 0;
        for (int i = 0; i < minute.length; i++) sum += minute[i];
        ans.append(sum);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}