import java.io.*;
import java.util.*;

/*
3079번 풀이

총 M 명이 N 개의 입국 심사대에서 입국 심사를 전부 받을 때 최소 시간을 구해야 함

일단,
입국심사시간 최대 10억
상근 + 친구들 숫자 최대 10억
입국 심사대 갯수 최소 1
따라서 최대 10억 * 10억 / 1 == 10해 => long형 필요

일단 입국심사대별 심사 시간을 오름차순으로 정렬

1)
입국 심사 시간에 *2 *3 해가면서 표시해두고 탐색하는 건 말이 안됨 (10해짜리 크기의 배열을 만들어야 함)

1부터 시작해서 곱하기 2씩해서 시간을 늘려가면서
모든 입국심사 시간으로 나눈 몫을 더한 값을 가지고 친구들 수하고 비교해서 더 커질때까지 올리다가
커지면 그 값 / 2 랑 그 값이랑 이분탐색

 */




public class Main {

    static long bs(long left, long right, int target, int[] times)
    {
        long l = left;
        long r = right;
        while (l <= r)
        {
            long mid = (l + r) / 2;
            int cnt = 0;
            for (int i = 0; i < times.length; i++) cnt += (mid / times[i]);
            if (cnt >= target) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 입국심사대 수 // 최대 10만
        int M = Integer.parseInt(st.nextToken()); // 상근 + 친구들 // 최대 10만
        long minTime = 1;
        int[] imagrationCheckTime = new int[N]; // 최대 10억 // int형도 무방
        for (int i = 0; i < N; i++) imagrationCheckTime[i] = Integer.parseInt(br.readLine());
        // 오름차순 정렬
        Arrays.sort(imagrationCheckTime);
        long cnt;
        while (true)
        {
            cnt = 0;
            for (int i = 0; i < N; i++) cnt += minTime / imagrationCheckTime[i];
            if (cnt > M) break;
            else minTime *= 2;
        }
        long left = minTime / 2;
        long right = minTime;
        ans.append(bs(left, right, M, imagrationCheckTime));
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}