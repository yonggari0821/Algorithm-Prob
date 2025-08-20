import java.util.*;

/*
    테이블
    모두 정수 타입 컬럼
    col번째 컬럼 값 기준으로 오름차순 정렬
    동일하면 첫번째 컬럼 값을 기준으로 내림차순 정렬
    정렬 후 S_i는 i번째 행의 각 컬럼의 값을 i로 나눈 나머지들의 합
    row_begin ≤ i ≤ row_end 인 모든 S_i를 누적하여 XOR 연산한 값을 해시값으로 반환
    
    
*/

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[col - 1] == b[col - 1]) return b[0] - a[0];
                return a[col - 1] - b[col - 1];
            }
        });
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = row_begin; i <= row_end; i++) {
            int tmp = 0;
            for (int j = 0; j < data[0].length; j++) {
                tmp += (data[i - 1][j] % i);
            }
            list.add(tmp);
        }
        
        for (int t : list) {
            // System.out.println("t = " + t);
            answer = answer ^ t;    
        }
        
        return answer;
    }
}