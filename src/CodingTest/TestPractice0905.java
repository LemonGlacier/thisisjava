package CodingTest;

		import java.util.Scanner;
		import java.io.FileInputStream;
		class TestPractice0905 {
			static int AnswerN;
			static int N = 10;

			public static void main(String args[]) throws Exception {
				//System.setIn(new FileInputStream("C:\sample_input.txt"));
				Scanner sc = new Scanner(System.in);

				int T = Integer.parseInt(sc.nextLine());          //int T = sc.nextInt(); sc.nextLine()
				int xLine = 0;
				int xColumn = 0;

				for(int test_case = 1; test_case <= T; test_case++) {
					N = Integer.parseInt(sc.nextLine());
					AnswerN=0;
					String[][] field = new String[N][N];
					for(int i =0;i<N;i++) {
						String[] line = sc.nextLine().split(" ");
						for(int j =0;j<N;j++) {
							field[i][j] = line[j];
							if(field[i][j].equals("X")) {
								xLine=i;
								xColumn=j;
							} 
						}
					}
					String[] xLineCase = new String[N];
					for(int k=0; k<N;k++) {
						xLineCase[k]=field[xLine][k];
					}
					String[] xColumnCase = new String[N];
					for(int k=0; k<N;k++) {
						xColumnCase[k]=field[k][xColumn];
					}
					for(int h=1; h<xColumn;h++) {
						if(xLineCase[xColumn-h].equals("Y")) {
							break;
						} else if(xLineCase[xColumn-h].equals("H")) {
							if(xLineCase[xColumn-h-1].equals("H")) {
								AnswerN++;
								h++;
							}
						}
					}
					for(int h=1; h<N-xColumn-1;h++) {
						if(xLineCase[xColumn+h].equals("Y")) {
							break;
						} else if(xLineCase[xColumn+h].equals("H")) {
							if(xLineCase[xColumn+h+1].equals("H")) {
								AnswerN++;
								h++;
							}
						}
					}
					for(int h=1; h<xLine;h++) {
						if(xColumnCase[xLine-h].equals("Y")) {
							break;
						} else if(xColumnCase[xLine-h].equals("H")) {
							if(xColumnCase[xLine-h-1].equals("H")) {
								AnswerN++;
								h++;
							}
						}
					}
					for(int h=1; h<N-xLine-1;h++) {
						if(xColumnCase[xLine+h].equals("Y")) {
							break;
						} else if(xColumnCase[xLine+h].equals("H")) {
							if(xColumnCase[xLine+h+1].equals("H")) {
								AnswerN++;
								h++;
							}
						}
					}
				
					System.out.println("#"+test_case+" "+AnswerN);
				}
			}
		}

