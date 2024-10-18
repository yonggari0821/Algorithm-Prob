import java.io.*;
import java.util.*;

/*
백준 14003
가장 긴 증가하는 부분 수열 5

[문제]
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어,
수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고,
길이는 4이다.
*/

public class Main {
    // 이진 탐색 함수: LISMin 배열에서 적절한 위치를 찾는다
    static int binarySearch(int l, int r, int t)
    {
        while (l <= r)
        {
            int m = l + (r - l) / 2;  // 중간 인덱스 계산
            if (t <= LISMin[m]) r = m - 1;  // 타겟이 중간값 이하면 왼쪽 탐색
            else l = m + 1;  // 타겟이 중간값보다 크면 오른쪽 탐색
        }
        return r;  // 적절한 위치 반환
    }

    static int N;  // 수열의 길이
    static int[] A;  // 입력 수열
    static int[] LIS;  // 각 원소의 LIS 길이
    static int[] LISMin;  // 각 길이에 대한 최소 끝 값
    static int[] WhereLISMin;  // LISMin의 각 값의 원래 위치
    static int[] Tracing;  // LIS 추적을 위한 배열

    static int maxLen = 1;  // 현재 가장 긴 증가하는 부분 수열의 길이
    static int maxIdx = 1;  // 현재 가장 긴 증가하는 부분 수열의 마지막 원소 인덱스

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 수열의 길이 입력

        // 배열 초기화
        A = new int[N + 1];
        LIS = new int[N + 1];
        LISMin = new int[N + 1];
        WhereLISMin = new int[N + 1];
        Tracing = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());  // 수열 입력
            LISMin[i] = Integer.MAX_VALUE;  // LISMin 초기화
        }

        for (int i = 1; i <= N; i++) {
            // 현재 원소가 LIS의 마지막 원소보다 작은 경우
            if (A[i] < LISMin[maxLen]) {
                int tmpIdx = binarySearch(1, maxLen, A[i]);  // 적절한 위치 찾기

                if (tmpIdx == 0) {  // 가장 작은 원소인 경우
                    LISMin[1] = A[i];
                    WhereLISMin[1] = i;
                }
                else {  // 중간에 삽입되는 경우
                    LISMin[tmpIdx + 1] = A[i];
                    WhereLISMin[tmpIdx + 1] = i;
                }

                LIS[i] = tmpIdx + 1;  // 현재 원소의 LIS 길이 저장
                Tracing[i] = WhereLISMin[tmpIdx];  // 이전 원소 추적
            }
            // 현재 원소가 LIS의 마지막 원소보다 큰 경우
            else if (A[i] > LISMin[maxLen]) {
                maxIdx = i;  // 최대 길이 갱신
                Tracing[i] = WhereLISMin[maxLen];  // 이전 원소 추적
                maxLen++;  // 최대 길이 증가
                LISMin[maxLen] = A[i];  // 새로운 최대 길이의 최소값 갱신
                WhereLISMin[maxLen] = i;  // 위치 저장
                LIS[i] = maxLen;  // 현재 원소의 LIS 길이 저장
            }
        }

        // 결과 출력을 위한 스택 생성
        Stack<Integer> output = new Stack<>();
        for (int i = maxLen - 1; i >= 0; i--) {
            output.push(A[maxIdx]);  // LIS의 원소를 스택에 저장
            maxIdx = Tracing[maxIdx];  // 이전 원소로 이동
        }

        // 결과 문자열 생성
        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append('\n');  // LIS의 길이 출력

        // LIS의 원소들을 순서대로 출력
        for (int i = 0; i < maxLen; i++) sb.append(output.pop()).append(" ");
        System.out.println(sb.toString());
        br.close();
    }
}