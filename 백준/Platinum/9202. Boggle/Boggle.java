import java.io.*;
import java.util.*;

/*
백준 9202 Boggle
트라이를 활용한 문제

인접한 글자(가로, 세로, 대각선 1칸)를 이용해서 단어를 만들 수 있고,
사전에 등재되어 있는 단어들만 올바른 단어임

1~2글자 0점
3~4글자 1점
5글자 2점
6글자 3점
7글자 5점
8글자 11점

사전 목록과 게임보드가 주어지고
얻을 수 있는 최대 점수 / 가장 긴 단어 / 찾은 단어의 수를 구하기

 */

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    boolean isPicked;

    public TrieNode(boolean isEnd, boolean isPicked) {
        this.isEnd = isEnd;
        this.isPicked = isPicked;
    }
}

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringBuilder tmpString = new StringBuilder();
    static int w, b;
    // w: 1~300000 단어 사전에 들어있는 단어의 수 // 단어는 최대 8글자 // 알파벳 대문자로만 이루어져 있음
    // b: 1~30 Boogle 판의 갯수 // 4x4의 보글판

    static TrieNode root;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11}; // 단어 글수가 그대로 인덱스 // 점수가 값

    static char[][] BoggleBoard;
    static boolean[][] visited;

    // 얻을 수 있는 최대 점수 / 가장 긴 단어 / 찾은 단어의 수를 구하기
    static int maxScore;
    static String longestWords;
    static int foundWordsAmount;

    static String compareString(String s1, String s2)
    {
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) > s2.charAt(i)) return s2;
        }
        return s1;
    }

    static void clearIsPicked(TrieNode node) {
        node.isPicked = false;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) clearIsPicked(node.children[i]);
        }
    }

    static void search(int r, int c, TrieNode cur) {
        visited[r][c] = true;
        tmpString.append(BoggleBoard[r][c]);

        if (cur.isEnd && !cur.isPicked) {
            maxScore += score[tmpString.length()];
            if (tmpString.length() > longestWords.length()) {
                // tmpString이 더 긴 경우, 무조건 longestWords로 설정
                longestWords = tmpString.toString();
            } else if (tmpString.length() == longestWords.length()) {
                // 길이가 같은 경우, 사전순으로 비교하여 더 앞서는 단어를 설정
                if (tmpString.toString().compareTo(longestWords) < 0) {
                    longestWords = tmpString.toString();
                }
            }
//            System.out.println("longestWords = " + longestWords);
            foundWordsAmount++;
            cur.isPicked = true;
        }

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || visited[nr][nc]) continue;
            if (cur.children[BoggleBoard[nr][nc] - 'A'] != null) search(nr, nc, cur.children[BoggleBoard[nr][nc] - 'A']);
        }

        visited[r][c] = false;
        tmpString.deleteCharAt(tmpString.length() - 1);
    }


    public static void main(String[] args) throws IOException {

//        System.out.println('Z' - 'A' + 1); // 알파벳 대문자 갯수 총 26개
//        System.out.println((int)'A'); // 알파벳 A의 아스키코드 값 65
//        System.out.println((int)'Z'); // 알파벳 Z의 아스키코드 값 90

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());
        root = new TrieNode(false, false);

        // 사전은 공유
        for (int i = 0; i < w; i++) {
            String word = br.readLine();
            TrieNode cur = root;
            // 이미 있으면 건너뛰고 없으면 자식 추가 후 현재 노드 이동
            for (int idx = 0; idx < word.length(); idx++) {
                char c = word.charAt(idx);
                if (cur.children[c - 'A'] == null) cur.children[c - 'A'] = new TrieNode(false, false);
                cur = cur.children[c - 'A'];
            }
            cur.isEnd = true;
        }

        br.readLine();

        // 단어판은 b번 바뀜
        b = Integer.parseInt(br.readLine());
        for (int i = 0; i < b; i++) {
            maxScore = 0;
            longestWords = "";
            foundWordsAmount = 0;
            BoggleBoard = new char[4][4];
            visited = new boolean[4][4];
            for (int r = 0; r < 4; r++) {
                String line = br.readLine();
                for (int c = 0; c < 4; c++) {
                    BoggleBoard[r][c] = line.charAt(c);
                }
            }

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (root.children[BoggleBoard[r][c] - 'A'] == null) continue;
                    tmpString = new StringBuilder();
                    search(r, c, root.children[BoggleBoard[r][c] - 'A']);
                }
            }

            sb.append(maxScore + " ");
            if (longestWords != null && longestWords != "") sb.append(longestWords + " ");
            sb.append(foundWordsAmount).append("\n");
            clearIsPicked(root);
            if (i != b - 1) br.readLine();
        }
        System.out.print(sb.toString());
    }
}

/*
[1]
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

3
ACMA
APCA
TOGI
NEST

PCMM
RXAI
ORCN
GPCG

ICPC
GCPC
ICPC
GCPC

8 CONTEST 4
14 PROGRAMM 4
2 GCPC 2

[2]
1
ABA

1
ABCD
EFGH
IJKL
MNOP

0 0

[3]
3
CAT
DOG
MOUSE

2
CATT
DOGG
MOOO
USED

ZZZZ
ZZZZ
ZZZZ
ZZZZ

4 MOUSE 3
0 0

[4]
5
HELLO
WORLD
BOGGLE
HELLOHELLO
GAMES

1
HOGG
ELLB
LDOG
AMES

2 HELLO 1

[5]
4
TREE
TRIE
TIER
TIRE

1
TIRE
TREE
RIER
EIRE

4 TIER 4
 */


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//class TrieNode
//{
//    TrieNode[] children = new TrieNode[26];
//    boolean isEnd;
//    boolean isPicked;
//
//    boolean hasChild(char c) // 매개변수로 들어온 문자를 자식으로 가지고 있는 지를 반환
//    {
//        return (children[c - 'A'] != null);
//        // 가지고 있으면 true // 없으면 false
//    }
//
//    TrieNode getChild(char c) // 매개변수로 들어온 문자를 자식으로 가지고 있으면 해당 자식 반환, 없으면 null 반환
//    {
//        return children[c - 'A'];
//    }
//
//    void clearPicked() // 해당 노드와 그 모든 자식 노드들에 대해서 골랐던 단어 여부 초기화
//    {
//        this.isPicked = false;
//        for (int i = 0; i < 26; i++)
//        {
//            if (children[i] != null)
//                children[i].clearPicked();
//        }
//    }
//}
//
//public class Main {
//    static  int[] dr = {-1, -1, -1, 0 , 1, 1, 1, 0};
//    static  int[] dc = {-1, 0 , 1, 1, 1, 0, -1, -1};
//    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
//    static int W, B; // W : 사전 단어 갯수 (최대 30만) // B : 보글판 갯수 (최대 30개)
//    static char[][] map; // 보글판 배열 (하나 받을 때마다 값 바뀜)
//    static boolean[][] visited; // 보글판 배열의 글자 방문 여부 배열 (map 마다 새로 만들기)
//    static String ans; // 가장 긴 단어
//    static int sum; // 얻을 수 있는 최대 점수
//    static int cnt; // 찾은 단어의 갯수(중복은 제외!)
//    static StringBuilder sb = new StringBuilder();
//    static TrieNode root = new TrieNode(); // 루트 노드 => 빈 공간 이어야 함!
//    static void insert(String word)
//    {
//        TrieNode current = root;
//        for (int i = 0; i < word.length(); i++)
//        {
//            char cc = word.charAt(i);
//            if (!current.hasChild(cc)) current.children[cc - 'A'] = new TrieNode();
//            current = current.getChild(cc);
//        }
//        current.isEnd = true;
//    }
//    static void search(int r, int c, TrieNode node)
//    {
//        // DFS
//        // 1. 체크인
//        visited[r][c] = true;
//        sb.append(map[r][c]);
//        // 2. 목적지에 도달했는가?
//        if (node.isEnd == true && node.isPicked == false)
//        {
//            // 점수 계산
//            node.isPicked = true;
//            sum += score[sb.length()];
//            cnt++; // 단어 수
//            String foundAnswer = sb.toString();
//            if (compare(ans, foundAnswer) > 0) ans = foundAnswer;
//        }
//        // 3. 연결된 곳을 순회
//        for (int i = 0; i < 8; i++)
//        {
//            int nr = r + dr[i];
//            int nc = c + dc[i];
//            // 4. 가능한 가? // 맵을 벗어나지 않고, Trie에 단어가 있고, 방문한 적이 없는가?
//            if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue;
//            if (node.hasChild(map[nr][nc]) && !visited[nr][nc])
//                search(nr, nc, node.getChild(map[nr][nc])); // 5. 간다
//        }
//        // 6. 체크아웃
//        visited[r][c] = false;
//        sb.deleteCharAt(sb.length() - 1);
//    }
//
//    static int compare(String o1, String o2)
//    {
//        int result = Integer.compare(o2.length(), o1.length());
//        if (result == 0) return o1.compareTo(o2);
//        return result;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder output = new StringBuilder();
//        W = Integer.parseInt(br.readLine());
//        for (int w = 0; w < W; w++)
//            insert(br.readLine());
//        br.readLine();
//        B = Integer.parseInt(br.readLine());
//        for (int b = 0; b < B; b++)
//        {
//            sb = new StringBuilder();
//            sum = 0;
//            ans = new String();
//            cnt = 0;
//            map = new char[4][4];
//
//            for (int r = 0; r < 4; r++)
//            {
//                String tmp = br.readLine();
//                for (int c = 0; c < 4; c++)
//                    map[r][c] = tmp.charAt(c);
//            }
//
//            for (int r = 0; r < 4; r++)
//            {
//                for (int c = 0; c < 4; c++)
//                {
//                    visited = new boolean[4][4];
//                    if (root.hasChild(map[r][c]))
//                        search(r, c, root.getChild(map[r][c]));
//                }
//            }
//            output.append(sum + " " + ans + " " + cnt).append('\n');
//            root.clearPicked();
//            if (b == B - 1) break;
//            br.readLine();
//        }
//        System.out.println(output.toString());
//    }
//}