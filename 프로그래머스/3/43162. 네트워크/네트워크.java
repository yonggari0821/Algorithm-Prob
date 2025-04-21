import java.util.*;

// /*
// 최소 신장 트리 풀듯이 풀었는데 안됨.
// 반례
// 6
// [[1, 1, 0, 0, 0, 1], [1, 1, 0, 0, 0, 0], [0, 0, 1, 0, 0, 0], [0, 0, 0, 1, 0, 1], [0, 0, 0, 0, 1, 0], [1, 0, 0, 1, 0, 1]]
// 답: 3

//  3   1   5
//     / \
//    2   6 - 4
   
// 다른 방식으로 풀어야 함.
// */

// class Solution {
//     static int[] parent;
    
//     static void union(int a, int b) {
//         int Pa = parent[a];
//         int Pb = parent[b];
//         if (Pa != Pb) {
//             if (Pa < Pb) parent[b] = find(parent[a]);
//             else parent[a] = find(parent[b]);
//         }
//     }
    
//     static int find(int a) {
//         if (parent[a] == a) return a;
//         return parent[a] = find(a);
//     }
    
//     public int solution(int n, int[][] computers) {
//         int answer = 0;
        
//         parent = new int[computers.length];
//         for (int i = 0; i < parent.length; i++) {parent[i] = i;}
        
//         for (int r = 0; r < computers.length; r++) {
//             for (int c = r + 1; c < computers.length; c++) {
//                 if (computers[r][c] == 1) {
//                     union(r, c);
//                 }
//             }
//         }
        
//         Set<Integer> set = new HashSet<>();
//         for (int i = 0; i < parent.length; i++) {
//             set.add(parent[i]);
//             // System.out.println("parent[" + (i+1) + "] = " + (parent[i] + 1));
//         }
//         return answer = set.size();
//     }
// }

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int len = computers.length;
        boolean[] visited = new boolean[len];
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                q.offer(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int c = 0; c < len; c++) {
                        if (!visited[c]) {
                            if (computers[cur][c] == 1) {
                                visited[c] = true;
                                q.offer(c);
                            }
                        }
                    }
                }
                answer++;
            }
        }
        
        return answer;
    }
}