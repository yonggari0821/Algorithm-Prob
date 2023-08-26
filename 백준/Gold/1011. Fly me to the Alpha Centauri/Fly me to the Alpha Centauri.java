import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static long[] nums = new long[46342];
    static void fillNums()
    {
        for (int i = 1; i < 46342; i++) {
            long tmp = nums[i - 1] + 2 * i;
            nums[i] = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        fillNums();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int gap = y - x;
            int half = 0;
            for (int j = 1; j < nums.length; j++)
            {
                if (nums[j] >= gap)
                {
                    half = j;
                    break;
                }
            }
            if ((nums[half] + nums[half - 1]) / 2 >= gap) ans.append(half * 2 - 1);
            else ans.append(half * 2);
            ans.append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

/*
x, y 범위가 Integer.MAX_VALUE 이하라 배열 만들어서 하는 건 불가능!
1+1 = 2
1+1+2+2 = 6
1+1+2+2+3+3 = 12
1+1+2+2+3+3+4+4 = 20
.
.
.
n => n * (n + 1) = 1부터 n까지의 합 x 2
n제곱 + n = 2의 30승
n < 2의 15승 < n + 1

[1*2]
1  1 [1]
2  1 1 [2]
-----------------------------------------------------------------
[2*3]
3  1 1 1 [3]
4  1 2 1 [3]
5  1 2 1 1 [4]
6  1 2 2 1 [4]
-----------------------------------------------------------------
[3*4]
7  1 2 2 1 1 [5]
8  1 2 2 2 1 [5]
9  1 2 3 2 1 [5]
10 1 2 3 2 1 1 [6]
11 1 2 3 2 2 1 [6]
12 1 2 3 3 2 1 [6]
-----------------------------------------------------------------
[4*5]
13 1 2 3 3 2 1 1 [7]
14 1 2 3 3 2 2 1 [7]
15 1 2 3 3 3 2 1 [7]
16 1 2 3 4 3 2 1 [7]
17 1 2 3 4 3 2 1 1 [8]
18 1 2 3 4 3 2 2 1 [8]
19 1 2 3 4 3 3 2 1 [8]
20 1 2 3 4 4 3 2 1 [8]
-----------------------------------------------------------------
[5*6]
21 1 2 3 4 4 3 2 1 1 [9]
...
30 1 2 3 4 5 5 4 3 2 1 [10]
-----------------------------------------------------------------
[46341 * 46342]
46340 * 46341 + 1 [92681] == 2147441940 + 1
2의 31승 1 2 3 ... 3 2 1 [92681] 2의 31승 == 2147483648 < 46340 * 46341 + 46341 == 2147488281
46341 * 46342 [92682]
 */