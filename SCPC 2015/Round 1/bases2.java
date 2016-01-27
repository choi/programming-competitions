import java.util.*;
import java.io.*;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class bases2 {
	public static void main(String args[]) throws Exception	{
		Reader sc = new Reader(System.in);
		int T, num;
		int test_case;
        int curr, prev, acc;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
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