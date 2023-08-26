import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] student = new int[7][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());
            student[grade][gender]++;
        }
        int room = 0;
        for (int i = 1; i <= 6; i++) {
            room += ((student[i][0] / 2) + (student[i][0] % 2));
            room += ((student[i][1] / 2) + (student[i][1] % 2));
        }
        ans.append(room);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}