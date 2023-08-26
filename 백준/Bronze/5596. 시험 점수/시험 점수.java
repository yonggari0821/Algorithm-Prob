import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int mk = 0;
        int ms = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) mk += Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) ms += Integer.parseInt(st.nextToken());
        ms = mk > ms ? mk : ms; // 둘 중 큰 값이 ms에 배정됨!
        ans.append(ms);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}