import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int x3 = Integer.parseInt(st.nextToken());
            int y3 = Integer.parseInt(st.nextToken());
            int x4 = Integer.parseInt(st.nextToken());
            int y4 = Integer.parseInt(st.nextToken());

            // X
            if (x2 < x3 || y2 < y3 || x4 < x1 || y4 < y1) ans.append('d').append('\n');
            // 점
            else if ( ( (x1 == x4) && ( (y1 == y4) || (y2 == y3) ) ) || ( (x2 == x3) && ( (y1 == y4) || (y2 == y3) ) ) ) ans.append('c').append('\n');
            // 선
            else if ( (x1 == x4) || (y1 == y4) || (x2 == x3) || (y2 == y3) ) ans.append('b').append('\n');
            // 면
            else ans.append('a').append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}