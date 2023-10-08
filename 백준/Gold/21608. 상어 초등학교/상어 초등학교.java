import java.io.*;
import java.util.*;

/*
[풀이]
(1, 1) ~ (N, N) N 은 3 이상 20 이하
학생 수는 N 제곱
인접하다 == 행 차 절댓값 + 열 차 절댓값 <= 1

-------------------<학생 배치 우선순위>-------------------
1) 비어있는 칸 중 좋아하는 학생이 가장 많은 칸
2) 1번이 여러 개면 그 중 가장 비어있는 칸이 많은 칸
3) 2번이 여러 개면 그 중 가장 높은 행 / 그 다음으론 가장 왼쪽 열 칸
------------------------------------------------------

학생 만족도는 인접한 칸 중 자기가 좋아하는 학생의 수에 따라
0명 0 / 1명 1 / 2명 10 / 3명 100 / 4명 1000

학생 만족도 총 합 구하기
 */


public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] satisfaction = {0, 1, 10, 100, 1000}; // 만족도 점수 배열 ( idx == 인접 & 좋아하는 학생 수 )

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 학급의 한 변의 길이 => 제곱하면 학생 수
        int[][] favorite = new int[N * N + 1][7]; // 마지막 자리에는 만족도를 저장
        int[][] classroom = new int[N + 1][N + 1];
        for (int idx = 1; idx <= N * N; idx++) {
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            favorite[idx][0] = studentNum;
            favorite[idx][1] = Integer.parseInt(st.nextToken());
            favorite[idx][2] = Integer.parseInt(st.nextToken());
            favorite[idx][3] = Integer.parseInt(st.nextToken());
            favorite[idx][4] = Integer.parseInt(st.nextToken());
            favorite[studentNum][5] = idx;
        }
        for (int idx = 1; idx <= N * N; idx++)
        {
            int studentNum = favorite[idx][0]; // 현재 배치할 학생 번호
            // 맨 처음엔 아무것도 배치 안되어있으므로 1) 2) 조건 둘 다 여러개 따라서 (2, 2)에 배치! (1, 1)의 경우 주변에 빈 칸이 적어서 제외!
            if (idx == 1) classroom[2][2] = studentNum;
            else // 나머지 경우 우선순위를 고려해서 놓을 자리 찾기
            {
                int pr = 0;
                int pc = 0;
                int maxAdjCnt = -1;
                int maxEmptyCnt = -1;
                for (int r = 1; r <= N; r++) {
                    for (int c = 1; c <= N; c++) {
                        // 이미 놓아진 자리는 pass
                        if (classroom[r][c] != 0) continue;
                        // 아니면 우선순위 판단 // 1) 인접 좋아 / 2) 인접 비어 / 3) 더 위 더 왼쪽
                        int tmpAdjCnt = 0;
                        int tmpEmptyCnt = 0;
                        for (int i = 0; i < 4; i++) {
                            int nr = r + dr[i];
                            int nc = c + dc[i];
                            // 범위 벗어나는 경우 제외
                            if (nr <= 0 || nc <= 0 || nr > N || nc > N) continue;
                            // 빈 공간은 빈 공간 갯수로 세기
                            if (classroom[nr][nc] == 0) tmpEmptyCnt++;
                            else // 나머지는 좋아하는 목록에 있는 지 체크해서 세주기
                            {
                                for (int j = 1; j <= 4; j++)
                                    if (classroom[nr][nc] == favorite[idx][j]) tmpAdjCnt++;
                            }
                        }
                        // 우선 순위 1 부터 체크 // 확실하게 크면 바로 change
                        if (tmpAdjCnt > maxAdjCnt) {
                            pr = r;
                            pc = c;
                            maxAdjCnt = tmpAdjCnt;
                            maxEmptyCnt = tmpEmptyCnt; // 얘까지 바꿔줘야 함!
                        } else if (tmpAdjCnt == maxAdjCnt) // 우선 순위 1이 같으면 우선 순위 2 체크
                        {
                            if (tmpEmptyCnt > maxEmptyCnt) // 우선 순위 2는 클 때만 고려하면 됨! 탐색 순서에 따라 자동으로 더 위 / 더 왼쪽 것이 남음!
                            {
                                pr = r;
                                pc = c;
                                maxAdjCnt = tmpAdjCnt;
                                maxEmptyCnt = tmpEmptyCnt; // 얘까지 바꿔줘야 함!
                            }
                        }
                    }
                }
                // 모든 가능한 경우 중 가장 우선순위가 높은 곳에 배정
                classroom[pr][pc] = studentNum;
            }
        }
        // 만족도 계산
        for (int r = 1; r <= N; r++)
        {
        	for (int c = 1; c <= N; c++)
        	{
                int cnt = 0;
                int studentIdx = favorite[classroom[r][c]][5];
                for (int i = 0; i < 4; i++)
                {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr <= 0 || nc <= 0 || nr > N || nc > N) continue;
                    for (int j = 1; j <= 4; j++)
                    {
                        if (classroom[nr][nc] == favorite[studentIdx][j]) cnt++;
                    }
                }
                favorite[classroom[r][c]][6] = satisfaction[cnt];
        	}
        }

        // 확인용
//        for (int r = 1; r <= N; r++)
//        {
//            System.out.println(Arrays.toString(classroom[r]));
//        }
        int satisfactionSum = 0;
        for (int i = 1; i <= N * N; i++) satisfactionSum += favorite[i][6];
        ans.append(satisfactionSum);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}

/*
3
4 2 5 1 7
3 1 9 4 5
9 8 1 2 3
8 1 9 3 4
7 2 3 4 8
1 9 2 5 7
6 5 2 3 4
5 1 9 2 8
2 9 3 1 4
[54]

3
4 2 5 1 7
2 1 9 4 5
5 8 1 4 3
1 2 9 3 4
7 2 3 4 8
9 8 4 5 7
6 5 2 3 4
8 4 9 2 1
3 9 2 1 4
[1053]
 */