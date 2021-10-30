package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import entity.Order;
import entity.Product;

public class CartDao extends BaseDao {
//	public static void main(String args[]) {
//		CartDao cartDao = new CartDao();
//		List<Product> products = cartDao.queryProducts();
//		products.stream().forEach(p ->System.out.println(p.getName()));
//	}
	
	
//	//使用static關鍵字可以讓程式被OS載入時就被儲存於記憶體中，直到Application結束為止。
//	private static List<Product> products;
//	private static List<Order> orders;

//	static {
//		orders = new ArrayList<Order>();
//		products = new ArrayList<Product>();
//		products.add(new Product(1,"鉛筆",10,10));
//		products.add(new Product(2,"橡皮",20,10));
//		products.add(new Product(3,"墊板",30,20));
//		products.add(new Product(4,"圓規",40,30));
//		products.add(new Product(5,"彈珠",50,10));
//	}

	
	// 查詢所有商品
	public List<Product> queryProducts() {
		// return products;
		List<Product> products = new ArrayList<>();
		String sql = "SELECT id, name, qty, price FROM products";
		/*
		 * 結果集(ResultSet)從其使用的特點上可以分為四類，這四類的結果集的所具備的特點都是和Statement語句的建立有關，
		 * 因為結果集是通過Statement語句執行後產生的
		 * ，所以可以說，結果集具備何種特點，完全決定於Statement，當然我是說下面要將的四個特點，在Statement建立時包括三種類型。首先是無引數型別的，
		 * 他對應的就是下面要介紹的基本的ResultSet對應的Statement。下面的程式碼中用到的Connection並沒有對其初始化，
		 * 變數conn代表的就是Connection對應的物件。SqlStr代表的是響應的SQL語句。
		 * 
		 * 1.最基本的ResultSet。
		 * 
		 * 之所以說是最基本的ResultSet是因為，這個ResultSet他起到的作用就是完成了查詢結果的儲存功能，而且只能讀去一次，不能夠來回的滾動讀取。
		 * 這種結果集的建立方式如下：
		 * 
		 * Statement st = conn.CreateStatement(); ResultSet rs = st.excuteQuery(sqlStr);
		 * 
		 * 由於這種結果集不支援，滾動的讀去功能所以，如果獲得這樣一個結果集，只能使用它裡面的next()方法，逐個的讀去資料。
		 * 
		 */

		try (Statement stmt = getConnection().createStatement(); 
			 ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int qty = rs.getInt("qty");
				int price = rs.getInt("price");
				Product product = new Product(id, name, qty, price);
				products.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return products;
	}

	// 查詢單一商品
	public Product getProductById(Integer id) {
		// return products.stream().filter(p -> p.getId() == id).findFirst().get();
		Product product = null;
		String sql = "SELECT * FROM products WHERE id = ?";
		/*
		 * 參數化查詢（parameterized query 或 parameterized
		 * statement）是指在設計與資料庫連結並存取資料時，在需要填入數值或資料的地方，使用參數（parameter）來給值，
		 * 這個方法目前已被視為最有效可預防SQL注入攻擊的攻擊手法的防禦方式。
		 * 除了安全因素，相比起拼接字串的SQL語句，參數化的查詢往往有效能優勢。因為參數化的查詢能讓不同的資料通過參數到達資料庫，從而公用同一條SQL語句。
		 * 大多數資料庫會快取解釋SQL語句產生的位元組碼而省下重複解析的開銷。如果採取拼接字串的SQL語句，則會由於運算元據是SQL語句的一部分而非參數的一部分，
		 * 而反覆大量解釋SQL語句產生不必要的開銷。
		 */

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, id);//設定第一個?號的值 型態為int
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int pid = rs.getInt("id");
				String name = rs.getString("name");
				int qty = rs.getInt("qty");
				int price = rs.getInt("price");
				product = new Product(id, name, qty, price);

			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return product;
	}

	// 查詢使用者訂單
	public List<Order> queryOrdersByUserId(Integer userId) {
		//return orders.stream().filter(o -> o.getUserId() == userId).toList();
		List<Order> orders = new ArrayList<Order>();
		String sql = "SELECT id, user_id, product_id, ts FROM orders WHERE user_id = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");				
				int user_id = rs.getInt("user_id");				
				int product_id = rs.getInt("product_id");				
				Date ts = rs.getDate("ts");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
		
		return orders;
		
		
		
	}

	public void addOrder(Integer userId, String[] data) {
		// 自編order id
		//Integer orderId = Math.abs((int) new Date().getTime()); 
//		if (data != null) {
//			for (String d : data) {
//				Order order = new Order(orderId, userId, Integer.parseInt(d));
//				orders.add(order);
//
//			}
//		}
		
		if(data != null) {
			String sql ="INSERT INTO orders(user_id,product_id,)VALUES(?,?)";
			try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
				pstmt.clearBatch();
				for(String d: data) {
					Order order = new Order(0,userId,Integer.parseInt(d));
					pstmt.setInt(1, order.getUserId());
					pstmt.setInt(2, order.getProductId());
					pstmt.addBatch();					
				}
				int[] rows = pstmt.executeBatch();
				System.out.println("rows"+Arrays.toString(rows));
				
			}catch(Exception e) {
				e.printStackTrace(System.out);//預設是System.err
			}
		}

	}
}
