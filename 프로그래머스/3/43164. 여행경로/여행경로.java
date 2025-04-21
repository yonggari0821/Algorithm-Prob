import java.util.*;

class Solution {
    
    static boolean[] visited;
    static List<String> fRoute = new ArrayList<>();
    
    static void dfs(List<String> route, String[][] tickets, int cnt) {
        if (cnt == visited.length) {
            if (fRoute.isEmpty()) {
                fRoute = new ArrayList(route);
            }
            return ;
        }
        
        String lastVisit = route.get(route.size() - 1);
        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) continue;
            if (tickets[i][0].equals(lastVisit)) {
                visited[i] = true;
                route.add(tickets[i][1]);
                dfs(route, tickets, cnt + 1);
                route.remove(route.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, new Comparator<String[]>(){
            @Override
            public int compare(String[] a, String[] b) {
                if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
                return a[0].compareTo(b[0]);
            }
        });
        
        visited = new boolean[tickets.length];
        List<String> route = new ArrayList<String>();
        route.add("ICN");
        
        dfs(route, tickets, 0);
        
        return fRoute.toArray(new String[fRoute.size()]);
    }
}