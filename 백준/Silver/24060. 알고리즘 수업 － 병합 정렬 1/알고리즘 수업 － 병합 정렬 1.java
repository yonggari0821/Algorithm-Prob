import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, K;
    static int saveNum = 0;
    static int saveOrder = 0;
    static void mergeSort(int[] nums, int l, int r)
    {
        if (l < r)
        {
            int mid = l + (r - l) / 2;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }
    }

    static void merge(int[] nums, int l, int mid, int r)
    {
        int[] tmpArr = new int[r-l+1];
        int idx = 0;
        int tl = l;
        int tr = mid + 1;
        while (tl <= mid && tr <= r)
        {
            if (nums[tl] > nums[tr]) tmpArr[idx++] = nums[tr++];
            else tmpArr[idx++] = nums[tl++];
        }
        if (tl > mid) // 오른쪽 배열의 값이 아직 남은 경우
        {
            while (tr <= r) tmpArr[idx++] = nums[tr++];
        }
        else // tr > r // 왼쪽 배열의 값이 아직 남은 경우
        {
            while (tl <= mid) tmpArr[idx++] = nums[tl++];
        }
        idx = 0;
        while (l <= r)
        {
            nums[l] = tmpArr[idx];
            saveOrder++;
            if (saveOrder == K) saveNum = tmpArr[idx];
            l++;
            idx++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(nums, 0, N-1);
//        System.out.println(Arrays.toString(nums));
        if (saveOrder < K) ans.append(-1);
        else ans.append(saveNum);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}