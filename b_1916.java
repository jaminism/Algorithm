import java.io.*;
import java.util.*;

/**
 * 백준, 1916
 * 최소 비용 구하기
 * 
 * 
 * condition : 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 * 
 **/
 
 public class Main {
	static int N, M;
	static int[][] map;
	static int[] visited;
	static int start, end;
	static int INF = Integer.MAX_VALUE-1;
	static int[] cost;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N+1][N+1];
		visited = new int[N+1];
		cost = new int[N+1];
        
        for (int[] arr : map) 
  			Arrays.fill(arr, -1);        
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt(); 
			int b = sc.nextInt();
            int c = sc.nextInt();
            
			if (map[a][b] == -1) 
				map[a][b] = c;
            else if (map[a][b] > c)
                map[a][b] = c;
		}
		
		for (int i = 1; i <= N; i++) {
			cost[i] = INF;
			visited[i] = 0;
		}
		
		start = sc.nextInt();
		end = sc.nextInt();
		
		dijkstra();
        
		System.out.println(cost[end]);		
	}

	public static void dijkstra () {
		cost[start] = 0;
		
		for (int i = 0; i < N; i++) {
            int from = -1;
			int min = INF+1;
			for (int j = 1; j <= N; j++) {
				if (visited[j] == 0 && min > cost[j]) {
					min = cost[j];
					from = j;
				}
			}
			visited[from] = 1;
			
			for (int to = 1; to <= N; to++) {
				if (cost[to] > cost[from] + map[from][to] && map[from][to] != -1 && cost[from] != INF) 
					cost[to] = cost[from] + map[from][to];
			}
		}
	}
}
