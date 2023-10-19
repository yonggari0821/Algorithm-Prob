import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int searchCnt(int idx, int[] arr) 
	{
		int left = 0; 
		int right = arr.length-1;
		int target = arr[idx];
		while (true) {
			if (left ==idx) left++;
			if (right ==idx) right--;
			if (left >= right) break;
			int sum = arr[left]+arr[right];
			if (sum == target) {
				return 1;
			}

			if (sum > target) right--;
			else left++;
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if (n<=2) {
			System.out.println(0);
			return;
		}
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);

		int base = -1000000001;
		int bf = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
            /*
			if (base == arr[i]) {
				cnt += bf;
				continue;
			}
            */
			cnt += bf = searchCnt(i, arr);
			base = arr[i];
		}
		System.out.println(cnt);
	}
}