package ConnectDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import information.Employee;
import login.Login;
/**
 * Servlet implementation class SelectDB
 */
@WebServlet("/SelectDB")
public class SelectDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// ｊｓに出力するため
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"checker\":false}";
		
		// ドライバのロード
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.print(json);
			out.close();
		}
		
		// 値の受け取り emStringはintへ変換
		String emString   = request.getParameter("employeeno");
		String name		  = request.getParameter("name");
		String department = request.getParameter("department");
		int    employeeno = 0;
		
		// emStringはintへ変換
		if (!emString.equals("")) {
			try {
				employeeno = Integer.parseInt(emString);
			} catch (NumberFormatException e) {
				e.getStackTrace();
				// 数値に直せなければ戻す。
				out.print(json);
				out.close();
			}
		}
		
		// Employee型を集めておくリスト
		List<Employee> employeeList = new ArrayList<>();
		
		// SQL関係の初期化
		Connection connection 		 = null;
		PreparedStatement pStatement = null;
		
		try {
			connection = DriverManager.getConnection(Login.DBURL, Login.DBUSER, Login.DBPASS);
			if (employeeno != 0 || !name.equals("") || !department.equals("")) {
				String selectSql = "SELECT employeeno, name, department FROM employeemst WHERE";
				// 複数の条件検索かどうかの判断
				int c = 0;
				if (employeeno != 0) {
					selectSql += " employeeno = ?";
					c++;
				}
				if (!name.equals("")) {
					if (c != 0) {
						selectSql += " AND name LIKE ?";
					} else {
						selectSql += " name LIKE ?";
					}
					c += 3;
				}
				if (!department.equals("")) {
					if (c != 0) {
						selectSql += " AND department LIKE ?";
					} else {
						selectSql += " department LIKE ?";
					}
					c += 5;
				}
				selectSql += " ORDER BY employeeno";
				pStatement = connection.prepareStatement(selectSql);
				switch (c) {
					case(1):
						pStatement.setInt(1, employeeno);
						break;
					case(3):
						pStatement.setString(1, name + "%");
						break;
					case(5):
						pStatement.setString(1, department +"%");
						break;
					case(4):
						pStatement.setInt(1, employeeno);
						pStatement.setString(2, name + "%");
						break;
					case(6):
						pStatement.setInt(1, employeeno);
						pStatement.setString(2, department +"%");
						break;
					case(8):
						pStatement.setString(1, name + "%");
						pStatement.setString(2, department +"%");
						break;
					case(9):
						pStatement.setInt(1, employeeno);
						pStatement.setString(2, name + "%");
						pStatement.setString(3, department +"%");
						break;
				}
				ResultSet rs = pStatement.executeQuery();
				while (rs.next()) {
					int    getEmployeeno = rs.getInt("employeeno");
					String getName		 = rs.getString("name");
					String getDepartment = rs.getString("department");
					// Employee型のオブジェクトにする。
					Employee employee = new Employee(getEmployeeno, getName, getDepartment);
					employeeList.add(employee);
				}
				// ""で再初期化
				json = "";
				for (Employee emp : employeeList) {
					json += mapper.writeValueAsString(emp) + "+";
				}							
			}
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.print(json);
			out.close();
		} finally {
            //接続を切断する
            if (pStatement != null) {
                try {
					pStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					out.print(json);
					out.close();
				}
            }
            if (connection != null) {
                try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					out.print(json);
					out.close();
				}
            }
		}
	}
	
}
