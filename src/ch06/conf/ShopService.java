package ch06.conf;

public class ShopService {
	private static ShopService Singleton = new ShopService();
	
	private ShopService() {}
	
	public static ShopService getInstance() {
		return Singleton;
	}

}
