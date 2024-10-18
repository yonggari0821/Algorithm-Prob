import java.io.*;
import java.util.*;

/*
백준 10816 숫자카드2


*/

public class Main {
    // 조건 만족하는 첫 원소 위치
    static int binarySearchLowerBound(int target) {
        int l = 0;
        int r = arr.length;
        int m;
        while (l < r)
        {
            m = (l + r) / 2;
            if (target <= arr[m]) r = m;
            else l = m + 1;
        }
        return l;
    }

    // 조건 만족하는 마지막 원소 위치
    static int binarySearchUpperBound(int target) {
        int l = 0;
        int r = arr.length;
        int m;
        while (l < r)
        {
            m = (l + r) / 2;
            if (target < arr[m]) r = m;
            else l = m + 1;
        }
        return l;
    }

    // upperbound에서 lowerbound를 빼주면 총 갯수!
    static int getCount(int target) {
        return binarySearchUpperBound(target) - binarySearchLowerBound(target);
    }

    static int[] arr;
    static int[] searchNums;
    static int N, M;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 상근이가 가지고 있는 숫자 카드 갯수
        arr = new int[N]; // 숫자 카드 배열 선언
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken()); // 숫자카드 배열 초기화
        Arrays.sort(arr); // binarySearch위해서 오름차순 정렬
        
        M = Integer.parseInt(br.readLine()); // 갯수 찾아야 하는 숫자 갯수
        searchNums = new int[N]; // 갯수 찾아야 하는 숫자 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) sb.append(getCount(Integer.parseInt(st.nextToken()))).append(" "); // binarySearch해서 나온 갯수 세서 붙여넣기
        System.out.println(sb.toString());
    }
}