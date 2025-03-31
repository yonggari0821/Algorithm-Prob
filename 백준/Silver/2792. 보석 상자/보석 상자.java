import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] jewelNums;
    
    static int binarySearch() {
        int l = 1;
        int r = 1_000_000_000;
        while (l <= r) {
            int mid = (l + r) / 2;
            int servedStudents = 0;
            
            for (int i = 0; i < jewelNums.length; i++) {
                servedStudents += (jewelNums[i] + mid - 1) / mid; // 올림 연산
            }
            
            if (servedStudents <= n) {
                r = mid - 1; // 학생 수가 충분하면 질투심을 더 줄여본다
            } else {
                l = mid + 1; // 학생 수가 부족하면 질투심을 늘려야 한다
            }
        }
        return l;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 아이들의 수
        m = Integer.parseInt(st.nextToken()); // 색상의 수
        jewelNums = new int[m];
        for (int i = 0; i < m; i++) {
            jewelNums[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(binarySearch());
    }
}
