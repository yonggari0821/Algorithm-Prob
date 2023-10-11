import java.io.*;
import java.util.*;

/*
풀이
배열 A 시작은 3 x 3
배열의 인덱스는 1부터 시작
1초마다 R 또는 C 연산
R 연산 (R >= C) : 모든 행에 대해 정렬(가로로 정렬)
C 연산 (R < C) : 모든 열에 대해 정렬(세로로 정렬)
각 행/열 별로 나온 숫자의 등장 횟수 오름차순 / 숫자 크기 오름차순
숫자별로 등장횟수 기록을 뭘로 하지?
다음 배열 크기를 정하기 위해서는 등장하는 숫자들의 갯수를 또 세놔야 함
=> hashMap으로해서 key 값에 숫자를, value 값에 등장 횟수를 저장하면 Map의 크기로 자동으로 등장하는 숫자들의 갯수는 세지겠네?
=> 구현해보니 넣고 사이즈 구하는 것까지는 쉬운데 꺼낼 때 복잡해짐
=> 배열로 해보자 대신 배열에 넣기 전에 해당 배열에 이미 값이 들어있는 지 확인해서 등장하는 숫자들의 갯수를 따로 세주고,
   등장횟수의 max 값도 구해놓자!

 */


public class Main {
	static final int timeLimit = 100;
	static int R, C, K, row, col;
	static boolean flag;
	static int[][] A;
	static int[] numCnt = new int[101];

	static void calR()
	{
		int[][] numCnt = new int[row + 1][101];
		int totalNumbers = 0;
		for (int r = 1; r <= row; r++)
		{
			int maxCnt = 0; // 숫자들의 등장 횟수 중 최고값 => 다음 배정 값을 위해 쓰임
			int numbers = 0; // 등장하는 숫자들의 갯수 => 다음 col 값 정할 때 쓰임
			for (int c = 1; c <=  col; c++)
			{
				int num = A[r][c];
				if (num == 0) continue;
				if (numCnt[r][num] == 0) numbers++;
				numCnt[r][num]++;
				maxCnt = Math.max(maxCnt, numCnt[r][num]);
			}
			totalNumbers = Math.max(totalNumbers, numbers); // 모든 행 중 가장 큰 등장하는 숫자들의 갯 * 2 로 다음 col 값을 정할 것!
			numCnt[r][0] = maxCnt; // 각 행의 최대 등장값을 0자리에 넣어두기!
		}
		int[][] nextA = new int[row + 1][Math.min(totalNumbers * 2, 100) + 1]; // 얘는 2배 + 1
		for (int r = 1; r <= row; r++)
		{
			int idx = 1; // 배치용 index
			for (int cnt = 1; cnt <= numCnt[r][0]; cnt++) // 저장해뒀던 최대 등장값까지 1부터 오름차순
			{
				for (int i = 1; i <= 100; i++) {
					if (idx < 100 && numCnt[r][i] == cnt)
					{
						nextA[r][idx++] = i; // 해당 수
						nextA[r][idx++] = cnt; // 해당 수의 등장 수
					}
				}
			}
		}
		col = Math.min(totalNumbers * 2, 100); // 얘는 2배만
		A = nextA; // 주소값 변경
	}

	static void calC()
	{
		int[][] numCnt = new int[101][col + 1];
		int totalNumbers = 0;
		for (int c = 1; c <= col; c++)
		{
			int maxCnt = 0; // 숫자들의 등장 횟수 중 최고값 => 다음 배정 값을 위해 쓰임
			int numbers = 0; // 등장하는 숫자들의 갯수 => 다음 row 값 정할 때 쓰임
			for (int r = 1; r <= row; r++)
			{
				int num = A[r][c];
				if (num == 0) continue;
				if (numCnt[num][c] == 0) numbers++;
				numCnt[num][c]++;
				maxCnt = Math.max(maxCnt, numCnt[num][c]);
			}
			totalNumbers = Math.max(totalNumbers, numbers); // 모든 열 중 가장 큰 등장하는 숫자들의 갯 * 2 로 다음 row 값을 정할 것!
			numCnt[0][c] = maxCnt; // 각 열의 최대 등장값을 0자리에 넣어두기!
		}
		int[][] nextAA = new int[Math.min(totalNumbers * 2, 100) + 1][col + 1]; // 얘는 2배 + 1
		for (int c = 1; c <= col; c++)
		{
			int idx = 1; // 배치용 index
			for (int cnt = 1; cnt <= numCnt[0][c]; cnt++) // 저장해뒀던 최대 등장값까지 1부터 오름차순
			{
				for (int i = 1; i <= 100; i++) // 숫자 값도 오름차순
				{
					if (idx < 100 && numCnt[i][c] == cnt)
					{
						nextAA[idx++][c] = i; // 해당 수
						nextAA[idx++][c] = cnt; // 해당 수의 등장 수
					}
				}
			}
		}
		row = Math.min(totalNumbers * 2, 100); // 얘는 2배만
		A = nextAA; // 주소값 변경
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
		flag = false;
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[4][4];
		for (int r = 1; r <= 3; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= 3; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		if (R < A.length && C < A[0].length && A[R][C] == K) flag = true;
		int time = 0;
		row = 3;
		col = 3;
		while (!flag && time < timeLimit)
		{
			time++;
//			for (int r = 0; r <= row; r++) System.out.println(Arrays.toString(A[r]));
//			System.out.println("---------");
			if (row >= col) calR();
			else calC();
			if (R < A.length && C < A[0].length && A[R][C] == K) flag = true;
//			for (int r = 0; r <= row; r++) System.out.println(Arrays.toString(A[r]));
//			System.out.println("row = " + row + " / col = " + col);
		}
		if (time == timeLimit && !flag) time = -1;
		ans.append(time);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}