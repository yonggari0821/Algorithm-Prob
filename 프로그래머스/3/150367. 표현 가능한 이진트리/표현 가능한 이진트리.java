import java.util.*;
/*
이진트리에서 리프 노드가 아닌 노드는 자신의 왼쪽 자식이 루트인 서브트리의 노드들보다 오른쪽에 있으며, 
자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다고 가정합니다.
=> 중위 순회 (좌 / 자기 / 우)

길이는
1 / 1 + 2 = 3 / 1 + 2 + 4 = 7 / 1 + 2 + 4 + 8 = 15 

0 => 안됨(1이 무조건 루트노드에 한개는 들어가므로)
1
010 => 2
011 => 3
110 => 6
111 => 7
0001000 => 8
0001010 => 10
0001011 => 11
0001111 => 15
0101000 => 40
0101010 => 42
0101011 => 43
0101110 => 46
0101111 => 47
0111111 => 63
1101000 => 104
1101010 => 106
1101011 => 107
1101111 => 111
1111111 => 127
.
.
.

일단 숫자를 Long.toBinaryString()에 넣어서 이진수를 만들고
포화 이진트리의 자릿수에 비해 딸리는 만큼(gap) 0으로 채운 후에
검증

*/
class Solution {
    
    private static String curStr;
    private static int isPossible(int l, int r) {
        
        
        if (l > r) return 1;
        int mid = l + (r - l) / 2;
        char root = curStr.charAt(mid);
        // 현재 루트가 0 => 아래 전부 0
        if (root == '0') {
            // 해당 범위가 모두 0인지 확인
            for (int i = l; i <= r; i++) {
                if (curStr.charAt(i) == '1') return 0;
            }
            return 1; // 재귀 중단
        }
        // 현재 1 => 아래 1 또는 0 둘 다 가능
        
        return isPossible(l, mid - 1) & isPossible(mid + 1, r);
    }
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        // int binaryNum = 1101000;
        // String binaryStr = String.valueOf(binaryNum);
        // int decimalNum = Integer.parseInt(binaryStr, 2);
        // System.out.println(decimalNum);
        // System.out.println(Integer.toBinaryString(decimalNum));
        // System.out.println((binaryStr.equals(Integer.toBinaryString(decimalNum))) ? "동일" : "다름");
        
        for (int i = 0; i < numbers.length; i++) {
            long cur = numbers[i];
            String binaryStr = Long.toBinaryString(cur);
            int digit = 1;
            int power = 1;
            while (digit < binaryStr.length()) {
                power *= 2;
                digit += power;
            }
            int gap = digit - 1 - binaryStr.length();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < gap; j++) sb.append('0');
            sb.append(binaryStr);
            curStr = sb.toString();
            // System.out.println(curStr);
            int mid = curStr.length() / 2;
            answer[i] = isPossible(0, curStr.length() - 1);
        }
        return answer;
    }
}