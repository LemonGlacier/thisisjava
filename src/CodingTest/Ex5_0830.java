package CodingTest;

import java.util.Scanner;

public class Ex5_0830 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		//int nn = Integer.parseInt(scn.nextLine());
		String[] Num = scn.nextLine().split(" ");
		int n = Integer.parseInt(Num[0]);
		int wn = Integer.parseInt(Num[1]);
		String[] mmrz= new String[n];
		String[] word= scn.nextLine().split(" "); 
		int sum = 0;
		int none = 0;
		int sec = 0;
		
		for(int i=0;i<n;i++) {           //word값이 mmrz에 있을 경우도 고려해야됨
			/*for(int d=0; d<n; d++) {
				if(word[i].equals(mmrz[d])) {
					//dup=true;
					word[i]= null; //대입 전에 null을 줘봤자
				}
			}                           //d 변수 for문 끝 //mmrz에 같은 값이 있는지 검증
			if (dup) {
				
				
			} else {*/
			//mmrz[i] = word[i];
			for(int d=0; d<n; d++) {
				if(word[i].equals(mmrz[d])) {
					mmrz[d]= null; 
					for(int k=d;k<n;k++) {  //지운 거 이후로 하나씩 당겨주기 
				      mmrz[k]= mmrz[k+1];   //mmrz하나씩 당겨주기
					 } //int k for문 끝
					sec +=1;
				}
			}        //d 변수 for문 끝 
			mmrz[i] = word[i];
			System.out.println("mmrz 값 검증1" +mmrz[i]);
			sum += mmrz[i].length();
			
		}//int i for문 끝
		int avg = sum/n;                       /////////
		sec = 3*n;
		System.out.println("mmrz 값 검증2" +mmrz[0]);
		System.out.println("mmrz 값 검증2" +mmrz[1]);
		System.out.println("mmrz 값 검증2" +mmrz[2]);
		if (n<wn) {
			for(int f=n;f<=wn;f++) {
				
			   for(int j=0;j<n;j++) {            //지울 거 찾기
				  if(mmrz[j].length() <= avg) {  //길이가 평균보다 짧으면
				   mmrz[j]=null;                 //지우기
				   break;
				}
			}  //int j for문 끝
			   for(int k=0;k<n;k++) {  //지운 거 이후로 하나씩 당겨주기
				 for(int h=0; h<n;h++) {  //지운 거 몇번째인지 찾기
					if(mmrz[h]==null) {
				   none = h;  
				   break;
					} 
				 }  
				 mmrz[none]= mmrz[none+1];   //mmrz하나씩 당겨주기//k-none? 
			 } //int k for문 끝
			 mmrz[n-1]=word[f]; //마지막 항목에 새로운 값 넣기 이걸 wn만큼 해야되는데
			 sec +=3;
			
			}	
			 
			 
				 //브레이크를 어디 넣어야 할까
			} /*else {                    //길이가 평균보다 길면
				for (int l=0;l<n;l++) { 
					if(mmrz[j+1].length() <= avg)  //다음거--짧은거 찾기
				mmrz[j]=null;          //초기화
				 for(int k=0;k<n;k++) { //하나씩 땡겨주기
					 mmrz[k]= mmrz[k+1];
				 }
				}
			}*/
		}
			
		

	}


