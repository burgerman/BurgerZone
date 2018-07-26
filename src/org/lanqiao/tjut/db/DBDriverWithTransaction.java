package org.lanqiao.tjut.db;


	
	
	import java.sql.Connection;
	import java.sql.SQLException;

	import org.apache.commons.dbutils.QueryRunner;
	import org.apache.commons.dbutils.ResultSetHandler;

	public class DBDriverWithTransaction {
		// 使用ThreadLocal存储数据库连接对象

		private ThreadLocal<Connection> tlConn = new ThreadLocal<Connection>();

		/**

		 * 打开事务

		 */
		public void beginTransaction() {
			// 从ThreadLocal中获取当前线程的数据库连接对象

			try {
				Connection conn = tlConn.get();
				if (conn == null) {
					// 获取一个数据库连接对象

					conn = DBSource.getDataSource().getConnection();
					// 将该Connection对象存入ThreadLocal，方便后续处理获取数据库连接对象

					tlConn.set(conn);
				}
				// 开启当前conn对象的事务

				// 设置connection对象的事务自动提交的属性为false

				conn.setAutoCommit(false);
			} catch (SQLException e) {
				// 打开数据库事务异常

				System.out.println("DBUtils事务处理-打开数据库事务异常：" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * 提交事务

		 */
		public void commit() {
			// 从ThreadLocal中获取当前线程的数据库连接对象

			try {
				Connection conn = tlConn.get();
				if (conn != null) {
					// 提交当前conn对象的事务

					conn.commit();
				}
			} catch (SQLException e) {
				// 提交数据库事务异常

				System.out.println("DBUtils事务处理-提交数据库事务异常：" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * 回滚事务

		 */
		public void rollback() {
			// 从ThreadLocal中获取当前线程的数据库连接对象

			try {
				Connection conn = tlConn.get();
				if (conn != null) {
					// 回滚当前conn对象的事务

					conn.rollback();
				}
			} catch (SQLException e) {
				// 回滚数据库事务异常

				System.out.println("DBUtils事务处理-回滚数据库事务异常：" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * 关闭数据库连接，资源回收

		 */
		public void close() {
			try {
				// 从ThreadLocal中获取当前线程的数据库连接对象

				Connection conn = tlConn.get();
				if (conn != null) {
					// 回滚当前conn对象的事务

					conn.close();
				}
				// 从ThreadLocal中移除事务对象(如果不显式进行移除，无法进行资源回收)

				tlConn.remove();
			} catch (SQLException e) {
				// 关闭数据库连接异常

				System.out.println("DBUtils事务处理-关闭数据库连接异常：" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * DBUtils数据库查询操作

		 * 

		 * 

		 * 

		 * @param sql

		 *            查询语句

		 * @param rsh

		 *            返回结果集类型句柄，可以选择一下类型作为返回结果集的句柄 ArrayHandler

		 *            将ResultSet转为一个Object[]的ResultSetHandler实现类 ArrayListHandler

		 *            将ResultSet转换为List<Object[]>的ResultSetHandler实现类 BeanHandler

		 *            将ResultSet行转换为一个JavaBean的ResultSetHandler实现类 BeanListHandler

		 *            将ResultSet转换为List<JavaBean>的ResultSetHandler实现类（经常使用※）

		 *            ColumnListHandler

		 *            将ResultSet的一个列转换为List<Object>的ResultSetHandler实现类 KeyedHandler

		 *            将ResultSet转换为Map<Map>的ResultSetHandler实现类 MapHandler

		 *            将ResultSet的首行转换为一个Map的ResultSetHandler实现类 MapListHandler

		 *            将ResultSet转换为List<Map>的ResultSetHandler实现类 ScalarHandler

		 *            将ResultSet的一个列到一个对象。

		 * 

		 * @param params

		 *            查询语句的参数

		 * @return 查询结果集

		 */
		public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
			// 返回值

			T tRe = null;

			try {
				// 从ThreadLocal中获取当前线程的数据库连接对象

				Connection conn = tlConn.get();
				if (conn != null) {
					// 创建dbutils的查询处理类 QueryRunner的实例对象

					// 构造参数为 true 表示要支持 sql语句的占位符处理

					QueryRunner qr = new QueryRunner(true);
					// 使用queryrunner进行查询操作

					tRe = qr.query(conn, sql, rsh, params);
				}
			} catch (SQLException e) {
				// 在事务处理中，如果发生了事务异常，则进行回滚处理

				rollback();
				// DBUtils数据库查询操作异常

				System.out.println("DBUtils数据库查询操作异常:" + e.getMessage());
				e.printStackTrace();
			}
			// 返回结果集

			return tRe;
		}

		/**

		 * DBUtils数据库新增、修改、删除操作

		 * 

		 * @param sql

		 *            数据库新增、修改、删除操作语句

		 * @param params

		 *            数据库新增、修改、删除操作语句的参数

		 * @return 数据库新增、修改、删除操作所影响记录行数

		 */
		public int update(String sql, Object... params) {
			// 返回值

			int iRe = 0;
			try {
				// 从ThreadLocal中获取当前线程的数据库连接对象

				Connection conn = tlConn.get();
				if (conn != null) {
					// 创建dbutils的更新处理类 QueryRunner的实例对象

					QueryRunner qr = new QueryRunner(true);
					// 使用queryrunner进行数据库新增、修改、删除操作

					iRe = qr.update(conn, sql, params);
				}
			} catch (SQLException e) {
				// 在事务处理中，如果发生了事务异常，则进行回滚处理

				rollback();
				// DBUtils数据库新增、修改、删除操作异常

				System.out.println("DBUtils数据库新增、修改、删除操作异常:" + e.getMessage());
				e.printStackTrace();
			}
			// 返回结果集

			return iRe;
		}
	}

