import java.util.*;

/*
    벽 위의 한 점을 잡고
    기울기(y변화량의 절댓값/x변화량의 절댓값)가 같으면서
    점까지의 이동거리의 제곱이 최소가 되는 점 찾기
    
    벽 위의 점이므로
    x나 y의 값이 0 또는 m 또는 n이어야 함.
    
    위쪽) y = 0
    아래쪽) y = n
    왼쪽) x = 0
    오른쪽) x = m
    
    그냥 4방향으로 따지되, 도착지점을 벽 밖의 임의이 점이라고 넘겨서 두 점 사이의 거리를 구하면 됨
*/

class Point {
    int x;
    int y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    private static int sx, sy, maxX, maxY;
    private static int[] answer;
    private static int[] min;
    private static int getDist(int x1, int x2, int y1, int y2) {
        return (int) ( (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) );
    }
    private static int find(int fx, int fy) {
        int minDist = Integer.MAX_VALUE;
        
        // 상 // 도착지점의 y 부호 바꾸기 // 단 시작지점의 x와 도착지점의 x가 같고, fy <= sy이면 제외!
        if (!(fx == sx && fy <= sy)) {
            minDist = Math.min(getDist(sx, fx, sy, -fy), minDist);
        }
        
        // 하 // 도착지점의 y에 maxY 더하기 // 단 시작지점의 x와 도착지점의 x가 같고, fy >= sy이면 제외!
        if (!(fx == sx && fy >= sy)) {
            minDist = Math.min(getDist(sx, fx, sy, 2*maxY - fy), minDist);
        }
        
        // 좌 // 도착지점의 x 부호 바꾸기 // 단 시작지점의 y와 도착지점의 y가 같고, fx <= sx이면 제외!
        if (!(fy == sy && fx <= sx)) {
            minDist = Math.min(getDist(sx, -fx, sy, fy), minDist);
        }
        
        // 우 // 도착지점의 x에 maxX 더하기 // 단 시작지점의 y와 도착지점의 y가 같고, fx >= sx이면 제외!
        if (!(fy == sy && fx >= sx)) {
            minDist = Math.min(getDist(sx, 2*maxX - fx, sy, fy), minDist);
        }
            
        return minDist;
    }
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        answer = new int[balls.length];
        min = new int[balls.length];
        sx = startX;
        sy = startY;
        maxX = m;
        maxY = n;
        
        for (int i = 0; i < balls.length; i++) {
            answer[i] = find(balls[i][0], balls[i][1]);
        }
        
        return answer;
    }
}