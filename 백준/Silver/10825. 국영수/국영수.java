import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int korean;
    int english;
    int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    @Override
    public int compareTo(Student o) {
        if (o.korean == this.korean)
        {
            if (o.english == this.english)
            {
                if (o.math == this.math)
                {
                    int i = 0;
                    int min = Math.min(o.name.length(), this.name.length());
                    while (i < min)
                    {
                        if (!(o.name.charAt(i) == this.name.charAt(i))) return this.name.charAt(i) - o.name.charAt(i);
                        i++;
                    }
                    return this.name.length() - o.name.length();
                }
                return o.math - this.math;
            }
            return this.english - o.english;
        }
        return o.korean - this.korean;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Student> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        while (!pq.isEmpty()) ans.append(pq.poll().name).append('\n');
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}