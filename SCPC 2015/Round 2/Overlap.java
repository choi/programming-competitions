import java.util.*;
import java.io.*;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class Overlap {
	public static void main(String args[]) throws Exception	{
		/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
		   만약 본인의 PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
		   표준입력 대신 input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
		   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
		   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        
		Reader sc = new Reader("input.txt");

		int T, N, max;
		int test_case;
		Pair[] pairs;
		Pair p;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
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
			

			// 이 부분에서 정답을 출력하십시오.
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