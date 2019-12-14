package com.abhishek.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApp03 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		BufferedReader br = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "Abhishek123");
			st = con.createStatement();
			
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Table Name : ");
			String tname = br.readLine();
			String primary_Key_Cols = "";
			String query = "";
			query = query + "create table "+tname+"(";
			int primary_Key_Count = 0;
			while(true) {
				System.out.print("Column Name : ");
				String col_Name = br.readLine();
				System.out.print("Column Data Type and Size : ");
				String col_Types_Size = br.readLine();
				System.out.print("Is it primary key Column[yes/no] : ");
				String primary_Key_Option = br.readLine();
				if(primary_Key_Option.equalsIgnoreCase("yes")) {
					primary_Key_Count = primary_Key_Count + 1;
					if(primary_Key_Count == 1) {
						primary_Key_Cols = primary_Key_Cols + col_Name;
					} else {
						primary_Key_Cols = primary_Key_Cols + "," + col_Name;
					}
				}
				query = query + col_Name +" "+col_Types_Size;
				
				System.out.print("Onemore Column? [yes/no] : ");
				String option = br.readLine();
				if(option.equalsIgnoreCase("yes")) {
					query = query + ",";
				} else {
					query = query + "," + "primary key(" +primary_Key_Cols+ "))";
					break;
				}
			}
			
			st.executeUpdate(query);
			System.out.println("Table " +tname+ " Created Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}