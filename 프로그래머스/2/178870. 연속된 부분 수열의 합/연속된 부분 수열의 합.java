import java.util.*;

/*
1000 * 1000000 = 10억 <= 2147483647(Integer.MAX_VALUE)
그냥 int 배열로 해도 무방!

2중 for문으로하면
sequence의 길이가 최대 100만이므로 시간초과가 발생
=> 그럼 어떻게 풀어야하나요?

연속된 부분합
정렬된 배열
모든 원소가 0 이상
=> 투 포인터

*/

// 처음 풀이
// class Solution {
//     public int[] solution(int[] sequence, int k) {
//         int[] answer;
        
//         ArrayList<Integer> ansList = new ArrayList<>();
        
//         int[] sum = new int[sequence.length + 1];
//         for (int i = 1; i <= sequence.length; i++) sum[i] = sum[i - 1] + sequence[i - 1];
//         int subSum = 0;
        
//         // [sequence]
//         // idx // 0 1 2 3 4 5 6 7
//         // val // 1 1 1 2 3 4 5 6
        
//         // [sum]
//         // idx // 0 1 2 3 4 5 6  7  8
//         // val // 0 1 2 3 5 8 12 17 23
        
//         // k = 5
        
//         // ansList
//         // 6,6
        
//         // 그 때의 s와 e값
//         // 6,7

//         for (int s = 0; s < sum.length; s++) {
//             if (s < sequence.length && sequence[s] > k) break;
//             for (int e = s + 1; e < sum.length; e++) {
//                 subSum = sum[e] - sum[s];
//                 if (subSum == k) {
//                     if (ansList.size() == 0 || e - 1 - s < ansList.get(1) - ansList.get(0)) {
//                         // System.out.println("s = " + s + " , e = " + e);
//                         // System.out.println("sval = " + sequence[s] + " , eval = " + sequence[e - 1]);
                        
//                         ansList = new ArrayList<>();
//                         ansList.add(s);
//                         ansList.add(e - 1);
//                     }
//                 }
//                 else if (subSum > k) break;
//             }
//         }
        
//         answer = ansList.stream().mapToInt(i -> i).toArray();
//         return answer;
//     }
// }

// ----------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------

// 고친 풀이 (투포인터 활용)
class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int left = 0; // 탐색 인덱스 왼쪽
        int right = 0; // 탐색 인덱스 오른쪽
        int al = left; // 답 왼쪽
        int ar = right; // 답 오른쪽
        int gap = sequence.length; // 탐색 & 답 gap(오른쪽 - 왼쪽)
        // int ar = sequence.length - 1; // 답 오 른쪽
        // int gap = ar - al; // 탐색 & 답 gap(오른쪽 - 왼쪽)
        int sum = sequence[0]; // 현재 구간합
        
        while (right < sequence.length) {
            // 만족하는 경우 => 더 짧은 길이가 있을 수 있으므로 left만 올려보기!
            if (sum == k) {
                if (right - left < gap) {
                    al = left;
                    ar = right;
                    gap = ar - al;
                }
                sum -= sequence[left];
                left++;
            }
            // 더 작은 경우 => r 증가
            else if (sum < k) {
                right++;
                if (right < sequence.length) sum += sequence[right];
            }
            // 더 큰 경우 => l 증가
            else {
                sum -= sequence[left];
                left++;
            }
        }
        
        return new int[] {al, ar};
    }
}