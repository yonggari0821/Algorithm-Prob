package B2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static int BS(int[] numArr, int start, int end, int target)
    {
        while (start <= end) // 가능한 공유기 간 최소거리와 최대거리를 가지고 이진탐색
        {
            int mid = start + (end - start) / 2;

            // 탐색하는 거리(mid)로 공유기를 두었을 때 둘 수 있는 최대 공유기 갯수가 놓아야하는 공유기 갯수보다 적으면
            if (MaxWifi(numArr, mid) < target)
            {
                end = mid - 1; // 탐색하는 거리를 줄여서볼 수 있음! 거리를 줄이면 놓을 수 있는 공유기 갯수가 늘어나므로!!
            }
            // 탐색하는 거리로 공유기를 두었을 때 둘 수 있는 최대 공유기 갯수가 놓아야 하는 공유기 갯수보다 같거나 많으면
            else
            {
                start = mid + 1; // 탐색하는 거리를 늘려볼 수 있음!! (till 둘 수 있는 최대 공유기 갯수가 놓아야 하는 공유기 갯수보다 적어질 때 까지!!)
            }
        }
        return start - 1;
    }

    public static int MaxWifi(int[] numArr, int MinDis)
    {
        int cnt = 1;
        int lastput = numArr[0];

        for (int i = 1; i < numArr.length; i++)
        {
            int put = numArr[i];

            if (put - lastput >= MinDis) // 최소 거리보다 공유기를 놓을 곳과 직전에 둔곳 간의 거리가 크거나 같을 때만 공유기를 놓음!
            {
                cnt++;
                lastput = put; // 놓았으면 직전에 둔 곳을 리뉴얼
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집 갯수
        int C = Integer.parseInt(st.nextToken()); // 놓아야 하는 공유기 갯수

        int[] Home = new int[N]; // 집들의 배열

        for (int i = 0; i < N; i++)
        {
            Home[i] = Integer.parseInt(br.readLine()); // 집들의 위치 값 넣어주기
        }

        Arrays.sort(Home); // 정렬 for 이진탐색

        int minlen = 1; // 공유기간 최소 거리
        int maxlen = Home[N-1] - Home[0] + 1; // 공유기간 최대 거리 (C = 2일때의 거리임!)

        System.out.println(BS(Home, minlen, maxlen, C));
    }
}

