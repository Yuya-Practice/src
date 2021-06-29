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

import login.Login;

/**
 * Servlet implementation class DeleteDB
 */
@WebServlet("/DeleteDB")
public class DeleteDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDB() {
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
		
		// SQL関係の初期化
		Connection connection 		 = null;
		PreparedStatement pStatement = null;
		
		try {
			connection = DriverManager.getConnection(Login.DBURL, Login.DBUSER, Login.DBPASS);
			if (employeeno != 0 || !name.equals("") || !department.equals("")) {
				String updateSql = "DELETE FROM employeemst WHERE employeeno = ? AND name = ? AND department = ?";
				pStatement = connection.prepareStatement(updateSql);
				pStatement.setInt(1, employeeno);
				pStatement.setString(2, name);
				pStatement.setString(3, department);
				// 更新された行数を取得 1であれば正常
				if (pStatement.executeUpdate() == 1) {
					json = "{\"checker\":true}";
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
