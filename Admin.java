// Made by Chason Bibeau

public class Admin {
	String username;
	String password;
	
	public Admin(String user, String pass) {
		this.username = user;
		this.password = pass;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return ("The username: " + this.getUsername() +
				" || The Password: " + this.getPassword());
	}
}
