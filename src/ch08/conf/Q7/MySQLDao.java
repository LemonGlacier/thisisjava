package ch08.conf.Q7;

public class MySQLDao implements DataAccessObject {
	@Override
    public void select() {
		System.out.println("Select MySQL DB");
	}
	public void insert() {
		System.out.println("Insert MySQL DB");
	}
	public void update() {
		System.out.println("Update MySQL DB");
	}
	public void delete() {
		System.out.println("Delete MySQL DB");
	}

}
