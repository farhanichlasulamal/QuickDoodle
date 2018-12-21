package com.quickdoodle.trainer.datahandler;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;

class Execute {	
	
	public static Connection setConnection(String konString){
		Connection koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(konString,"root","");
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection Failed");
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
        }
        return koneksi;
	}
	
	
	public static int executeInputQuery(String SQL, String path) {
        int status = 0;
        Connection koneksi = setConnection(path);
        try {
            Statement st = koneksi.createStatement();
            status = st.executeUpdate(SQL);
        } catch (SQLException ex) {
        	System.out.println("Failed to execute input"+ex);
        }
        finally {
            try {
				koneksi.close();
			} catch (SQLException e) {
	        	System.out.println("Failed to execute input"+e);
			}
        }
        return status;
    }
    
    public static ArrayList<String> executeOutPutQuery(String SQL, String path, int column) {
        ResultSet rs = null;
        Connection koneksi = setConnection(path);
        ArrayList<String> res = new ArrayList<String>();
        try {
            Statement st = koneksi.createStatement();
            rs = st.executeQuery(SQL);
            while(rs.next()) {
            	res.add(rs.getString(column));
            }
        } catch (SQLException ex) {
        	System.out.println("Failed to execute output"+ex);
        }
        finally {
        	try {
				koneksi.close();
			} catch (SQLException e) {
	        	System.out.println("Failed to execute output"+e);
			}
        }
        return res;
    }
}
