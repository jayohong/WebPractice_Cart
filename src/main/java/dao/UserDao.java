package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao extends BaseDao{
	//private static List<User> users;
	
//	static {
//		users = new ArrayList<User>();
//		users.add(new User(1,"admin","1111"));
//		users.add(new User(1,"John","1111"));
//		users.add(new User(1,"Mary","1111"));
//	}
//	
//	public static void main(String args[]) {
//		UserDao userDao = new UserDao();
//		userDao.queryUsers().stream().forEach(i -> System.out.println(i.getName()));
//	}
	
	
	private  List<User> queryUsers(){
		List<User> users = new ArrayList<>();
		String sql="SELECT id, name ,password FROM users";
		try(Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				User user = new User(id,name,password);
				users.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
		return users;
	}	

	
	public User loginCheck(String name, String password) {
//		return users.stream().filter(u -> u.getName().equals(name) && u.getPassword().equals(password))
//				.findFirst().get();
		return queryUsers().stream()
				//這邊不能用==而要用equals 因為String的==看的是儲存字串物件的位址是否相同 但就算內容相同也不代表位址相同
				.filter(u -> u.getName().equals(name) && u.getPassword().equals(password))
				.findFirst()
				.get();
				
	}

}
