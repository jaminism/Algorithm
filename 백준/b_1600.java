import java.io.*;
import java.util.*;

/**
 * 백준, 1600
 * 말이 되고픈 원숭이
 * 
 * 
 * condition : 첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.
 * 
 * 8 방위로 이용하여 bfs 해결
 **/

public class Main {
	static int K, C, R;
	static int[][] map;
	static boolean[][][] visited;
	static int result;
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1 ,-2, -1, 0, 1, 0};
	static int[] dy = {-1, -2, -2 ,-1, 1, 2, 2, 1, 0, -1, 0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		K = sc.nextInt();
		C = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[R][C];
		visited = new boolean[K+1][R][C];
		result = -1;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0, 0, 0});
		bfs(queue);
		
		System.out.println(result);
	}

	public static void bfs(Queue<int[]> queue) {
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int x = tmp[0];
			int y = tmp[1];
			int cnt = tmp[2];
			int cycle = tmp[3];
			
			if (x==C-1 && y==R-1) {
				result = cycle;
				break;
			}
			
			for (int i = 8; i < 12; i++) {
				int mx = x + dx[i];
				int my = y + dy[i];
				
				if (mx >= 0 && my >= 0 && mx < C && my < R && !visited[cnt][my][mx] && map[my][mx] != 1) {
					visited[cnt][my][mx] = true;
					queue.add(new int[] {mx, my, cnt, cycle+1});
				}
			}
			
			if (cnt < K) {
				for (int i = 0; i < 12; i++) {
					int mx = x + dx[i];
					int my = y + dy[i];
					
					if (mx >= 0 && my >= 0 && mx < C && my < R && !visited[cnt+1][my][mx] && map[my][mx] != 1) {
						visited[cnt+1][my][mx] = true;
						queue.add(new int[] {mx, my, cnt+1, cycle+1});
					}
				}
			}
		}
	}
}
