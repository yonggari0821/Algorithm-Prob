import java.io.*;
import java.util.*;

/*
[1911]_흙길 보수하기_안상준

물 웅덩이의 위치는 0부터 10억까지이므로 이거 크기로 배열 만드는 건 불가능!
단 물 웅덩이의 갯수는 10000개로 배열을 만들든 연산을 하든 가능한 범위!
또한 입력으로 주어지는 웅덩이는 겹치지 않음 => 그러나 널빤지의 크기를 고려해서 짜야 함!

예를 들면 널빤지 크기가 10이고
웅덩이가
1 5
7 10
이라면 널빤지 하나로 웅덩이 2개도 커버 가능함!


 */

public class Main {

    static int N, L;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물 웅덩이 갯수
        L = Integer.parseInt(st.nextToken()); // 널빤지 길이
        int[] start = new int[N];
        int[] end = new int[N];
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
        }
        // 웅덩이가 겹치지 않는다고 했으므로 둘 다 그냥 오름차순 정렬하더라도 순서가 흐뜨러지지 않음!
        Arrays.sort(start);
        Arrays.sort(end);
        int realLeft = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++)
        {
            int left = start[i];
            int right = end[i];
            realLeft = Math.max(realLeft, left);
            while (realLeft < right)
            {
                realLeft += L;
                cnt++;
            }
        }
        ans.append(cnt);
        System.out.println(ans.toString());
    	br.close();
    }
}

/*

 */