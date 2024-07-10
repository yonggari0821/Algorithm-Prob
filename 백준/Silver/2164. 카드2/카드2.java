/*
[백준 2164] 카드2

1번부터 N번까지의 카드
1번이 제일 위에 그리고 N번이 제일 아래인 상태로 놓여 있음.

1. 맨 위 카드 바닥에 버리기
2. 그 다음 맨 위 카드 제일 아래로 옮기기

1234
1번 이후 234
2번 이후 342
1번 이후 42
2번 이후 24
1번 이후 4
=> 마지막 4

그냥 한 번 버리고 한 번은 도로 넣는 큐를 구현하면 될 것 같음
그런데 뭔가 홀수번째는 1을 제외하고는 절때 마지막에 남을 수 없음 등 규칙이 보이기도 함
규칙성을 찾아보면
2의 제곱수일 때는 자기 자신이 마지막에 남으며,
그 외에는 자기보다 작은 2의 제곱수로부터 몇번째인지에 2를 곱한 값이 마지막에 남게 됨.

 */

import java.io.*;
import java.util.*;

public class Main {

    // 일반적인 큐를 이용한 풀이
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        Queue<Integer> q = new LinkedList<>();
//        for (int i = 1; i <= N; i++) {
//            q.offer(i);
//        }
//        while (q.size() > 1)
//        {
//            q.poll();
//            for (Integer e : q) System.out.print(e);
//            System.out.println("");
//            q.offer(q.poll());
//            for (Integer e : q) System.out.print(e);
//            System.out.println("");
//        }
//        System.out.println("마지막 수는");
//        System.out.println(q.poll());
//    }

    // 규칙성을 찾아서 효율화시킨 풀이

    static int getLastNum (int num)
    {
        if (num == 1) return num;
        int t = 1;
        while(t * 2 <= num) t *= 2;
        // 2의 제곱수일때는 자기 자신이 마지막에 남음
        if (t == num) return num;
        // 그 외에는 자기보다 작으면서 가장 큰 2의 제곱수로부터 몇 번째 수인지에 2를 곱한 수가 마지막에 남음
        else
        {
            int order = num - t;
            return 2 * order;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        System.out.println(getLastNum(N));
    }
}

/*
1

1
--------------
2

2
--------------
3

2
--------------
4

4
--------------
5

2
--------------
6

4
--------------
7

6
--------------
8

8
--------------
9

2
--------------
10

4
--------------
11

6
--------------
12

8
--------------
13

10
--------------
14

12
--------------
15

14
--------------
16

16
--------------
*/