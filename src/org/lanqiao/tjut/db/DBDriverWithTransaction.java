package org.lanqiao.tjut.db;


	
	
	import java.sql.Connection;
	import java.sql.SQLException;

	import org.apache.commons.dbutils.QueryRunner;
	import org.apache.commons.dbutils.ResultSetHandler;

	public class DBDriverWithTransaction {
		// ʹ��ThreadLocal�洢���ݿ����Ӷ���

		private ThreadLocal<Connection> tlConn = new ThreadLocal<Connection>();

		/**

		 * ������

		 */
		public void beginTransaction() {
			// ��ThreadLocal�л�ȡ��ǰ�̵߳����ݿ����Ӷ���

			try {
				Connection conn = tlConn.get();
				if (conn == null) {
					// ��ȡһ�����ݿ����Ӷ���

					conn = DBSource.getDataSource().getConnection();
					// ����Connection�������ThreadLocal��������������ȡ���ݿ����Ӷ���

					tlConn.set(conn);
				}
				// ������ǰconn���������

				// ����connection����������Զ��ύ������Ϊfalse

				conn.setAutoCommit(false);
			} catch (SQLException e) {
				// �����ݿ������쳣

				System.out.println("DBUtils������-�����ݿ������쳣��" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * �ύ����

		 */
		public void commit() {
			// ��ThreadLocal�л�ȡ��ǰ�̵߳����ݿ����Ӷ���

			try {
				Connection conn = tlConn.get();
				if (conn != null) {
					// �ύ��ǰconn���������

					conn.commit();
				}
			} catch (SQLException e) {
				// �ύ���ݿ������쳣

				System.out.println("DBUtils������-�ύ���ݿ������쳣��" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * �ع�����

		 */
		public void rollback() {
			// ��ThreadLocal�л�ȡ��ǰ�̵߳����ݿ����Ӷ���

			try {
				Connection conn = tlConn.get();
				if (conn != null) {
					// �ع���ǰconn���������

					conn.rollback();
				}
			} catch (SQLException e) {
				// �ع����ݿ������쳣

				System.out.println("DBUtils������-�ع����ݿ������쳣��" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * �ر����ݿ����ӣ���Դ����

		 */
		public void close() {
			try {
				// ��ThreadLocal�л�ȡ��ǰ�̵߳����ݿ����Ӷ���

				Connection conn = tlConn.get();
				if (conn != null) {
					// �ع���ǰconn���������

					conn.close();
				}
				// ��ThreadLocal���Ƴ��������(�������ʽ�����Ƴ����޷�������Դ����)

				tlConn.remove();
			} catch (SQLException e) {
				// �ر����ݿ������쳣

				System.out.println("DBUtils������-�ر����ݿ������쳣��" + e.getMessage());
				e.printStackTrace();
			}
		}

		/**

		 * DBUtils���ݿ��ѯ����

		 * 

		 * 

		 * 

		 * @param sql

		 *            ��ѯ���

		 * @param rsh

		 *            ���ؽ�������;��������ѡ��һ��������Ϊ���ؽ�����ľ�� ArrayHandler

		 *            ��ResultSetתΪһ��Object[]��ResultSetHandlerʵ���� ArrayListHandler

		 *            ��ResultSetת��ΪList<Object[]>��ResultSetHandlerʵ���� BeanHandler

		 *            ��ResultSet��ת��Ϊһ��JavaBean��ResultSetHandlerʵ���� BeanListHandler

		 *            ��ResultSetת��ΪList<JavaBean>��ResultSetHandlerʵ���ࣨ����ʹ�á���

		 *            ColumnListHandler

		 *            ��ResultSet��һ����ת��ΪList<Object>��ResultSetHandlerʵ���� KeyedHandler

		 *            ��ResultSetת��ΪMap<Map>��ResultSetHandlerʵ���� MapHandler

		 *            ��ResultSet������ת��Ϊһ��Map��ResultSetHandlerʵ���� MapListHandler

		 *            ��ResultSetת��ΪList<Map>��ResultSetHandlerʵ���� ScalarHandler

		 *            ��ResultSet��һ���е�һ������

		 * 

		 * @param params

		 *            ��ѯ���Ĳ���

		 * @return ��ѯ�����

		 */
		public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
			// ����ֵ

			T tRe = null;

			try {
				// ��ThreadLocal�л�ȡ��ǰ�̵߳����ݿ����Ӷ���

				Connection conn = tlConn.get();
				if (conn != null) {
					// ����dbutils�Ĳ�ѯ������ QueryRunner��ʵ������

					// �������Ϊ true ��ʾҪ֧�� sql����ռλ������

					QueryRunner qr = new QueryRunner(true);
					// ʹ��queryrunner���в�ѯ����

					tRe = qr.query(conn, sql, rsh, params);
				}
			} catch (SQLException e) {
				// ���������У���������������쳣������лع�����

				rollback();
				// DBUtils���ݿ��ѯ�����쳣

				System.out.println("DBUtils���ݿ��ѯ�����쳣:" + e.getMessage());
				e.printStackTrace();
			}
			// ���ؽ����

			return tRe;
		}

		/**

		 * DBUtils���ݿ��������޸ġ�ɾ������

		 * 

		 * @param sql

		 *            ���ݿ��������޸ġ�ɾ���������

		 * @param params

		 *            ���ݿ��������޸ġ�ɾ���������Ĳ���

		 * @return ���ݿ��������޸ġ�ɾ��������Ӱ���¼����

		 */
		public int update(String sql, Object... params) {
			// ����ֵ

			int iRe = 0;
			try {
				// ��ThreadLocal�л�ȡ��ǰ�̵߳����ݿ����Ӷ���

				Connection conn = tlConn.get();
				if (conn != null) {
					// ����dbutils�ĸ��´����� QueryRunner��ʵ������

					QueryRunner qr = new QueryRunner(true);
					// ʹ��queryrunner�������ݿ��������޸ġ�ɾ������

					iRe = qr.update(conn, sql, params);
				}
			} catch (SQLException e) {
				// ���������У���������������쳣������лع�����

				rollback();
				// DBUtils���ݿ��������޸ġ�ɾ�������쳣

				System.out.println("DBUtils���ݿ��������޸ġ�ɾ�������쳣:" + e.getMessage());
				e.printStackTrace();
			}
			// ���ؽ����

			return iRe;
		}
	}

