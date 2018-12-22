package com.quickdoodle.trainer.datahandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import com.mysql.jdbc.Connection;

public class DBHandler {	
	
//	public static Connection setConnection(String konString){
//		Connection koneksi = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            koneksi = (Connection) DriverManager.getConnection(konString,"root","");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Connection Failed");
//        } catch (SQLException ex) {
//            System.out.println("Connection Failed");
//        }
//        return koneksi;
//	}
//	
//	
//	public static int executeInputQuery(String SQL, String path) {
//        int status = 0;
//        Connection koneksi = setConnection(path);
//        try {
//            Statement st = koneksi.createStatement();
//            status = st.executeUpdate(SQL);
//        } catch (SQLException ex) {
//        	System.out.println("Failed to execute input"+ex);
//        }
//        finally {
//            try {
//				koneksi.close();
//			} catch (SQLException e) {
//	        	System.out.println("Failed to execute input"+e);
//			}
//        }
//        return status;
//    }
//    
//    public static ArrayList<String> executeOutPutQuery(String SQL, String path, int column) {
//        ResultSet rs = null;
//        Connection koneksi = setConnection(path);
//        ArrayList<String> res = new ArrayList<String>();
//        try {
//            Statement st = koneksi.createStatement();
//            rs = st.executeQuery(SQL);
//            while(rs.next()) {
//            	res.add(rs.getString(column));
//            }
//        } catch (SQLException ex) {
//        	System.out.println("Failed to execute output"+ex);
//        }
//        finally {
//        	try {
//				koneksi.close();
//			} catch (SQLException e) {
//	        	System.out.println("Failed to execute output"+e);
//			}
//        }
//        return res;
//    }
	
	private static String localRoot = "./data/local/";
	private static String dbRoot = "./data/db/";
	private static String devRoot = "./data/dev/";
	
	public static String[] loadObjects(String path) throws IOException {
		List<String> objects = Files.readAllLines(Paths.get(path));
		String[] objectsArr = new String[objects.size()];
		objectsArr = objects.toArray(objectsArr);
		return objectsArr;
	}
	
	public static void downloadFileDev(String fileName) throws IOException {
		File src = new File(dbRoot + fileName);
		File dst = new File(devRoot + fileName);
	    Files.copy(src.toPath(), dst.toPath());
	}
	
	public static void downloadFileEnd(String fileName) throws IOException {
		File src = new File(dbRoot + fileName);
		File dst = new File(localRoot + fileName);
	    Files.copy(src.toPath(), dst.toPath());
	}
	
	public static void uploadFileDev(String fileName) throws IOException {
		File src = new File(devRoot + fileName);
		File dst = new File(dbRoot + fileName);
	    Files.copy(src.toPath(), dst.toPath());
	}
	
	public static void uploadFileEnd(String fileName) throws IOException {
		File src = new File(localRoot + fileName);
		File dst = new File(dbRoot + fileName);
	    Files.copy(src.toPath(), dst.toPath());
	}
	
	
	public static void saveModel(String fileName, String text) {
		try {
			File file = new File("./data/db/"+fileName +".csv");
			file.getParentFile().mkdirs();
			file.createNewFile();
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.print(text);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
