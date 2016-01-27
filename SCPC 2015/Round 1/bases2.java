import java.util.*;
import java.io.*;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class bases2 {
	public static void main(String args[]) throws Exception	{
		Reader sc = new Reader(System.in);
		int T, num;
		int test_case;
        int curr, prev, acc;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
        	num = sc.nextInt(); 
            if (num < 3) {
               	System.out.println("Case #" + test_case);
                System.out.println("2");
                continue;
            }
                
            for (int b = 2; b < 32000; b++) {
                curr = num%b;
                prev = num%b;
                acc = (num-num%b)/b;
                curr = acc%b;
                while (acc != 0 && curr == prev) {
					prev = curr;
                	curr = acc%b;
                    acc = (acc-curr)/b;
            	}
                if (curr == prev) {
                    num = 0;
                    System.out.println("Case #" + test_case);
                    System.out.println("" + b);
                    break;
                }
            }
            
            if (num != 0)
                for (int i = 31250; i > 0; i--) 
                	if (num%i == 0) {
                		System.out.println("Case #" + test_case);
                		System.out.println("" + (num/i-1));
                		break;
                	}
            
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