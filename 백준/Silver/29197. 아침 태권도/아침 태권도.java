import java.io.*;
import java.util.*;

/*
백준 29197 아침태권도
(0,0)에 사범님이 있고,
(x, y)까지 선분(직선아님!)을 그었을 때 같은 선분에 있는 애들 중에는 1명만 열심히 함
열심히 하는 애들의 수를 구해야 함

1 <= N <= 2 x 10의 5승
-(10의 4승) <= X, Y <= 10의 4승
똑같은 점 X

같은 선분에 있음 == 기울기가 같음 + 속한 사분면이 같음

ax + by = c라고 가정할 때,
0,0에서 출발하므로 c는 무조건 0;

그럼 이제
by = -ax
y = (-b/a)(x)
가 되는데
이 때 (-b/a)가 기울기!

그리고 사분면과 x선위 y선위도 다 포함해서 8가지의 케이스로 나눌 것!
*/

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static double[][] xy;

    static Set<Double> set1 = new HashSet<>();
    static Set<Double> set2 = new HashSet<>();
    static Set<Double> set3 = new HashSet<>();
    static Set<Double> set4 = new HashSet<>();
    static boolean set5 = false;
    static boolean set6 = false;
    static boolean set7 = false;
    static boolean set8 = false;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        xy = new double[N][2];
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            xy[i][0] = Integer.parseInt(st.nextToken());
            xy[i][1] = Integer.parseInt(st.nextToken());
            if (xy[i][0] > 0 && xy[i][1] > 0) {
                set1.add((double)(xy[i][1] / xy[i][0]));
//                System.out.println("case1");
            }
            else if (xy[i][0] > 0 && xy[i][1] < 0) {
                set2.add((double)(xy[i][1] / xy[i][0]));
//                System.out.println("case2");
            }
            else if (xy[i][0] < 0 && xy[i][1] > 0) {
                set3.add((double)(xy[i][1] / xy[i][0]));
//                System.out.println("case3");
            }
            else if (xy[i][0] < 0 && xy[i][1] < 0) {
                set4.add((double)(xy[i][1] / xy[i][0]));
//                System.out.println("case4");
            }
            else if (xy[i][0] == 0 && xy[i][1] > 0 && !set5) {
                cnt++;
                set5 = true;
//                System.out.println("case5");
            }
            else if (xy[i][0] == 0 && xy[i][1] < 0 && !set6) {
                cnt++;
                set6 = true;
//                System.out.println("case6");
            }
            else if (xy[i][0] > 0 && xy[i][1] == 0 && !set7) {
                cnt++;
                set7 = true;
//                System.out.println("case7");
            }
            else if (xy[i][0] < 0 && xy[i][1] == 0 && !set8) {
                cnt++;
                set8 = true;
//                System.out.println("case8");
            }
        }

        cnt += set1.size();
        cnt += set2.size();
        cnt += set3.size();
        cnt += set4.size();
        System.out.println(cnt);
    }
}