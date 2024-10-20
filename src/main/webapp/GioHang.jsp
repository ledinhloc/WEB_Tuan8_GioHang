<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Giỏ hàng</h1>
    <table>
      <thead>
        <tr>
          <th>Số lượng</th>
          <th>Mô tả</th>
          <th>Giá</th>
          <th>Tổng tiền</th>
          <th>&nbsp;</th>
        </tr>
      </thead>
      <tboday>
      	<c:if test="${not empty cart.items}">
      		<c:forEach var="item" items="${cart.items}">
                    <tr>
                        
                        <td><c:out value='${item.product.description}'></c:out></td>
                        <td><c:out value='${item.product.price}'></c:out></td>
                        <td><c:out value='${cart.tinhTongTien(item) }'></c:out></td>
                        <td>
                            <form action="?action=update" method="post">
                            	<input type="hidden"  name="productCode" value="${item.product.code}">
                                <input type="text" name="productQuantity" value="${item.quantity}">
                                <input type="submit" value="Update">
                            </form>
                        </td>
                        <td>
                            <form action="?id=${item.product.code}&&quantity=0" method="post">
                                <input type="hidden" name="product" value="${item.product.description}">
                                <input type="submit" value="Remove Item">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
      	</c:if>
      		
      </tboday>
      <tfoot></tfoot>
    </table>
    <p><b>Để thay đổi số lượng</b></p>
    <p>nhập số lượng vào nhấn Update</p>
   	<button><a href="?action=shop"> Continue shopping</a></button>
    <style>
    	a{
    		text-decoration:none
    	}
    </style>
</body>
</html>