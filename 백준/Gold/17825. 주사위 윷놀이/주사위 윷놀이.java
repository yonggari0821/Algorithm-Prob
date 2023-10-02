import java.io.*;
import java.util.*;

public class Main {
    static int[][] original = {
            {0, 2, 4, 6, 8}, // 0 ~ 4
            {10, 12, 14, 16, 18}, // 5 ~ 9
            {20, 22, 24, 26, 28}, // 10 ~ 14
            {30, 32, 34, 36, 38}, // 15 ~ 19
            {40, 987654321, 987654321, 987654321, 987654321}, // 20 ~
    };

    static int[][] shortcut = {
            {},
            {10, 13, 16, 19},
            {20, 22, 24},
            {30, 28, 27, 26},
            {25, 30, 35, 40, 987654321, 987654321, 987654321, 987654321, 987654321, 987654321}
    };

    static int max = 0;
    static int[] dices = new int[10];
    static int[] piecesChoosedDice = new int[10];
    static void chooseDice(int choosed)
    {
        if (choosed == 10)
        {
            // 게임 해보는 함수
            diceYutnori();
            return ;
        }
        for (int piece = 0; piece < 4; piece++) // 0 0 0 0 0 0 0 0 0 0 ~ 3 3 3 3 3 3 3 3 3 3 까지 모두 확인!
        {
            piecesChoosedDice[choosed] = piece;
            chooseDice(choosed + 1);
        }
    }

    // 주사위 윷놀이 진행 // 진행 불가능한 경우가 한 번이라도 나오면 값 구하지 않고 함수 종료 // 모든 경우가 가능한 경우에만 최대값 리뉴얼
    static void diceYutnori()
    {
        int point = 0;
        boolean[][] existOriginal = new boolean[original.length][original[0].length];
        boolean[][] existShortcut = new boolean[shortcut.length][shortcut[4].length];
        int[][] whichMapAndWhere = new int[4][2]; // [말 번호][0] : 라인 종류 0 - original 1 - shortcut 2 - 도착점 // [말 번호][1] : 위치
        // 배정한 주사위 순서에 따라 말들을 이동시킬 것!
        for (int dice = 0; dice < 10; dice++)
        {
            int pieceNum = piecesChoosedDice[dice]; // 이동시킬 말 번호
            int stepsToMove = dices[dice]; // 이동시킬 거리
            // 이동시키기 전에 현재 어떤 위치에 있는 지 확인해야함!
            // 1) 현재 도착점에 있는 경우 => 이동이 불가능한데 골라진 경우 => 즉시 return!!
            if (whichMapAndWhere[pieceNum][0] == 2) return ;
            else // 그 외
            {
                int wherePieceWas = whichMapAndWhere[pieceNum][1]; // 원래 자리
                int wherePieceWill = whichMapAndWhere[pieceNum][1] + stepsToMove; // 이동 할 자리


                // 2) 현재 original 라인에 있는 경우 => 도착할 가능성 체크 && 지름길로 이동할 가능성 체크
                if (whichMapAndWhere[pieceNum][0] == 0)
                {
                    existOriginal[wherePieceWas / 5][wherePieceWas % 5] = false; // 원래 자리 비우기
                    // original 라인이므로 기준은 '5'
                    int line = wherePieceWill / 5;
                    int col = wherePieceWill % 5;
                    // 도착할 가능성 체크
                    if (wherePieceWill > 20)
                    {
                        whichMapAndWhere[pieceNum][0] = 2; // 현재 말 위치 도착점으로 표시
                        whichMapAndWhere[pieceNum][1] = wherePieceWill; // 현재 말 위치 옮겨놓기
                        continue;
                    }
                    // shortcut 라인으로 이동하는 경우
                    if (col == 0 && line != 4) {
                        // 지름길로 이동시켜서 해당 자리 확인
                        whichMapAndWhere[pieceNum][0] = 1; // shortcut 라인으로 옮겨주기
                        if (existShortcut[line][col]) return; // 해당 자리에 이미 다른 말이 있는 경우 => 즉시 return!!
                        // 아니면 현재 자리 비우고 이동할 자리 차지해주고 point += 이동할 자리
                        existShortcut[line][col] = true; // 이동할 자리 차지 // 이동할 자리는 shortcut 라인에 있으므로 shortcut 라인을 채워야 함
                        point += shortcut[line][col]; // 이동할 자리의 값을 더해 줌
                        whichMapAndWhere[pieceNum][1] = line * 10; // 현재 말 위치 옮겨놓기
                    }
                    else if (col == 0 && line == 4)
                    {
                        whichMapAndWhere[pieceNum][0] = 1; // shortcut 라인으로 옮겨주기
                        col = 3;
                        if (existShortcut[line][col]) return; // 해당 자리에 이미 다른 말이 있는 경우 => 즉시 return!!
                        // 아니면 현재 자리 비우고 이동할 자리 차지해주고 point += 이동할 자리
                        existShortcut[line][col] = true; // 이동할 자리 차지 // 이동할 자리는 shortcut 라인에 있으므로 shortcut 라인을 채워야 함
                        point += 40; // 이동할 자리의 값을 더해 줌
                        whichMapAndWhere[pieceNum][1] = line * 10 + 3; // 현재 말 위치 옮겨놓기
                    }
                    else // shortcut 라인으로 이동하지 않는 경우
                    {
                        if (existOriginal[line][col]) return; // 해당 자리에 이미 다른 말이 있는 경우 => 즉시 return!!
                        existOriginal[line][col] = true; // 이동할 자리 채우고
                        point += original[line][col]; // 이동할 자리의 값을 더해 줌
                        whichMapAndWhere[pieceNum][1] = wherePieceWill; // 현재 말 위치 옮겨놓기
                    }
                }


                else // 3) 현재 shortcut 라인에 있는 경우 // 다시 original 라인으로 갈 가능성 X => 도착하는 지만 체크해주면 됨!
                {
                    // 현재 라인을 확인해서 라인 별로 다른 기준으로 판단해야 함!
                    int sLine = wherePieceWas / 10;
                    int sCol = wherePieceWas % 10;
                    // 일단 현재 자리 비우기
                    existShortcut[sLine][sCol] = false;
                    if (sLine == 1 || sLine == 3)
                    {
                        if (sCol + stepsToMove > 3)
                        {
                            sLine = 4;
                            sCol = (sCol + stepsToMove - 4);
                        }
                        else sCol += stepsToMove;
                    }
                    else if (sLine == 2)
                    {
                        if (sCol + stepsToMove > 2)
                        {
                            sLine = 4;
                            sCol = (sCol + stepsToMove - 3);
                        }
                        else sCol += stepsToMove;
                    }
                    else if (sLine == 4) sCol += stepsToMove;

                    // 해당 자리에 이미 다른 말이 있다면 즉시 return;
                    if (existShortcut[sLine][sCol]) return;
                    // 도착한 경우
                    if (shortcut[sLine][sCol] == 987654321)
                    {
                        whichMapAndWhere[pieceNum][0] = 2; // 도착점으로 표시
                        whichMapAndWhere[pieceNum][1] = sLine * 10 + sCol;
                    }
                    else // 아직 도착하지 않은 경우
                    {
                        existShortcut[sLine][sCol] = true; // 이동할 자리 차지
                        point += shortcut[sLine][sCol]; // 이동할 자리 값 더해주고
                        whichMapAndWhere[pieceNum][1] = sLine * 10 + sCol; // 이동시켜 놔주기
                    }
                }
            }
        }
//        if (point == ) System.out.println(Arrays.toString(piecesChoosedDice));
        max = max > point ? max : point;
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int dice = 0; dice < 10; dice++) dices[dice] = Integer.parseInt(st.nextToken());
        chooseDice(0);
        ans.append(max);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}

/*
4개의 말에 10개의 주사위 결과를 배정 => 주사위 결과는 이미 순서가 고정 => 해당 순서에 4개의 말이 들어간다고 가정

1번 주사위 결과 배정 가능한 말 => 1, 2, 3, 4
2번 주사위 결과 배정 가능한 말 => 1, 2, 3, 4
3번 주사위 결과 배정 가능한 말 => 1, 2, 3, 4
.
.
.
n번 주사위 결과 배정 가능한 말 => 1, 2, 3, 4
따라서 총 4의 10승 => 2의 20승의 가짓수를 가짐! => int형 범위 내!

주사위 각각의 순서에 말 배정하는 배열 및 재귀 함수 1개 필요 => chooseDice

주사위 순서에 따라 말이 배정되고 나면, 주사위 순서 및 결과대로 해당 말들을 이동시켜야 함
이 때, 불가능한 경우가 1번이라도 나오면 해당 말 배정은 불가능한 경우이므로 바로 쳐냄
모두 충족되면 최대값 리뉴얼 해줘야함
=> 이 과정 담당하는 함수 1개 필요 => diceYutnori

diceYutnori 함수의 경우 chooseDice 중간에 탈출조건 만족 시 탈출 전에 수행해야 하는 함수이므로
해당 두 함수 모두에 공통되는 배열이 static 하게 필요함 => 주사위 순서에 맞게 배정된 말들 배열 "piecesChoosedDice"

 */