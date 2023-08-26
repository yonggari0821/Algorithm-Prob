import java.io.*;
import java.util.*;
public class Main {
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
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] nums = new int[T];
        for (int t = 0; t < T; t++) nums[t] = Integer.parseInt(br.readLine());
        Arrays.sort(nums);
        int min = nums[1] - nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = gcd(min, (nums[i] - nums[i-1]));
        }
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int m = 2; m * m <= min; m++)
        {
            if (min % m == 0)
            {
                if (m * m < min)
                {
                    s2.insert(0, min / m + " ");
                }
                s1.append(m + " ");
            }
        }
        ans.append(s1).append(s2).append(min);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}

/*
a 와 b 와 ..... z 까지 모두 나머지가 같은 M이 있다.
a % M = r;
b % M = r;
.
.
.
z % M = r;
이다.

따라서 정렬 후 연속된 수들 간의 차 % M == 0인 M을 찾으면 된다!
예를 들면,
3 (3개 수)
3
4
80004 (8만4)
800000004 (8억4)
이렇게 있다면
8만과 (8억-8만)의 최대공약수(8만)의 약수들이 곧 답이 된다.
=> 2 4 5 8 10 16 20 25 32 40 50 64 80 100 125 128 160 200 250 320 400 500 625 640 800 1000 1250 1600 2000 2500 3200 4000 5000 8000 10000 16000 20000 40000 80000
 */

/*
3
4
80004
800000004
 */