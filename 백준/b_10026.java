import java.io.*;
import java.util.*;

/**
 * 백준, 10026
 * 적록색약
 * 
 * 
 * condition : 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
 * 
 * DFS로 문제를 풀었다.
 * @author jaminism
 **/

public class Main {
	static int N;
	static String[] arr;
	static char[][] map;
	static char[][] chgMap;
	static int count, count1;
	static boolean[][] visited;
  // 좌, 상, 우, 하
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("d://sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new char[N][N];
		chgMap = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String a = sc.next();
			map[i] = a.toCharArray();
			chgMap[i] = a.toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G') {
					chgMap[i][j] = 'R';
				}
			}
		}
		visited = new boolean[N][N];		
		count = 0;
		
		//Arrays.fill(visited, false);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(map, i, j);
					count=count+1;
				}
			}
		}
		
		//
		//Arrays.fill(visited, false);
		visited = new boolean[N][N];		
		count1 = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(chgMap, i, j);
					count1=count1+1;
				}
			}
		}
		System.out.println(count + " " + count1);
	}

	public static void dfs (char[][] map, int sy, int sx) {
		if (visited[sy][sx] == true && sx != 0 && sy != 0) {
			return;
		}
		visited[sy][sx]=true;
		char tmp = map[sy][sx];
		
		for (int i = 0; i < 4; i++) {
			int x = sx + dx[i];
			int y = sy + dy[i];
			
			if (x >= 0 && x < N && y >= 0 && y < N && visited[y][x]==false && map[y][x] == tmp) {
				//System.out.println("x = " + x + ",  y = " + y);
				dfs(map, y, x);
			}
		}
	}
}
