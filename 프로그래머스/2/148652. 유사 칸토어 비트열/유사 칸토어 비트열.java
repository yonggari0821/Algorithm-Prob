import java.util.*;

/*
    원래 칸토어 비트열
    0/1 사이의 실수로 이루어진 집합
    각 구간을 3등분하여 가운데 구간을 반복적으로 제외하는 방식으로 만들어짐
    
    유사 칸토어 비트열
    0 -> 00000
    1 -> 11011
    
    0) 1 [1 - 1 - 0]
    1) 11011 [5 - 3 - 1]
    2) 1101111011000001101111011 [25 - 13 - (4 + 5)]
    3) 11011110110000011011110111101111011000001101111011000000000000000000000000011011110110000011011110111101111011000001101111011 [125 - 63 - (36 + 25)]
    .
    .
    .
    n) n-1이 2번 나오고 0이 반복 n-1 길이만큼 반복된 후 다시 n-1이 2번 나옴
    l과 r이 주어졌을 때 구간 내 1의 갯수
    
    규칙성은 찾았는데
    n이 최대 20이므로
    유사 칸토어 비트열의 20번째의 길이는 95,367,431,640,625(약 95조)가 됨
    다만 r이 최대 l보다 천만까지만 클 수 있으므로 답은 int로 구해도 되지만
    답을 구할 어떤 규칙성을 찾아야 함
    
    전체 구간은 총 5개로 나누어 질 수 있음
    n번째 유사 칸토어 비트열의 인덱스를 n-1번째 유사 칸토어비트열의 길이로 나눈 몫에 따라서
    [0 / 1 / 2 / 3 / 4]
    이 중 2를 제외하고는 전부 n-1번째 유사 칸토어 비트열 중 인덱스를 n-1번째 유사 칸토어 비트열 길이로 나눈 나머지가 같은 위치의 값과 동일
    2 구간은 무조건 0
    
    => l
*/

// class Solution {
//     private static int[] base = {1, 1, 0, 1 ,1};
    
//     public int solution(int n, long l, long r) {
//         int answer = 0;
        
//         long[] lens = new long[21];
//         long[] zeros = new long[21];
        
//         lens[0] = 1;
//         lens[1] = 5;
//         zeros[1] = 1;
        
//         for (int i = 1; i <= 20; i++) {
//             lens[i] = lens[i - 1] * 5;
//             zeros[i] = zeros[i - 1] * 4 + lens[i - 1];
//         }
        
//         // System.out.println("[ lens ]");
//         // System.out.println(Arrays.toString(lens));
//         // // System.out.println("");
//         // System.out.println("");
//         // // System.out.println("");
//         // System.out.println("[ zeros ]");
//         // System.out.println(Arrays.toString(zeros));
        
//         // 1) r까지의 1의 갯수 구하기

        
        
//         return answer;
//     }
// }

// public class Solution {
//     public long solution(int n, long l, long r) {
//         // System.out.println("=== 유사 칸토어 비트열 재귀 탐색 시작 ===\n");
//         long res = countCantor(n, l, r, 0);
//         // System.out.println("\n=== 탐색 끝. 총 1의 개수: " + res + " ===");
//         return res;
//     }

//     // depth를 통한 indent 및 재귀 단계 표기
//     private long countCantor(int n, long l, long r, int depth) {
//         String indent = "│ ".repeat(depth);
//         // 단계 진입 구분선
//         // System.out.printf("%s┌──[단계 n=%d] [l=%d, r=%d] 탐색 시작\n", indent, n, l, r);

//         if (n == 0) {
//             int cnt = (l <= 1 && r >= 1) ? 1 : 0;
//             // System.out.printf("%s│   [n=0] (기저: 0번째 비트열, l=%d, r=%d), return %d\n", indent, l, r, cnt);
//             // System.out.printf("%s└──[단계 n=%d] [l=%d, r=%d] 종료, 반환=%d\n\n", indent, n, l, r, cnt);
//             return cnt;
//         }

//         long result = 0;
//         long len = (long)Math.pow(5, n - 1);

//         for (int i = 0; i < 5; i++) {
//             long start = i * len + 1;
//             long end = (i + 1) * len;
//             // System.out.printf("%s│ 블록%d: [%d~%d] ", indent, i, start, end);

//             if (l > end || r < start) {
//                 // System.out.println("(구간 미포함! 스킵)");
//                 continue;
//             }

//             if (i == 2) {
//                 // System.out.println("(가운데 블록, 항상 0! 스킵)");
//                 continue;
//             }

//             long newL = Math.max(l, start) - start + 1;
//             long newR = Math.min(r, end) - start + 1;
//             // System.out.printf("(재귀: n=%d, l=%d, r=%d) ⇒ 블록내 실제 [%d~%d]\n", n - 1, newL, newR, Math.max(l, start), Math.min(r, end));

//             result += countCantor(n - 1, newL, newR, depth + 1);
//         }

//         // 단계 반환 구분선 및 줄바꿈
//         // System.out.printf("%s└──[단계 n=%d] [l=%d, r=%d] 종료, 반환=%d\n\n", indent, n, l, r, result);
//         return result;
//     }
// }

// 진짜 미친 풀이

// class Solution {
//     public int solution(int n, long l, long r) {
//         //
//         long answer = r-l+1;
//         for(long i=l-1;i<=r-1;i++){
//             for(int j=0;j<n;j++){
//                 if((i/(int)Math.pow(5,j))%5==2){
//                     answer--;
//                     break;
//                 }
//             }
//         }

//         return (int) answer;
//     }
// }

// 또는
    
public class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        for (long i = l - 1; i < r; i++) {
            if (func(i)) answer++;
        }
        return answer;
    }

    // 인덱스 i가 "1"이면 true, "0"이면 false
    private boolean func(long i) {
        if (i % 5 == 2) return false;
        if (i < 5) return true;
        
        // 
        return func(i / 5);
    }
}
