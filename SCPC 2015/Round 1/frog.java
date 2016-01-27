import java.util.*;
import java.io.*;


class frog {
	public static void main(String args[]) throws Exception	{
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        Reader sc = new Reader("input.txt");
		//Reader sc = new Reader(System.in);

		int T, N, K, current;
		int test_case, count;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
        	N = sc.nextInt(); // N equals number of stones
            int[] stones = new int[N];
            for(int i = 0; i < N; i++)
                stones[i] = sc.nextInt();
            
            K = sc.nextInt(); // K equals minimum jump length
            
            current = 0;
            count = 0;
            
            // jump as far as possible
            // once farthest jump is confirmed, take the next step
            // jump as far as possible again
            // repeat
            
            // loop through stones
            // 	 if jump is valid: keep going
            //   if jump is not valid: jump to previous
            // 
            // loop through rest of stones
            //   if jump is valid: keep going
            //   if jump is not valid: jump to previous
            // 
            
            
            for (int j = 0; j < N; j++) {
                if (stones[j] > current + K) {  // if next stone exceeds jump, check previous to see if valid
                	if (j == 0) { // if frog can't jump from stone 1
            			count = -1;
                		break;
                	}
					if (current == stones[j-1]) { // no more jumps possible
						count = -1;
            			break;
					}
                    // go to previous jump
                    count++;
                    
                    if (stones[j-1] <= current + K) { // if previous jump is valid
                    	current = stones[j-1];    // let current equal previous stone
                    	//System.out.println(""+current);
                    	j -= 1;
                    	continue;
                    }
                    
                    
                   /* if (stones[j] < current + K) {
                        count = -1;
                        break;
                    }*/
                }
            }
            if (stones[N-1] > current + K)
            	count = -1;
            else
            	count++;
            if (count != -1) {
            	System.out.println("Case #" + test_case);
        		System.out.println("" + count);
            }
            else {
            	System.out.println("Case #" + test_case);
        		System.out.println("" + count);
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