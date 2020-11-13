package com.eshop.database;

import com.eshop.product.Product;
import com.eshop.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Database {
    /*public static boolean checkIfBarcodeExists(String bar) {


    }*/

    public static void insertProduct(Product pr) {
        Transaction transaction = null;

        try  {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(pr);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Product getProduct(String barcode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Product pr where pr.barcode = :barcode");
        query.setParameter("barcode", barcode);
        Product pr = (Product) query.uniqueResult();

        return pr;
    }

    public static ArrayList<Product> allProducts() {
        ArrayList<Product> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Product");
        list = (ArrayList<Product>) query.list();
        
        
        
      return list;

    }
}
