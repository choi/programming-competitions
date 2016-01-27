import java.util.*;
import java.io.*;
import java.math.*;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class College2 {
	public static void main(String args[]) throws Exception	{
		/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
		   만약 본인의 PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
		   표준입력 대신 input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
		   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
		   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        
		Reader sc = new Reader("input.txt");

		int T, M, N;
		int v1, v2, w;
		int test_case;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
			N = sc.nextInt();
			M = sc.nextInt();
			
			int[][] graph = new int[N+1][N+1]; 
			int[] connect = new int[N+1];
			int[][] neighbors = new int[N+1][N+1];
			
			// graph initialization
			for (int i = 0; i < M; i++) {
				v1 = sc.nextInt();
				v2 = sc.nextInt();
				w = sc.nextInt();
				graph[v1][v2] = w;
				graph[v2][v1] = w;
				neighbors[v1][0] += 1;
				neighbors[v1][neighbors[v1][0]] = v2;
				neighbors[v2][0] += 1;
				neighbors[v2][neighbors[v2][0]] = v1;
			}
			
			// loop through vertices as source for dijkstras
			// keep track of paths taken
			Long u;
			int iu;
			int[] dist = new int[N+1];
			int[][] prev = new int[N+1][2];
			PriorityQueue<Long> q = new PriorityQueue<Long>(N);
			for (int i = 1; i < N; i++) {
				q.clear();
				
				for (int j = 1; j <= N; j++) {
					dist[j] = 10000001;
					prev[j][0] = -1;
					
					q.add((long) dist[j]*10000+j);
				}
				
				dist[i] = 0;
				
				while (q.size() > 0) {
					u = q.poll();
					iu = (int) (u%10000);
					for (int k = 1; k <= neighbors[iu][0]; k++) {
						int j = neighbors[iu][k];
						if (graph[iu][j] != 0) 
							if (dist[j] > dist[iu] + graph[iu][j]) {
								q.remove((long) dist[j]*10000+j);
								dist[j] = dist[iu] + graph[iu][j];
								prev[j][0] = iu;
								prev[j][1] = j;
								q.add((long) dist[j]*10000+j);
							}
							else if (dist[j] == dist[iu] + graph[iu][j] && prev[j][1] == j) {
								prev[j][1] = -1;
							}
					}
				}
				for (int j = 1; j <= N; j++)
					if (prev[j][0] != i && prev[j][0] != -1 && prev[j][1] >= 1)
						connect[prev[j][0]] = 1;
				
			}
        
			// list all numbers that are not connections
			int count = 0;
			String s = "";
			for (int i = 1; i <= N; i++)
				if (connect[i] == 1) {
					count++;
					s += " " + (i);
				}
			
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println("" + count + s);
		}
	}

    static class Reader {
		static BufferedReader reader;
		static StringTokenizer tokenizer;
		
        Reader(String input) throws FileNotFoundException {
			reader = new BufferedReader(new FileReader(input));
			tokenizer = new StringTokenizer("");
        }

		Reader(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
            tokenizer = new StringTokenizer("");	
        }

		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens())
				tokenizer = new StringTokenizer(reader.readLine());
			return tokenizer.nextToken();
        }

		static int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}