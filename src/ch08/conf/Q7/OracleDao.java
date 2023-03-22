package ch08.conf.Q7;

public class OracleDao implements DataAccessObject {
	@Override
    public void select() {
		System.out.println("Select Oracle DB");
	}
	public void insert() {
		System.out.println("Insert Oracle DB");
	}
	public void update() {
		System.out.println("Update Oracle DB");
	}
	public void delete() {
		System.out.println("Delete Oracle DB");
	}

}
