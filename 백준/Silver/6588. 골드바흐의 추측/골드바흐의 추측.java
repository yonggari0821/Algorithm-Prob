import java.io.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    static int[] nums = new int[100000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cnt = 0;
        int max = 4;
        while (true)
        {
            int even = Integer.parseInt(br.readLine());
            if (even < 6) break;
            max = max < even ? even : max;
            nums[cnt] = even;
            cnt++;
        }
        int[] isPrime = new int[max/2 + 1];
        // 소수 판별위한 표시
        for (int i = 3; i <= max ; i+=2)
        {
            for (int j = i; j <= max; j+=(2*i))
                isPrime[j/2]++;
        }
        // 골드바흐의 추측
        for (int i = 0; i < cnt; i++)
        {
            int tmpEven = nums[i];
            int left = 3;
            int right = tmpEven - left;
            boolean isGoldbachRight = false;
            for (int l = left; l <= right; l += 2)
            {
                if (isGoldbachRight == true) break;
                int r = tmpEven - l;
                if (isPrime[l/2] == 1 && isPrime[r/2] == 1)
                {
                    ans.append(tmpEven + " = " + l + " + " + r).append('\n');
                    isGoldbachRight = true;
                    break;
                }
            }
            if (!isGoldbachRight) ans.append("Goldbach's conjecture is wrong.").append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}