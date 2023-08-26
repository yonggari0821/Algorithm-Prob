import java.io.*;
public class Main {
    static int N, ans = 0;
    static long sum(int[] numbers, int left, int right)
    {
        long tmp = 0;
        for (int i = left; i <= right; i++)
            tmp += numbers[i];
        return tmp;
    }
    static void BS (int[] nums, int end, int target)
    {
        int left = 0;
        int right = end - 1;
        if (nums[left] == target || nums[right] == target) ans++;
        long tmp = sum(nums, left, right);
        while (left < right)
        {
            while (tmp > target)
            {
                tmp -= nums[right];
                right--;
            }
            if (tmp == target) ans++;
            tmp -= nums[left];
            left++;
            right++;
            tmp += nums[right];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        if (N < 2) ans = 0;
        else if (N < 4) ans = 1;
        else {
            // 값이 1이면 소수인 배열 만들기 + 소수 갯수 세기 by 에라토스테네스의 체
            boolean[] isPrime = new boolean[N + 1];
            int[] primeNums = new int[N / 2 + 1];
            isPrime[2] = true;
            int howManyPrime = 0;
            primeNums[howManyPrime++] = 2;
            for (int i = 3; i <= N; i += 2) {
                if (!isPrime[i])
                {
                    primeNums[howManyPrime++] = i;
                    for (int j = i; j <= N; j += (2 * i)) {
                        isPrime[j] = true;
                    }
                }
            }
            BS(primeNums, howManyPrime, N);
        }
        bw.write(String.valueOf(ans));
        br.close();
        bw.flush();
        bw.close();
    }
}