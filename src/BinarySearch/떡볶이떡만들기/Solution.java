package 떡볶이떡만들기;

import java.util.Scanner;

public class Solution {

    public static int binarySearch(int[] arr, int start, int end, int target)
    {
        while  (start <= end)
        {
            int mid = start + (end - start) / 2;

            int total = 0;
            for (int i = 0; i < arr.length; i++)
            {
                int a = arr[i] - mid;
                if (a < 0)
                    a = 0;
                total += a;
            }

            if (total==target) return mid;
            else if (total > target) start = mid + 1;
            else end = mid - 1;
        }
        return end; // target을 찾을 수 없으면 0 리턴
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] RC = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++)
        {
            RC[i] = sc.nextInt();
            if (max <= RC[i])
                max = RC[i];
        }

        System.out.println(binarySearch(RC, 1, max, M));
    }
}
