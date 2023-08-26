import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    static HashMap<String, Double> treeAndCnt;
    static ArrayList<String> toArrange;
    static double total = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        treeAndCnt = new HashMap<>();
        toArrange = new ArrayList<>();

        while(true)
        {
            String tree = br.readLine();
            if (tree == null || tree.equals("")) break;
            else
            {
                treeAndCnt.put(tree, treeAndCnt.getOrDefault(tree, (double) 0) + 1);
                total++;
            }
        }

        Iterator<Map.Entry<String, Double>> it = treeAndCnt.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>)it.next();
            String treeName = entry.getKey();
            toArrange.add(treeName);
        }

        Collections.sort(toArrange);
        for (int i = 0; i < toArrange.size(); i++)
        {
            String tmp = toArrange.get(i);
            ans.append(tmp + " " + String.format("%.4f", (treeAndCnt.get(tmp) / total * 100 * 10000) / 10000.0)).append('\n');
        }

        System.out.println(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}