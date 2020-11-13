<%--
  Created by IntelliJ IDEA.
  User: Tzhmos
  Date: 10/27/2020
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.eshop.product.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="db" class="com.eshop.database.Database" scope="page"/>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>






    <%   ArrayList<Product> list = db.allProducts();
        if(list.size()!=0){
           
            
                
    %>
    <h2>Last added item:</h2>
    <div class="box2">

        <img class="product-icon" src="images/open.png">
        <div class="barcode">
            <img  src="images/barcode.png">
            <h5><%=list.get(list.size()-1).getBarcode()%></h5>
        </div>

        <ul>
            <h3><i><u>Product Characteristics:</u></i></h3>

            <li><h6><%=list.get(list.size()-1).getName()%></h6></li>
            <li><h5><%=list.get(list.size()-1).getColor()%></h5></li>
        </ul>
        <div class="disc"><%=list.get(list.size()-1).getDescription()%>
        </div>

    </div>
    <%
         }
    %>




    <%
        if(list.size()==1){
    %>
    <h2>There is <%=list.size()%> product in the list</h2>
    <%
        }else{
    %>
    <h2>There are <%=list.size()%> products in the list</h2>
    <%
        }
    %>




    <form action="controller" method="post">
        <input class="go-back" type="submit" name="submit" value="Go Back">

        <%
            if(list.size()!=0){
        %>

        <div class="last-view">

                <input class="table-cell view-all" type="submit" name="submit" value="View All">
        </div>

        <%
            }
        %>
    </form>


    <%
        if(request.getAttribute("status").equals("1")){
            for(int i = 0; i <list.size(); i++){ 


    %>



        <div class="box2">

            <img class="product-icon" src="images/open.png">
            <div class="barcode">
                <img  src="images/barcode.png">
                <h5><%=list.get(i).getBarcode()%></h5>
            </div>

            <ul>
                <h3><i><u>Product Characteristics:</u></i></h3>

                <li><h6><%=list.get(i).getName()%></h6></li>
                <li><h5><%=list.get(i).getColor()%></h5></li>
            </ul>
            <div class="disc"><%=list.get(i).getDescription()%>
            </div>

        </div>

    <%
            }
    %>
    <form action="controller" method="post">
        <input class="hide" type="submit" name="submit" value="Hide">
     <%
        }
    %>


</body>
</html>
