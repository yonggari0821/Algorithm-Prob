package 특정수개수구하기;

import java.util.Scanner;

public class Solution {

    public static int ans = -1;

    public static int BS(int[] numArr, int start, int end, int target)
    {
        while (start <= end)
        {
            int mid = start + ((end - start) / 2);

            if (numArr[mid] == target)
            {
                ans = 1;
                for (int i = mid - 1; i >= 0; i--)
                {
                    if (numArr[i] == target)
                        ans++;
                    else break;
                }
                for (int i = mid + 1; i <= end; i++)
                {
                    if (numArr[i] == target)
                        ans++;
                    else break;
                }
                break;
            }

            else if (numArr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int x = sc.nextInt();

        int[] numArr = new int[N];

        for (int i = 0; i < N; i++)
        {
            numArr[i] = sc.nextInt();
        }

        System.out.println(BS(numArr,0,N-1, x));

    }
}
