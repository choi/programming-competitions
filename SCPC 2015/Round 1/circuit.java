import java.util.*;
import java.io.*;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class circuit {
	public static void main(String args[]) throws Exception	{
		/* �Ʒ� �޼ҵ� ȣ���� ������ ǥ���Է�(Ű����) ��� input.txt ���Ϸ� ���� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   ���� ������ PC ���� �׽�Ʈ �� ����, �Է°��� input.txt�� ������ �� �� �ڵ带 ù �κп� ����ϸ�,
		   ǥ���Է� ��� input.txt ���Ϸ� ���� �Է°��� �о� �� �� �ֽ��ϴ�.
		   ����, ���� PC���� �Ʒ� �޼ҵ带 ������� �ʰ� ǥ���Է��� ����Ͽ� �׽�Ʈ�ϼŵ� �����մϴ�.
		   ��, Codeground �ý��ۿ��� "�����ϱ�" �� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ�(//) ó�� �ϼž� �մϴ�. */
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
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
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
			

			// �� �κп��� ������ ����Ͻʽÿ�.
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
	