import java.util.*;
import java.io.*;
import static java.lang.Math.*;

class Marathon{
	public static void main(String args[]) throws Exception	{
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        
		Reader sc = new Reader("input.txt");
        int[][][] memo;
		int[][] map;
		int T, M, N, K;
		int test_case;
        int diffdown, diffleft, lines, nums;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            memo = new int[N+1][M+1][11];
            map = new int[N+1][M+1];
            
            for(lines = 0; lines <= N; lines++) 
                for(nums = 0; nums <= M; nums++) 
                	map[lines][nums] = sc.nextInt();
           
            //row 0 lines = 0
            for (nums = 1; nums <= M; nums++) {
                diffleft = abs(abs(map[0][nums])-abs(map[0][nums-1]));
                if (map[0][nums] > 0) { //doesn't have water
                	memo[0][nums][0] = memo[0][nums-1][0] + diffleft;
                    for (int i = 0; i < 11; i++) {
                    	if (memo[0][nums-1][i] == 0)
                    		break;
                        memo[0][nums][i] = memo[0][nums-1][i] + diffleft;
                    }
                }
                else {
                    memo[0][nums][0] = memo[0][nums-1][0] + diffleft;
                    for (int i = 1; i < 11; i++){
                    	memo[0][nums][i] = memo[0][nums-1][i-1] + diffleft;
                        if (memo[0][nums-1][i] == 0)
                        	break;
                    }
                }
            }         
            
            //column 0
            for (lines = 1; lines <= N; lines++) {
                diffdown = abs(abs(map[lines][0])-abs(map[lines-1][0]));
                if (map[lines][0] > 0) { //doesnt have water
                    memo[lines][0][0] = memo[lines-1][0][0] + diffdown;
                    for (int i = 0; i < 11; i++) {
                    	if (memo[lines-1][0][i] == 0)
                    		break;
                        memo[lines][0][i] = memo[lines-1][0][i] + diffdown;
                    }
                }
                else {
                    memo[lines][0][0] = memo[lines-1][0][0] + diffdown;
                    for (int i = 1; i < 11; i++) {
                    	memo[lines][0][i] = memo[lines-1][0][i-1] + diffdown;
                        if (memo[lines-1][0][i] == 0)
                        	break;
                    }
                }
            }    

            
            for (lines = 1; lines <= N; lines++) 
                for (nums = 1; nums <= M; nums++) {
                	diffleft = abs(abs(map[lines][nums])-abs(map[lines][nums-1]));
                	diffdown = abs(abs(map[lines][nums])-abs(map[lines-1][nums]));
                	if (map[lines][nums] > 0) { //doesnt have water 
	                    for (int i = 0; i < 11; i++) {
	                    	if (memo[lines-1][nums][i] == 0)
    	                		break;
        	                memo[lines][nums][i] = memo[lines-1][nums][i] + diffdown;
            	        }
                        for (int i = 0; i < 11; i++) {
                    		if (memo[lines][nums-1][i] == 0)
                    			break;
                    		if (memo[lines][nums][i] == 0)
                    			memo[lines][nums][i] = memo[lines][nums-1][i] + diffleft;
                        	memo[lines][nums][i] = min(memo[lines][nums][i], memo[lines][nums-1][i] + diffleft);
	                    }
                    }
	                else {
    	                memo[lines][nums][0] = min(memo[lines-1][nums][0] + diffdown, memo[lines][nums-1][0] + diffleft);
        	            for (int i = 1; i < 11; i++) {
            	        	memo[lines][nums][i] = memo[lines-1][nums][i-1] + diffdown;
                	        if (memo[lines-1][nums][i] == 0)
                    	    	break;
                    	}
                        for (int i = 1; i < 11; i++){
                        	memo[lines][nums][i] = min(memo[lines][nums][i], memo[lines][nums-1][i-1] + diffleft);
                        	if (memo[lines][nums][i] == 0)
                        		memo[lines][nums][i] = memo[lines][nums-1][i-1] + diffleft;
                       		if (memo[lines][nums-1][i] == 0)
                        		break;
                    	}
                	}
            	}
            
            System.out.println("Case #" + test_case);
            System.out.println(memo[N][M][K]);
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