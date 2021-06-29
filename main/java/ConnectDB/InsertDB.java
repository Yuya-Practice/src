package ConnectDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import information.Employee;
import login.Login;
/**
 * Servlet implementation class InsertDB
 */
@WebServlet("/InsertDB")
public class InsertDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDB() {
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
		
		// SQLが更新されたかの確認用
		int checker = -1;
     	
		// ｊｓに出力するため
		PrintWriter out = response.getWriter();
		
		// ドライバのロード
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.print(checker);
		}
		
		// 値の受け取り emStringはintへ変換
		String emString   = request.getParameter("employeeno");
		String name		  = request.getParameter("name");
		String department = request.getParameter("department");
		int    employeeno = 0;
		
		// emStringはintへ変換
		try {
			employeeno = Integer.parseInt(emString);
		} catch (NumberFormatException e) {
			e.getStackTrace();
			// 数値に直せなければ戻す。
			out.print(checker);
			out.close();
		}
		
		// Employee型のオブジェクトにする。
		Employee employee = new Employee(employeeno, name, department);
		
		// SQL関係の初期化
		Connection connection 		 = null;
		PreparedStatement pStatement = null;
		
		try {
			connection = DriverManager.getConnection(Login.DBURL, Login.DBUSER, Login.DBPASS);
			String insertSql = "INSERT INTO employeemst(employeeno, name, department) VALUES(?, ?, ?)";
			pStatement = connection.prepareStatement(insertSql);
			pStatement.setInt(1, employee.getEmployeeno());
			pStatement.setString(2, employee.getName());
			pStatement.setString(3, employee.getDepartment());
			// 更新された行数を取得 1であれば正常
			checker = pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            //接続を切断する
            if (pStatement != null) {
                try {
					pStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            if (connection != null) {
                try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            // 上手くいってればchecker=1 更新がなければ0, 途中のエラーは-1
            out.print(checker);
            out.close();
		}
	}

}
