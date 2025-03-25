import java.util.*;

class Solution {
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int cr, cc;
    static int rowLimit, colLimit;
    static int minR, minC, maxR, maxC;
    static boolean cannotArrive;

    static void doQuery(int queryOption, int val) {
        if (cannotArrive) return;
        switch (queryOption) {
            case 0:
                wentLeft(val);
                break;
            case 1:
                wentRight(val);
                break;
            case 2:
                wentUp(val);
                break;
            case 3:
                wentBelow(val);
                break;
            default:
                break;
        }
//        System.out.println("--------------------------------");
//        System.out.println("[min] (" + minR + " , " + minC + ")");
//        System.out.println("[max] (" + maxR + " , " + maxC + ")");
//        System.out.println("--------------------------------");

    }

    static void wentRight(int val) {
        // 오른쪽으로 움직였다 = 왼쪽에서 왔을 수 있다
        // 벽에 닿아서 더 이상 못 갔을 수도 있으므로 maxC가 colLimit이면 minC만 조정
        if (maxC == colLimit) {
            minC = Math.max(0, minC - val);
        } else {
            // 벽에 닿지 않았으면 둘 다 조정
            minC = Math.max(0, minC - val);
            maxC = maxC - val;
            // 여기서 maxC가 0보다 작아지면 도달 불가능
            if (maxC < 0) {
                cannotArrive = true;
            }
        }
    }

    static void wentLeft(int val) {
        // 왼쪽으로 움직였다 = 오른쪽에서 왔을 수 있다
        // 벽에 닿아서 더 이상 못 갔을 수도 있으므로 minC가 0이면 maxC만 조정
        if (minC == 0) {
            maxC = Math.min(colLimit, maxC + val);
        } else {
            // 벽에 닿지 않았으면 둘 다 조정
            minC = minC + val;
            maxC = Math.min(colLimit, maxC + val);
            // 여기서 minC가 colLimit보다 커지면 도달 불가능
            if (minC > colLimit) {
                cannotArrive = true;
            }
        }
    }

    static void wentBelow(int val) {
        // 아래로 움직였다 = 위에서 왔을 수 있다
        // 벽에 닿아서 더 이상 못 갔을 수도 있으므로 maxR이 rowLimit이면 minR만 조정
        if (maxR == rowLimit) {
            minR = Math.max(0, minR - val);
        } else {
            // 벽에 닿지 않았으면 둘 다 조정
            minR = Math.max(0, minR - val);
            maxR = maxR - val;
            // 여기서 maxR이 0보다 작아지면 도달 불가능
            if (maxR < 0) {
                cannotArrive = true;
            }
        }
    }

    static void wentUp(int val) {
        // 위로 움직였다 = 아래에서 왔을 수 있다
        // 벽에 닿아서 더 이상 못 갔을 수도 있으므로 minR이 0이면 maxR만 조정
        if (minR == 0) {
            maxR = Math.min(rowLimit, maxR + val);
        } else {
            // 벽에 닿지 않았으면 둘 다 조정
            minR = minR + val;
            maxR = Math.min(rowLimit, maxR + val);
            // 여기서 minR이 rowLimit보다 커지면 도달 불가능
            if (minR > rowLimit) {
                cannotArrive = true;
            }
        }
    }


    static long getCount() {
        long rowLen = maxR - minR + 1;
        long colLen = maxC - minC + 1;
        return rowLen * colLen;
    }

    public long solution(int n, int m, int x, int y, int[][] queries) {
        minR = x;
        minC = y;
        maxR = x;
        maxC = y;
        cr = x;
        cc = y;
        rowLimit = n - 1;
        colLimit = m - 1;
        cannotArrive = false;

        int queryNum = queries.length;
        for (int i = queryNum - 1; i >= 0; i--) {
            doQuery(queries[i][0], queries[i][1]);
        }

        if (cannotArrive) return 0;
        return getCount();
    }
}