package main;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class JDBCTools {
	/**
	 * 通用的查询方法
	 * @param url:数据库url
	 * @param sql：查询语句
	 * @param args：查询对象
	 * @return：Arraylist 每一条查询结果是list中的一个元素，每条查询结果中的单个原数据用"\t"隔开
	 */
	public static ArrayList<String> get(String url,String sql,Connection connection,Object... args) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> result = new ArrayList<>();
		try {
			// 1. 数据库连接
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			// 2. 得到查询结果
			resultSet = preparedStatement.executeQuery();

			// 得到 ResultSetMetaData 对象(用于获取查询结果的列和行信息)
			ResultSetMetaData rsmd = resultSet.getMetaData();

			// 4. 处理结果集. 利用 ResultSetMetaData 填充 3 对应的 Map 对象
			while (resultSet.next()) {
				String values = "";
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					Object columnValue = resultSet.getObject(i + 1);
					values += columnValue+"\t";
				}
				result.add(values);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			JDBCTools.releaseDB(resultSet, preparedStatement, connection);
		}
		return result;
	}

	/**
	 * 执行 SQL 语句, 使用 PreparedStatement,返回插入新数据的主键
	 * @param sql: insert, update 或 delete。 而不包含 select
	 * @param args: 填写 SQL 占位符的可变参数
	 */
	public static long update(String url,int key,String sql,Connection connection,Object ... args){
		PreparedStatement preparedStatement = null;
		long mainKey = 0;
		try {
			preparedStatement = connection.prepareStatement(sql,key);

			for(int i = 0; i < args.length; i++){
				if(args[i] == "null"){
					args[i]="---" ;
				}
				preparedStatement.setObject(i + 1, args[i]);
			}

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				System.out.println(rs.getObject(1));
			}
			mainKey = (long) rs.getObject(1);
			System.out.println(mainKey);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally{
			JDBCTools.releaseDB(null, preparedStatement, connection);
		}
		return mainKey;
	}

	/**
	 * 执行 SQL 语句, 使用 PreparedStatement
	 * @param sql: insert, update 或 delete。 而不包含 select
	 * @param args: 填写 SQL 占位符的可变参数
	 */
	public static void update(String url,String sql, Connection connection,Object ... args){
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				if(args[i] == "null"){
					args[i]="---" ;
				}
				preparedStatement.setObject(i + 1, args[i]);

			}
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();



		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.releaseDB(null, preparedStatement, connection);
		}
	}

	/**
	 * 执行 SQL 的方法
	 *
	 * @param sql: insert, update 或 delete。 而不包含 select
	 */
	public static void update(String url,String sql,Connection connection) {
		Statement statement = null;

		try {
			// 1. 获取数据库连接

			// 2. 调用 Connection 对象的 createStatement() 方法获取 Statement 对象
			statement = connection.createStatement();

			// 4. 发送 SQL 语句: 调用 Statement 对象的 executeUpdate(sql) 方法
			statement.executeUpdate(sql);

		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			// 5. 关闭数据库资源: 由里向外关闭.
			releaseDB(null, statement, connection);
		}
	}

	/**
	 * 释放数据库资源的方法
	 *
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void releaseDB(ResultSet resultSet, Statement statement,
			Connection connection) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
/**导入每张表只用一个connection，因此这里不释放connection的资源**/
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

	/**
	 * 获取数据库连接的方法
	 */
	public static Connection getConnection(String url) throws IOException,
			ClassNotFoundException, SQLException {
		// 0. 读取 jdbc.properties
		/**
		 * 1). 属性文件对应 Java 中的 Properties 类 2). 可以使用类加载器加载 bin 目录(类路径下)的文件
		 */

		Properties properties = new Properties();
		InputStream inStream = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(inStream);
		// 1. 准备获取连接的 4 个字符串: user, password, jdbcUrl, driverClass
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty(url);
		String driverClass = properties.getProperty("driver");
		// 2. 加载驱动: Class.forName(driverClass)
		Class.forName(driverClass);

		// 3. 调用
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(jdbcUrl, user,
				password);
		return connection;
	}

}