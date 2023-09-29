import java.io.*;
import java.util.*;

public class Main {
//	static String[] res = {"아웃", "안타", "2루타", "3루타", "홈런"};
	static int N, maxScore = 0;
	static int[][] playerPerformancesInEveryInning;
	static int[] decidedPlayerOrder = new int[10];
	static void decidePlayerOrder(int decidedPlayerNums, int picked)
	{
		if (decidedPlayerNums == 4) {
			decidePlayerOrder(decidedPlayerNums + 1, (picked | (1 << 1)));
			return ;
		}
		if (decidedPlayerNums == 10)
		{
//			System.out.println(Arrays.toString(decidedPlayerOrder));
			getScore();
			return ;
		}
		for (int i = 2; i <= 9; i++)
		{
			if ((picked & (1 << i)) != 0) continue;
			decidedPlayerOrder[decidedPlayerNums] = i;
			decidePlayerOrder(decidedPlayerNums + 1, (picked | 1 << i));
		}
	}

	static void getScore()
	{
		int score = 0;
		int inning = 1;
		int playerOrder = 1;
		while (inning <= N)
		{
			int out = 0;
			int[] base = new int[4]; // 이닝마다 진루 상황 초기화
			while (out < 3)
			{
				// 나가 있는 주자인 지 확인 => 나가있는 주자면 pass
				boolean flag = true;
				while (flag)
				{
					for (int i = 1; i < 4; i++)
					{
						if (base[i] == decidedPlayerOrder[playerOrder])
						{
//							System.out.print(playerOrder + " -> ");
							playerOrder = (playerOrder + 1) / 10 + ((playerOrder + 1) % 10);
//							System.out.println(playerOrder);
							break;
						}
						if (i == 3) flag = false;
					}
				}
				int playerNum = decidedPlayerOrder[playerOrder]; // 현재 타자 번호
				playerOrder = (playerOrder + 1) / 10 + ((playerOrder + 1) % 10); // 타자 번호 넘겨놓기
				int perform = playerPerformancesInEveryInning[inning][playerNum]; // 현재 이닝에 현재 타자가 수행하는 행위
//				System.out.println(perform);
				if (perform == 0) // out
				{
					out++;
				}
				else if (perform == 1)
				{
					base[0] = playerNum;
					for (int i = 3; i >= 0; i--) // 3루부터 확인해서 한 칸 씩 밀어주기!
					{
						if (base[i] != 0)
						{
							if (i == 3) score++;
							else base[i + 1] = base[i];
							base[i] = 0;
						}
					}
				}
				else if (perform == 2)
				{
					base[0] = playerNum;
					for (int i = 3; i >= 0; i--)
					{
						if (base[i] != 0)
						{
							if (i >= 2) score++;
							else base[i + 2] = base[i];
							base[i] = 0;
						}
					}
				}
				else if (perform == 3)
				{
					base[0] = playerNum;
					for (int i = 3; i >= 0; i--)
					{
						if (base[i] != 0)
						{
							if (i >= 1) score++;
							else base[i + 3] = base[i];
							base[i] = 0;
						}
					}
				}
				else if (perform == 4)
				{
					base[0] = playerNum;
					for (int i = 3; i >= 0; i--)
					{
						if (base[i] != 0)
						{
							score++;
							base[i] = 0;
						}
					}
				}
			}
			inning++;
		}
		maxScore = maxScore > score ? maxScore : score;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		playerPerformancesInEveryInning = new int[N + 1][10]; // 각 이닝 및 타자의 번호가 인덱스가 될 수 있도록 N + 1칸 및 10칸으로 설정!
		for (int inning = 1; inning <= N; inning++) {
			String str = br.readLine();
			for (int performance = 0; performance <= 16; performance += 2) playerPerformancesInEveryInning[inning][performance / 2 + 1] = str.charAt(performance) - '0';
		}
		// 선수 조합 // 1번 타자를 4번째로 하는 것은 고정
		// 1 2 3 5 6 7 8 9 번째에 2 3 4 5 6 7 8 9번 타자를 배치해야 함!
//		for (int i = 1; i <= N; i++) System.out.println(Arrays.toString(playerPerformancesInEveryInning[i]));
		decidedPlayerOrder[4] = 1;
		decidePlayerOrder(1, 1);
		ans.append(maxScore);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}