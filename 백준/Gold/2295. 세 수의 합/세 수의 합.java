import java.util.*;
import java.io.*;


/*
5
1
2
3
7
21
>> 21

6
1
2
3
4
11
20
>> 11

5
1
80
90
181
250
>> 250

 */
public class Main {

    static int binarySearch(double target, double[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target < nums[mid]) r = mid - 1;
            else if (target == nums[mid]) return mid;
            else l = mid + 1;
        }
        return -1;
    }

    static int binarySearchHB(double target, double[] nums) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target < nums[mid]) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    static int binarySearchLB(double target, double[] nums) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target <= nums[mid]) r = mid;
            else l = mid + 1;
        }
        return l;
    }


    // a + b + c = d
    // a + b = d - c
    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {nums[i] = Integer.parseInt(br.readLine());}
        Arrays.sort(nums);

        double[] sums = new double[N * N];
        double[] minus = new double[N * N];
        Map<Double, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sums[N * i + j] = (double)nums[i] + (double)nums[j];
                minus[N * i + j] = (double)nums[i] - (double)nums[j];
                if (minus[N * i + j] >= 2) {
                    map.put(minus[N * i + j], i);
                }
            }
        }
        Arrays.sort(sums);
        Arrays.sort(minus);
        int val;
        double ans = 0;
        for (int i = N * N - 1; i >= 0; i--) {
            val = binarySearch(minus[i], sums);
            if (val != -1) {
                ans = Math.max( ans, nums[ map.get( minus[i] ) ] );
            }
        }
        System.out.println((int)ans);
    }
}