import java.util.*;
import java.io.*;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class Sequences {
	public static void main(String args[]) throws Exception	{
		Reader sc = new Reader("input.txt");

		int T, M;
        long d, prev, temp;
		int test_case;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
        	M = sc.nextInt();
            d = sc.nextLong();
            prev = sc.nextLong();
            d = prev - d;
            for (int i = 2; i < M; i++) {
                temp = sc.nextLong();
        		d = gcd(d, temp - prev);
                prev = temp;
            }
            
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(factors(d));
		}
	}
    public static int factors(long d) {
        int total = 1;
        for (int i = 1; i < d; i++)
            if (d%i == 0)
            	total++;
        return total;
    }
    public static long gcd(long a, long b) {
        return b==0 ? a : gcd(b, a%b);
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