import java.util.*;
import java.io.*;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class Overlap {
	public static void main(String args[]) throws Exception	{
		/* �Ʒ� �޼ҵ� ȣ���� ������ ǥ���Է�(Ű����) ��� input.txt ���Ϸ� ���� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   ���� ������ PC ���� �׽�Ʈ �� ����, �Է°��� input.txt�� ������ �� �� �ڵ带 ù �κп� ����ϸ�,
		   ǥ���Է� ��� input.txt ���Ϸ� ���� �Է°��� �о� �� �� �ֽ��ϴ�.
		   ����, ���� PC���� �Ʒ� �޼ҵ带 ������� �ʰ� ǥ���Է��� ����Ͽ� �׽�Ʈ�ϼŵ� �����մϴ�.
		   ��, Codeground �ý��ۿ��� "�����ϱ�" �� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ�(//) ó�� �ϼž� �մϴ�. */
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        
		Reader sc = new Reader("input.txt");

		int T, N, max;
		int test_case;
		Pair[] pairs;
		Pair p;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
			N = sc.nextInt();
			max = 1;
			pairs = new Pair[N];
			for (int i = 0; i < N; i++) {
				pairs[i] = new Pair(sc.nextInt(), sc.nextInt());	
			}
			Arrays.sort(pairs);
			pairs[0].count = 1;
			for (int i = 1; i < N; i++)
				for (int j = i-1; j >= 0;j--)
					if (pairs[j].eats(pairs[i])) {
						pairs[i].count = pairs[j].count+1;
						if (pairs[i].count > max)
							max = pairs[i].count;
						break;
					}
			

			// �� �κп��� ������ ����Ͻʽÿ�.
			System.out.println("Case #" + test_case);
			System.out.println(max);
		}
	}
	static class Pair implements Comparable<Pair> {
		public int x;
		public int y;
		public int count;
		Pair (int a, int b) {
			this.x = a;
			this.y = b;
			this.count = 0;
		}
		boolean eats (Pair p) {
			return p.x > this.x && p.y < this.y;
		}
		void set (int i) {
			this.count = i;
		}
		public int compareTo(Pair comPair) {
			if (this.y - this.x > comPair.y - comPair.x)
				return -1;
			if (this.y - this.x < comPair.y - comPair.x)
				return 1;
			return 0;
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
           
        static long nextLong() throws IOException {
            return Long.parseLong(next());
        }
	}
}