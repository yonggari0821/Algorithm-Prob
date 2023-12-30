import java.io.*;
import java.util.*;

/*
[브루트포스 17142]_연구소 3_안상준
<바이러스>
1) 활성
상하좌우 인접한 모든 빈칸 & 비활성 바이러스칸으로 동시에 복제 - 1초
2) 비활성
맨 처음에는 전부 비활성

<연구소>
1) 빈칸 0
2) 벽 1
3) 바이러스 2

활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변함

놓을 수 있는 바이러스의 개수는 M개이고
비활성 바이러스가 있는 곳에 놓을 수 있다.

비활성 + 활성 + 벽으로만 맵이 이루어지도록 하는 최소 시간을 구해야 함
즉, 빈 칸이 없도록 해야 함!

만약 벽 때문에 아무리 바이러스를 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력

--------------------------------풀이--------------------------------
1. 활성 바이러스를 놓을 수 있는 위치 즉, 비활성 바이러스들의 초기 위치를 저장해두는 리스트를 만들어둔다.
2. 입력 값을 받으면서 빈 칸의 갯수 세기 + 비활성 바이러스들의 위치 리스트 채우기
3. 초기 비활성 바이러스들의 위치들의 조합을 구하기 => N과 M 방식
4. 얻어낸 조합으로 활성 바이러스 배치 후 해당 배치에서의 최대 시간과 전체 최소시간 비교해서 전체 최소시간 리뉴얼
(단, 이 때 해당 배치에서의 빈 칸 갯수를 또 세서 0이 아니면 무시)
 */

class Virus
{
	int r;
	int c;

	public Virus(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {

	static int N, M, minTime = Integer.MAX_VALUE;
	static int empty; // 빈 칸의 갯수
	static int[][] lab;
	static int[][] tmpLab;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Virus> initViruses = new ArrayList<>();

	static void renewalTmpLab()
	{
		for (int r = 0; r < lab.length; r++)
		{
			for (int c = 0; c < lab[0].length; c++) tmpLab[r][c] = lab[r][c];
		}
	}

	static void chooseInactiveVirus(int pickedNum, int pickBitNum, int last)
	{
		if (pickedNum == M)
		{
//			System.out.println("picked!" + Integer.toBinaryString(pickBitNum));
			putActiveVirus(pickBitNum);
			return;
		}
		for (int i = last + 1; i < initViruses.size(); i++)
		{
			chooseInactiveVirus(pickedNum + 1, pickBitNum | (1 << i), i);
		}
	}

	static void putActiveVirus(int whereToPut)
	{
		int maxTime = 0;
		int curEmpty = empty;
		renewalTmpLab();
		Queue<Virus> virusQueue = new LinkedList<>();
		for (int i = 0; i < initViruses.size(); i++)
		{
			if ((whereToPut & (1 << i)) != 0)
			{
				Virus tmp = initViruses.get(i);
				virusQueue.offer(new Virus(tmp.r, tmp.c));
				tmpLab[tmp.r][tmp.c] = 0;
			}
		}

		while (!virusQueue.isEmpty())
		{
			Virus curVirus = virusQueue.poll();
			int cr = curVirus.r;
			int cc = curVirus.c;
			maxTime = Math.max(maxTime, tmpLab[cr][cc]);
			if (curEmpty == 0) continue ;
			for (int i = 0; i < 4; i++)
			{
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || tmpLab[nr][nc] >= 0) continue;
				if (tmpLab[nr][nc] == -2) {
//					System.out.println("[ " + nr + ", " + nc + " ] && " + curEmpty);
					curEmpty--;
				}
				tmpLab[nr][nc] = tmpLab[cr][cc] + 1;
				virusQueue.offer(new Virus(nr, nc));
			}
		}
		if (curEmpty == 0) minTime = Math.min(minTime, maxTime);
//		System.out.println(minTime + " / " +Integer.toBinaryString(whereToPut));
	}



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		tmpLab = new int[N][N];
		for (int r = 0; r < N; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				// 빈 칸 => 나중에 BF 돌릴 때 어떤 값보다 더 작은 값이 되어야 함 => 초기값 -2로 세팅
				//		=> 나중에 BF 돌릴 때 마지막에 굳이 한 번 더 탐색하지 않도록 갯수를 세두면 좋음
				if (tmp == 0)
				{
					lab[r][c] = -2;
					empty++;
				}
				// 벽 => 나중에 BF 돌릴 때 어떤 시간이 지나도 더 큰 값이 되어야 함 => 초기값 Integer.MAX_VALUE로 세팅
				else if (tmp == 1)
				{
					lab[r][c] = Integer.MAX_VALUE;
				}
				// 초기 비활성 바이러스 => 나중에 BF 돌릴 때 0보다 더 작은 값이 되어야 함 => 초기가 -1으로 세팅
				// 				   => 나중에 BF 돌릴 때 활성 바이러스를 처음 놓는 기준 위치가 되어야 함 => 위치 리스트에 넣어두기
				else if (tmp == 2)
				{
					lab[r][c] = -1;
					initViruses.add(new Virus(r, c));
				}
			}
		}

		// 가능한 조합으로 N과 M 돌리기
		chooseInactiveVirus(0, 0, -1);

		if (minTime == Integer.MAX_VALUE) minTime = -1;
		ans.append(minTime);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}