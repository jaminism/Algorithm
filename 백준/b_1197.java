import java.io.*;
import java.util.*;

/**
 * 백준, 1197
 * 최소 스패닝 트리
 * 
 * 
 * condition : 첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.
 * 
 * Union-find 로 부모값을 알아낸 뒤 MST 를 구한다. 
 * (Union-find 의 개념은 이해하면 좋을거 같다) 
 * 크루스칼, 프림 두개다 가능
 **/

public class Main {
    static int V;
    static int E;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //System.setIn(new FileInputStream("d://sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        
        ArrayList<Edge> edges = new ArrayList<>();
        
        int sum = 0;
        int edgeCount = 0;
        
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(from, to, cost));
        }
        
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return a.cost - b.cost;
            }
        });
        
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
        
        for (Edge eg : edges) {
            int from = find(eg.from);
            int to = find(eg.to);
            
            if (from != to) {
                union(eg.from, eg.to);
                sum += eg.cost;
                if (++edgeCount == V-1)
                    break;
            }
        }
        
        System.out.println(sum);
    }
    
    public static int find(int x) {
        if (parent[x] == x)
            return x;
        
        int root = find(parent[x]);
        parent[x] = root;
        return root;
        // return parent[x] = find(parent[x]);
    }
    
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        parent[y] = x;
    }
}

class Edge{
    int from, to, cost;
    
    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
