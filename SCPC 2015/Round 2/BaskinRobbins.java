import java.util.*;
import java.io.*;

class BaskinRobbins {
	public static void main(String args[]) throws Exception	{
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Reader sc = new Reader("input.txt");

		int T,a,b,c,N,K;
		int test_case;
        String ans;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
        	a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            ans = "";
            for(int i = 0; i < c; i++) {
                N = sc.nextInt();
                K = sc.nextInt();
                if (a == b) {
                    if ((N-1)%(b*K+b) <= b-1)
                        ans += "b";
                    else
                        ans += "a";
                    continue;
                }
                if (a*K >= N-1) {
                    ans += "a";
                    continue;
                }
                if (a*K+b >= N-1 && a+b*K >= N-1) {
                	ans += "b";
                	continue;
            	}
                if (a*(K-2) >= b*K-1) {
                    ans += "a";
                    continue;
                }
                boolean flag = false;
                for (int j = a-b; j <= K*(a-b); j++) {
                    int M = b*(K+1)+j;
                    int mod = (N-1)%M;
                    if ((mod <= (a*K)%M && a%M <= mod) || (a%M > (a*K)%M && (mod <= (a*K)%M || a%M <= mod))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    ans += "a";
                    continue;
                }
        		ans += "b";
            }
			// �� �κп��� ������ ����Ͻʽÿ�.
			System.out.println("Case #" + test_case);
			System.out.println(ans);
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