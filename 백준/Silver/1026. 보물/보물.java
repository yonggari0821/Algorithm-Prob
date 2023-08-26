import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int nums1[] = new int[N];
        int nums2[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums1[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums2[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums1);
        Integer[] tNums2 = Arrays.stream(nums2).boxed().toArray(Integer[]::new);
        Arrays.sort(tNums2, Comparator.reverseOrder() );
        nums2 = Arrays.stream(tNums2).mapToInt(Integer::intValue).toArray();
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) sum += (nums1[i] * nums2[i]);
        ans.append(sum);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}