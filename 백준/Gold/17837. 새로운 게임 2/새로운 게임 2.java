import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
	static int turn (int n)
	{
		if (n <= 2) return 3 - n; // 1 이면 2 // 2 이면 1을 반환
		else return 7 - n; // 3이면 4 // 4면 3을 반환
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 체스판 크기 4 ~ 12
        K = Integer.parseInt(st.nextToken()); // 말의 갯수 4 ~ 10
		// 입력값 받기
		int[][] grid = new int[N + 1][N + 1]; // 체스판 배열 => 색 표현 : 0 흰색 / 1 빨간색 / 2 파랑색
		Deque<Integer>[][] chessPiecesOnGrid = new Deque[N + 1][N + 1]; // 체스판에 올라가 있는 체스말들 리스트
		int[][] chessPiecesLocationAndDirection = new int[K + 1][2];
		for (int r = 1; r <= N; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				chessPiecesOnGrid[r][c] = new LinkedList<>();
			}
		}
//		System.out.println("\n[grid]");
//		for (int i = 0; i <= N; i++) System.out.println(Arrays.toString(grid[i]));
		for (int i = 1; i <= K; i++)
		{
			// 체스 말들 번호에 따라서 행과 열 그리고 방향값을 저장
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			chessPiecesLocationAndDirection[i][0] = r * 100 + c; // 위치
			chessPiecesLocationAndDirection[i][1] = dir; // 방향
			chessPiecesOnGrid[r][c].add(i);
		}
		int turn = 0;
		loop :
		while (turn < 1000)
		{
			turn++;
//			System.out.println("[turn = " + turn + "]");
			for (int i = 1; i <= K; i++) // i == 현재 체스 말 번호
			{
				int r = chessPiecesLocationAndDirection[i][0] / 100; // 현재 체스 말의 행
				int c = chessPiecesLocationAndDirection[i][0] % 100; // 현재 체스 말의 열
				int dir = chessPiecesLocationAndDirection[i][1]; // 현재 체스 말의 방향
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				// 범위 밖으로 벗어나거나 파란색인 경우 뒤로 돌기
				if (nr < 1 || nc < 1 || nr > N || nc > N || grid[nr][nc] == 2)
				{
					// 우선 위치는 그대로고 방향만 바꿔서 넣어줌!
					dir = turn(dir);
					chessPiecesLocationAndDirection[i][1] = dir;
					// 해당 방향으로 다시 검사
					nr = r + dr[dir];
					nc = c + dc[dir];
                    if (nr < 1 || nc < 1 || nr > N || nc > N || grid[nr][nc] == 2) continue; //  이번엔 그냥 아무것도 안하고 끝냄
                    else // 갈 수 있는 곳이면 그곳의 색깔부터 검사!
					{
						if (grid[nr][nc] == 0) // 하얀색 // 앞에서부터 빼서 쌓아주기
						{
							int size = chessPiecesOnGrid[r][c].size();
							while (chessPiecesOnGrid[r][c].peek() != i)
							{
								chessPiecesOnGrid[r][c].offer(chessPiecesOnGrid[r][c].poll());
								size--;
							}
							while (size > 0)
							{
								int cur = chessPiecesOnGrid[r][c].poll(); // 앞에서부터 빼기
								chessPiecesOnGrid[nr][nc].offer(cur);
								chessPiecesLocationAndDirection[cur][0] = nr * 100 + nc;
								// 여기서 방향전환은 하면 안됨!! 가지고 있던 방향을 바꾸는 건 파란색 칸이나 벽을 만났을 때 뿐!!
								if (chessPiecesOnGrid[nr][nc].size() >= 4) break loop;
								size--;
							}
						}
						else // 빨간색 // 뒤에서부터 빼서 쌓아주기
						{
							while (chessPiecesOnGrid[r][c].peekLast() != i)
							{
								int cur = chessPiecesOnGrid[r][c].pollLast(); // 뒤에서부터 빼기
								chessPiecesOnGrid[nr][nc].offer(cur);
								chessPiecesLocationAndDirection[cur][0] = nr * 100 + nc;
								// 여기서 방향전환은 하면 안됨!! 가지고 있던 방향을 바꾸는 건 파란색 칸이나 벽을 만났을 때 뿐!!
								if (chessPiecesOnGrid[nr][nc].size() >= 4) break loop;
							}
							// 마지막 해당 숫자도 옮겨주기
							int theNum = chessPiecesOnGrid[r][c].pollLast();
							chessPiecesOnGrid[nr][nc].offer(theNum);
							chessPiecesLocationAndDirection[theNum][0] = nr * 100 + nc;
							if (chessPiecesOnGrid[nr][nc].size() >= 4) break loop;
						}
                    }
                }
				else // 여기서부터 흰색 또는 빨간색 // 이동하고자 하는 칸을 먼저 검사
				{
					if (grid[nr][nc] == 0) // 하얀색 // 앞에서부터 빼서 쌓아주기
					{
						int size = chessPiecesOnGrid[r][c].size();
						while (chessPiecesOnGrid[r][c].peek() != i)
						{
							chessPiecesOnGrid[r][c].offer(chessPiecesOnGrid[r][c].poll());
							size--;
						}
						while (size > 0)
						{
							int cur = chessPiecesOnGrid[r][c].poll(); // 앞에서부터 빼기
							chessPiecesOnGrid[nr][nc].offer(cur);
							chessPiecesLocationAndDirection[cur][0] = nr * 100 + nc;
							// 여기서 방향전환은 하면 안됨!! 가지고 있던 방향을 바꾸는 건 파란색 칸이나 벽을 만났을 때 뿐!!
							if (chessPiecesOnGrid[nr][nc].size() >= 4) break loop;
							size--;
						}
					}
					else // 빨간색 // 뒤에서부터 빼서 쌓아주기
					{
						while (chessPiecesOnGrid[r][c].peekLast() != i)
						{
							int cur = chessPiecesOnGrid[r][c].pollLast(); // 뒤에서부터 빼기
//							System.out.println(cur);
							chessPiecesOnGrid[nr][nc].offer(cur);
							chessPiecesLocationAndDirection[cur][0] = nr * 100 + nc;
							// 여기서 방향전환은 하면 안됨!! 가지고 있던 방향을 바꾸는 건 파란색 칸이나 벽을 만났을 때 뿐!!
							if (chessPiecesOnGrid[nr][nc].size() >= 4) break loop;
						}
						// 마지막 해당 숫자도 옮겨주기
						int theNum = chessPiecesOnGrid[r][c].pollLast();
						chessPiecesOnGrid[nr][nc].offer(theNum);
						chessPiecesLocationAndDirection[theNum][0] = nr * 100 + nc;
						if (chessPiecesOnGrid[nr][nc].size() >= 4) break loop;
					}
				}
//				for (int j = 1; j <= K; j++) {
//					r = chessPiecesLocationAndDirection[j][0] / 100;
//					c = chessPiecesLocationAndDirection[j][0] % 100;
//					dir = chessPiecesLocationAndDirection[j][1];
//					System.out.println(j + "] r = " + r + " c = " + c + " dir = " + dir);
//				}
//				System.out.println("");
			}
		}
		if (turn == 1000) ans.append(-1);
		else ans.append(turn);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}