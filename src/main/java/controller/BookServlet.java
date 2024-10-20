package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.LineItem;
import beans.Product;
import beans.Product;

/**
 * Servlet implementation class bookServlet
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		List<Product> list = new ArrayList<>();
		list.add(new Product("1","Harry Potter", 450000));
		list.add(new Product("2","Nhà giả kim", 95000));
		list.add(new Product("3","Không Phải Sói Nhưng Cũng Đừng Là Cừu", 130000));
		list.add(new Product("4","Nghìn lẻ một đêm", 230000));

        request.setAttribute("productList", list);
       
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ListProduct.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc= getServletContext();
		String action = request.getParameter("action");
		
		if(action==null)
		{
			action = "addToCart";
		}
		String url = "/BookServlet";
		
		if(action.equals("shop")) 
		{
			System.out.print(action);
			url = "/BookServlet";
			
		}
		else if (action.equals("addToCart")) 
		{
			String id = request.getParameter("id");

			String quantity = request.getParameter("productQuantity");
			
			HttpSession session = request.getSession();
	        Cart cart = (Cart) session.getAttribute("cart");
	        
	        if (cart == null) {
	            cart = new Cart();
	            
	        }
	        
	        int sl;
	        try 
	        {
	        	sl = Integer.parseInt(quantity);
				sl = 1;
			} 
	        catch (NumberFormatException e) 
	        {
	        	sl = 1;
			}
	        
	        Product bookBean = new Product();
	        bookBean.getProductInfo(id);
	        
	        LineItem lineItem = new LineItem(bookBean,sl);
	        if(sl>0)
	        {
	        	cart.addItem(lineItem);
	        }
	        else if(sl==0)
	        {
	        	cart.removeItem(lineItem);
	        }
	        session.setAttribute("cart", cart);
	        url = "/GioHang.jsp";
		}
		else if (action.equals("update"))
		{
			String id = request.getParameter("productCode");
			System.out.print(id);
			String quantity = request.getParameter("productQuantity");
			int sl = Integer.parseInt(quantity);
			HttpSession session = request.getSession();
	        Cart cart = (Cart) session.getAttribute("cart");
	        
	        Product bookBean = new Product();
	        bookBean.getProductInfo(id);
	        
	        LineItem lineItem = new LineItem(bookBean,sl);
			cart.updateItem(lineItem);
			session.setAttribute("cart", cart);
			
			url = "/GioHang.jsp";
		}

		sc.getRequestDispatcher(url).forward(request, response);
	}

}
