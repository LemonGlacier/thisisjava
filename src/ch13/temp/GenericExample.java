package ch13.temp;

public class GenericExample {

	public static void main(String[] args) {
		//Home.turnOnLight();      //인스턴스 메소드(객체 필요)
		Home home = new Home();
		home.turnOnLight();
		
		HomeAgency ha = new HomeAgency();
		//Home hm= ha.rent();     //타입 안 맞음
		Home hm=null;
		//hm.turnOnLight();

	}

}
