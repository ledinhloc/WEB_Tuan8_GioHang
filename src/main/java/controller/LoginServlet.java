package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DangNhap;encrypt=true;trustServerCertificate=true";
	    private static final String DB_USER = "sa";  // Tài khoản SQL Server của bạn
	    
	    private static final String DB_PASSWORD = "123"; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		  // Lấy tên đăng nhập và mật khẩu từ form
		
		
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (checkLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("Welcome.jsp");
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
	}
	
	 private boolean checkLogin(String username, String password) {
	        boolean isValidUser = false;

	        // Sử dụng JDBC để kết nối và kiểm tra thông tin
	        try {
	            // Tải driver JDBC cho SQL Server
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	            // Kết nối tới cơ sở dữ liệu
	            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	            // Câu truy vấn SQL kiểm tra tên đăng nhập và mật khẩu
	            String sql = "SELECT * FROM [user] WHERE username = ? AND password = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            // Thực thi câu truy vấn
	            ResultSet rs = stmt.executeQuery();

	            // Nếu có kết quả trả về, nghĩa là thông tin hợp lệ
	            if (rs.next()) {
	                isValidUser = true;
	            }

	            // Đóng kết nối và các tài nguyên
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return isValidUser;
	    }
	 
}
