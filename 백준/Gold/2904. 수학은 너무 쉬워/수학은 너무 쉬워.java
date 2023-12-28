import java.io.*;
import java.util.*;

/*
[에라토스테네스의 체 2904]_수학은 너무 쉬워_안상준

주어지는 모든 수들을 1000000만 이하의 소수들로 나눠 그 갯수를 표시해뒀다가
N개 이상 생기는 배열들에서 값을 가져와 최대 공약수를 만들것!




 */


public class Main {
	static int N;
	static int[] nums;

	static int[] valueArr = new int[1000001];
	static ArrayList<Integer> primeNum = new ArrayList<>();
	static boolean[] isUsed = new boolean[1000001];

	static {
		for (int i = 2; i <= 1000000 ; i++) {
			if (!isUsed[i]) {
				primeNum.add(i);
				int tmp = i;
				while (tmp <= 1000000) {
					isUsed[tmp] = true;
					tmp += i;
				}
			}
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 숫자들의 갯수 => 최대 100
		if (N == 1) // 예외 처리
		{
			System.out.println(Integer.parseInt(br.readLine()) + " " + 0);
			return ;
		}
		nums = new int[N]; // 주어지는 숫자들 최대 100만
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
		{
			nums[i] = Integer.parseInt(st.nextToken());
			int tNum = nums[i];
//			System.out.print(nums[i] + " = ");
			// 소수들로 나누면서 그 몫을 해당 소수의 자리에 기록
			for (int j = 0; j < primeNum.size(); j++)
			{
				if (tNum < primeNum.get(j)) break;
				// 이렇게 쓰연 안됨!! 예를 들면 6인 경우에 2가 1개만 들어가야하는데 로그로 하면 로그 2의 6은 2.XXX이라서 2개가 들어가버림!
				// if (tNum % primeNum.get(j) == 0) valueArr[primeNum.get(j)] += (int) ( Math.log(tNum) / Math.log(primeNum.get(j) ) );
				while ((tNum >= primeNum.get(j)) && (tNum % primeNum.get(j) == 0))
				{
					tNum /= primeNum.get(j);
					valueArr[primeNum.get(j)]++;
//					System.out.print(primeNum.get(j) + " x ");
				}
			}
//			System.out.println("");
		}

		int res = 1; // 최대공약수
		int minMove = 0; // 최소 움직임

		List<Integer> gcdAtomList = new ArrayList<>();
		for (int i = 2; i <= 1000000 ; i++)
		{
			// 최대공약수에 반영될 수 있는 기준 // 기록해놓은 값이 N개 이상이어야 함!
			if (valueArr[i] >= N)
			{
				for (int j = 0; j < valueArr[i] / N; j++) {
					res *= i; // 해당 수를 반영 가능한 갯수 만큼 곱하기 // 여기서 잘못됨! i를 갯수만큼 곱하는 게 아니라 갯수 승 만큼 곱해줘야 함!
					gcdAtomList.add(i); // 그리고 반영한 갯수 만큼 해당 수를 최대 공약수의 원자 리스트에 넣어주기
				}
//				System.out.println("i = " + i + " // res = "+ res);
			}
		}

		for (int i = 0; i < N; i++)
		{
			// 주어지는 수 별로 최대 공약수의 원소들로 나눠가면서
			for (int j = 0; j < gcdAtomList.size(); j++)
			{
				// 나누어 떨어지면 나눠주고
				if (nums[i] % gcdAtomList.get(j) == 0) nums[i] /= gcdAtomList.get(j);
				// 아니면 옮긴 거니깐 이동횟수를 올려줌
				else minMove++;
			}
		}
		ans.append(res + " " + minMove);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}

/*
반례
1
1000000

정답 : 1000000 0

2
1000000 999888

정답 : 4000 4
 */