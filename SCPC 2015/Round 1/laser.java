import java.util.*;
import java.io.*;


class laser {
	public static void main(String args[]) throws Exception	{        
		//Reader sc = new Reader(System.in);
		Reader sc = new Reader("input.txt");

		int T, N;
		int test_case;
		int total, count;
		int direction; // 0 = E, 1 = S, 2 = W, 3 = N
		int r, c;
//		int[][] matrix;
		String line;
		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			int[][] matrix = new int[N][N];
			r = 0;
			c = 0;
			direction = 0;
			total = 0;
			count = 0;
			
			for (int i = 0; i < N; i++) {
				line = sc.next();
				for (int j = 0; j < N; j++)
					matrix[i][j] = (int) (line.charAt(j)) - 48;
			}
			
			while (r >= 0  && r < N && c >= 0 && c < N) {
				if (matrix[r][c] != 0) {
					if (matrix[r][c] < 3)
						count++;
					matrix[r][c] = 2000000 + matrix[r][c]%10; // changing to 20000000 reduces one
					direction ^= (11*(matrix[r][c]%10)-5*(matrix[r][c]%10)*(matrix[r][c]%10))/2;
					// quadratic fit for (2,1), (1,3), (0,0)
				}
				c += (1-direction%10%2) * (1 - direction%10);
				r += (direction%10%2) * (2-direction%10);
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(""+count);
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