import java.util.*;
import java.io.*;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class circuit {
	public static void main(String args[]) throws Exception	{
		/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
		   만약 본인의 PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
		   표준입력 대신 input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
		   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
		   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
        
		Reader sc = new Reader("input.txt");

		int T, N;
		double guess, margin;
		int test_case;
		double[][] points;
		double i0,i1,i2,i3;
		double x0,x1,y0,y1;
		boolean flag, flag2;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
			N = sc.nextInt();
			points = new double[N][4];
			for (int i = 0; i < N; i++) {
				i0 = sc.nextInt();
				i1 = sc.nextInt();
				i2 = sc.nextInt();
				i3 = sc.nextInt();
				if (i0 > i2 || i1 > i3) {
					points[i][0] = i2;
					points[i][1] = i3;
					points[i][2] = i0;
					points[i][3] = i1;
				}
				else {
					points[i][0] = i0;
					points[i][1] = i1;
					points[i][2] = i2;
					points[i][3] = i3;
				}
			}
			
			guess = 25000000.;
			margin = 16777216;
			
			while (margin > .2) {
				flag = true;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						flag2 = (points[j][0]-guess < points[i][0]+guess && points[j][0]+guess > points[i][0]-guess &&
								points[j][1]-guess < points[i][1]+guess && points[j][1]+guess> points[i][1]-guess);
						flag2 |= (points[j][2]-guess < points[i][0]+guess && points[j][2]+guess > points[i][0]-guess &&
								points[j][3]-guess < points[i][1]+guess && points[j][3]+guess> points[i][1]-guess);
						flag2 |= (points[j][0]-guess < points[i][2]+guess && points[j][0]+guess > points[i][2]-guess &&
								points[j][1]-guess < points[i][3]+guess && points[j][1]+guess> points[i][3]-guess);
						flag2 |= (points[j][2]-guess < points[i][2]+guess && points[j][2]+guess > points[i][2]-guess &&
								points[j][3]-guess < points[i][3]+guess && points[j][3]+guess> points[i][3]-guess);
						flag &= flag2;
					}
				}
				
				if (flag)
					guess -= margin;
				else
					guess += margin;
				margin /= 2;
			}
			

			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			if (((int) (2*guess))/2. == ((int) (2*guess))/2)
				System.out.println(((int) (2*guess))/2);
			else
				System.out.println(((int) (2*guess))/2.);
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
	