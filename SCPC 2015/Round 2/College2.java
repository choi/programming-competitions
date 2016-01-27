import java.util.*;
import java.io.*;
import java.math.*;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class College2 {
	public static void main(String args[]) throws Exception	{
		/* �Ʒ� �޼ҵ� ȣ���� ������ ǥ���Է�(Ű����) ��� input.txt ���Ϸ� ���� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   ���� ������ PC ���� �׽�Ʈ �� ����, �Է°��� input.txt�� ������ �� �� �ڵ带 ù �κп� ����ϸ�,
		   ǥ���Է� ��� input.txt ���Ϸ� ���� �Է°��� �о� �� �� �ֽ��ϴ�.
		   ����, ���� PC���� �Ʒ� �޼ҵ带 ������� �ʰ� ǥ���Է��� ����Ͽ� �׽�Ʈ�ϼŵ� �����մϴ�.
		   ��, Codeground �ý��ۿ��� "�����ϱ�" �� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ�(//) ó�� �ϼž� �մϴ�. */
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        
		Reader sc = new Reader("input.txt");

		int T, M, N;
		int v1, v2, w;
		int test_case;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
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
			
			// �� �κп��� ������ ����Ͻʽÿ�.
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