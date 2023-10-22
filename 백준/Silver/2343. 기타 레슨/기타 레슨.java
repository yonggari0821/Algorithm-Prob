import java.io.*;
import java.util.*;

/*
[풀이]
강의 순서 변경 불가능 => 정렬 불가능
우리가 찾아야 되는 값은 모든 강의를 블루레이에 담으려고 할 때 가능한 최소한의 블루레이 크기이고,
블루레이의 갯수가 주어지므로
블루레이의 크기를 이분탐색으로 살펴보면 됨!

[이분탐색 방법]
1 ~ 10억(최대 10000길이 x 최대 10만 강의)
기본 블루레이 갯수 1개로 두고 시작해서
현재 시도해볼 길이 - 모든 동영상의 길이를 하나씩 빼가면서
0이나 음수가 되면 + 1해가고
만약에 현재 살피는 길이보다 아예 큰 동영상의 길이가 나오면 아예 탐색을 중단함
 */

public class Main {

	static int bs (int[] nums, int blueRayCnt)
	{
		int min = 1;
		int max = 1000000000;
		while (min <= max)
		{
			int mid = ( min + max ) / 2;
			int curVal = mid;
			int cnt = 1;
			for (int i = 0; i < nums.length; i++)
			{
				if (nums[i] > curVal)
				{
					if (nums[i] > mid) {
						cnt = nums.length + 1;
						break;
					}
					else
					{
						cnt++;
						curVal = mid - nums[i];
					}
				}
				else if (nums[i] == curVal)
				{
					if (i != nums.length - 1) cnt++;
					curVal = mid;
				}
				else curVal -= nums[i];
			}
			if (cnt <= blueRayCnt) // 블루레이 크기 더 줄여봐도 됨
			{
				max = mid - 1;
			}
			else // 블루레이 크기 늘려야 함
			{
				min = mid + 1;
			}
		}
		return min;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 강의의 수
		int M = Integer.parseInt(st.nextToken()); // 블루레이의 수
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		ans.append(bs(nums, M));
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}