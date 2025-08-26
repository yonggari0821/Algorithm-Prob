import java.util.*;

/*
    [우박수열]

    1. 짝수면 2로 나누기
    2. 홀수면 3을 곱하고 1 더하기
    3. 아직 1보다 크다면 1~2 반복
    
    ex)
    5 => 16 => 8 => 4 => 2 => 1
    
    이걸 n번째의 값이 m일 때,
    (n,m)의 좌표 평면 위에 표시한다고 쳤을 때,
    이를 정적분 하려고 함.
    
    1) 정적분 [a, b]   
    x = a, x = b, y = 0 으로 둘러 쌓인 공간의 면적
    
    2) 정적분 [a, -b]   
    x = a, x = n - b, y = 0 으로 둘러 쌓인 공간의 면적
    
    3) 정적분 유효하지 않은 범위
    -1
    
    
    넓이 구하는 방법
    전체 사각형 넓이: 더 큰 쪽 y값 x 1
    거기서 빼야하는 삼각형 넓이: (더 큰 쪽 y값 - 더 작은 쪽 y값) / 2
*/

class Solution {
    
    private static double[] di;
    
    private static double getD(double big, double small) {
        return (big + small) / 2;
    }
    
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(k);
        while(k > 1) {
            if (k % 2 == 0) k /= 2;
            else if (k % 2 == 1) {
                k *= 3;
                k += 1;
            }
            list.add(k);
        }
        
        System.out.println(list.toString());
        
        double[] di = new double[list.size()];
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) di[i] = getD(list.get(i), list.get(i - 1)) + di[i - 1];
            else di[i] += getD(list.get(i - 1), list.get(i)) + di[i - 1];
        }
        
        for (int i = 0; i < answer.length; i++) {
            if (ranges[i][1] <= 0) {
                if (ranges[i][0] > list.size() - 1 + ranges[i][1]) answer[i] = -1.0;
                else answer[i] = di[list.size() - 1 + ranges[i][1]] - di[ranges[i][0]];
            } else {
                if (ranges[i][0] >= ranges[i][1]) answer[i] = -1.0;
                else answer[i] = di[ranges[i][1]] - di[ranges[i][0]];
            }
        }
        
        return answer;
    }
}