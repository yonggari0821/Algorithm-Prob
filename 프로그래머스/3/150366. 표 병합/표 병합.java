import java.util.*;

class Cell {
    int r, c;
    Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    private static String[][] table = new String[51][51];
    private static Cell[][] parent = new Cell[51][51];

    static {
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                table[r][c] = "";
                parent[r][c] = new Cell(r, c);
            }
        }
    }

    // 유니온 파인드 find
    private static Cell find(Cell cell) {
        if (parent[cell.r][cell.c].r == cell.r && parent[cell.r][cell.c].c == cell.c) {
            return parent[cell.r][cell.c];
        }
        return parent[cell.r][cell.c] = find(parent[cell.r][cell.c]);
    }

    // 유니온 파인드 union
    private static void union(Cell c1, Cell c2) {
        Cell root1 = find(c1);
        Cell root2 = find(c2);
        if (root1.r == root2.r && root1.c == root2.c) return;
        parent[root2.r][root2.c] = root1;
    }

    // (r,c)가 속한 그룹 전체에 val을 할당
    private static void update(int r, int c, String val) {
        Cell root = find(new Cell(r, c));
        for (int row = 1; row <= 50; row++) {
            for (int col = 1; col <= 50; col++) {
                if (find(new Cell(row, col)).r == root.r && find(new Cell(row, col)).c == root.c) {
                    table[row][col] = val;
                }
            }
        }
    }

    // val1을 가진 모든 셀을 val2로 변경 (병합 그룹 전체에 적용)
    private static void update(String val1, String val2) {
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                if (table[r][c].equals(val1)) {
                    update(r, c, val2);
                }
            }
        }
    }

    // 병합
    private static void merge(int r1, int c1, int r2, int c2) {
        Cell cell1 = new Cell(r1, c1);
        Cell cell2 = new Cell(r2, c2);
        Cell root1 = find(cell1);
        Cell root2 = find(cell2);

        if (root1.r == root2.r && root1.c == root2.c) return;

        String val1 = table[root1.r][root1.c];
        String val2 = table[root2.r][root2.c];
        String repVal = !val1.equals("") ? val1 : val2;

        union(root1, root2);

        Cell newRoot = find(root1);
        for (int row = 1; row <= 50; row++) {
            for (int col = 1; col <= 50; col++) {
                if (find(new Cell(row, col)).r == newRoot.r && find(new Cell(row, col)).c == newRoot.c) {
                    table[row][col] = repVal;
                }
            }
        }
    }

    // 병합 해제
    private static void unMerge(int r, int c) {
        Cell root = find(new Cell(r, c));
        String repVal = table[r][c];

        List<Cell> groupCells = new ArrayList<>();
        for (int row = 1; row <= 50; row++) {
            for (int col = 1; col <= 50; col++) {
                if (find(new Cell(row, col)).r == root.r && find(new Cell(row, col)).c == root.c) {
                    groupCells.add(new Cell(row, col));
                }
            }
        }
        for (Cell cell : groupCells) {
            parent[cell.r][cell.c] = new Cell(cell.r, cell.c);
            table[cell.r][cell.c] = "";
        }
        table[r][c] = repVal;
    }

    private static String print(int r, int c) {
        if (table[r][c].equals("")) return "EMPTY";
        return table[r][c];
    }

    public String[] solution(String[] commands) {
        // 매 테스트케이스마다 static 초기화 필요!
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                table[r][c] = "";
                parent[r][c] = new Cell(r, c);
            }
        }

        List<String> ansList = new ArrayList<>();
        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String com = st.nextToken();

            if (com.equals("UPDATE")) {
                if (st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String val = st.nextToken();
                    update(r, c, val);
                } else {
                    String val1 = st.nextToken();
                    String val2 = st.nextToken();
                    update(val1, val2);
                }
            } else if (com.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(r1, c1, r2, c2);
            } else if (com.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unMerge(r, c);
            } else if (com.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                ansList.add(print(r, c));
            }
        }
        return ansList.toArray(new String[0]);
    }
}
