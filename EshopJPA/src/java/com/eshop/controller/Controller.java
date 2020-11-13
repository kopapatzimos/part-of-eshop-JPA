package com.eshop.controller;

import com.eshop.database.Database;
import com.eshop.product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Product p = new Product();
         p.setBarcode(request.getParameter("barcode"));
        p.setName(request.getParameter("name"));
        p.setColor(request.getParameter("color"));
        p.setDescription(request.getParameter("description"));
        if(!(p.getBarcode().isEmpty() && p.getName().isEmpty() &&p.getDescription().isEmpty() &&p.getColor().isEmpty())) {

            if (!Database.checkIfBarcodeExists(p.getBarcode())) {
                Database.insertProduct(p);
                response.sendRedirect("welcome.jsp");
            } else {
                request.setAttribute("msg", "Barcode already exists");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("msg", "All information should be completed");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }*/

        Database db = new Database();

        String barcode = request.getParameter("barcode");
        String name = request.getParameter("name");
        String color = request.getParameter("color");
        String description = request.getParameter("description");

        Product p = db.getProduct(barcode);

        if(p == null){
            p = new Product();
        }


        if(request.getParameter("submit").equals("Insert")) {
            if (!(barcode.isEmpty() || name.isEmpty() || color.isEmpty() || description.isEmpty())) {
                if (barcode.length() == 10) {
                    if (p.getBarcode()==null) {
                        p.setBarcode(barcode);
                        p.setName(name);
                        p.setColor(color);
                        p.setDescription(description);
                        Database.insertProduct(p);
                        response.sendRedirect("index.jsp");

                    } else {
                        request.setAttribute("msg", "Barcode already exists");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }else {
                    request.setAttribute("msg", "Barcode must be 10 digits");
                    request.getRequestDispatcher("index.jsp").forward(request, response);

                }
            } else {
                request.setAttribute("msg", "All information should be completed");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        else if(request.getParameter("submit").equals("View")) {
            request.setAttribute("status", "0");
            request.getRequestDispatcher("welcome.jsp").forward(request, response);

        }else if(request.getParameter("submit").equals("View All")){
            request.setAttribute("status", "1");
            request.getRequestDispatcher("welcome.jsp").forward(request, response);

        }
        else if(request.getParameter("submit").equals("Hide")){
            request.setAttribute("status", "0");
            request.getRequestDispatcher("welcome.jsp").forward(request, response);

        }
        else{
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
