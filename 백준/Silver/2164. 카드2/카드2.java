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

큐를 구현하면 될 것 같음

 */

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }
        while (q.size() > 1)
        {
            q.poll();
            q.offer(q.poll());
        }
        System.out.println(q.poll());
    }
}

/*
6

4
--------------
 */