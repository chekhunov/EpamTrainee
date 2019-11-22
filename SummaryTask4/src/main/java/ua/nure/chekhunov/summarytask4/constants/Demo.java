package ua.nure.chekhunov.summarytask4.constants;

import ua.nure.chekhunov.summarytask4.db.DBManager;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        DBManager dbManager = new DBManager();
       System.out.println(dbManager.getAllCostOrder(2));
       // System.out.println(dbManager.getAllCount(2));
    }
}
