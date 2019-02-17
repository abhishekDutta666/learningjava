package com.abhishek;

import java.io.*;
import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//The project is not complete
//The program doesn't have an exit strategy or option of a wrong input as of now


public class LibraryDB {
	
	static String librarian_name="library";
	static String librarian_password="root";
	
	
	
	public static void librarianPage()throws IOException{
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("1. Add books /n2. View all books/n3. Issue book /n4. View issued book"
				+ "/n5. Return book/n6. Logout");
		int choice =Integer.parseInt(in.readLine());
		switch(choice) {
		case 1:
			addBooks();
			break;
		case 2:
			viewBooks();
			break;
		case 3:
			issueBooks();
			break;
		case 4:
			viewIssuedBooks();
			break;
		case 5:
			returnBook();
			break;
		}
	}
	
	private static void returnBook() {
		try {
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
//			logger.debug("Connection with driver established");
			PreparedStatement removingbook=con.prepareStatement("update availability='A', issuedTO=NULL from allbooks where id=?");
//			logger.debug("Statement creation completed");
			System.out.print("Book ID: ");
			int book_id=Integer.parseInt(in.readLine());
			removingbook.setInt(1, book_id);
			int k= removingbook.executeUpdate();
			System.out.print("\n"+k+" books returned");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void viewIssuedBooks() {
		
		try {
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
//			logger.debug("Connection with driver established");
			Statement stmt=con.createStatement();
//			logger.debug("Statement creation completed");
			ResultSet rs =stmt.executeQuery("select * from allbooks where availability='NA'");
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"	"+rs.getString(2)+"	  "+rs.getString(4));
				}
			}
			else
				System.out.println("No books issued");
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void issueBooks() {
		
		try {
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
//			logger.debug("Connection with driver established");
			Statement stmt=con.createStatement();
//			logger.debug("Statement creation completed");
			ResultSet rs =stmt.executeQuery("select * from allbooks where book_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void viewBooks() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
//			logger.debug("Connection with driver established");
			Statement stmt=con.createStatement();
//			logger.debug("Statement creation completed");
			ResultSet rs =stmt.executeQuery("select * from allbooks");
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"	"+rs.getString(2)+"	  "+rs.getString(3)+"   "+rs.getString(4));
				}
			}
			else
				System.out.println("No books inserted");
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void addBooks() {
				//books are added here by taking the book id and name. we need to create a trigger to auto
				//increment the value of the id
		try {
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
//			logger.debug("Connection with driver established");
			PreparedStatement addingbook=con.prepareStatement("insert into allbooks values(?,?,?,?)");
			System.out.print("Book ID: ");
			int id=Integer.parseInt(in.readLine());
			System.out.print("\nBook Name: ");
			String book_name=in.readLine();
//			logger.debug("Statement creation completed");
			addingbook.setInt(1,id);
			addingbook.setString(2, book_name);
			addingbook.setString(3, "A");
			addingbook.setString(4, "NULL");
			int k=addingbook.executeUpdate();
			System.out.print("\n"+k+" records inserted");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void librarianLogin()throws IOException{
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Username: ");
		String username_input=in.readLine();
		System.out.println("\nPassword: ");
		String password_input=in.readLine();
		if (username_input.equals(librarian_name)) {//work needs to be done to exit the channel
			if(password_input.equals(librarian_password)) {
				librarianPage();
			}
		}
	}
	

//	public static void inititalLoginQuestion()throws IOException{
//		
//		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
//		boolean exit =false;
//		The for loop is used to let the user keep getting the interactive console even if a wrong input is given
//		while(exit) {
//			System.out.println("1. Admin login\n2.Librarian login\n3. Exit");
//			int choice=Integer.parseInt(in.readLine());
//			if(choice==1) 
//				adminLogin();
//			else if(choice==2)
//				librarianLogin();
//			else if(choice==3)
//				exit=true;
//			else {
//				System.out.println("wrong Input");
//			}
//		}
//	}
	
	public static void main(String[] args)throws IOException {
		
//		inititalLoginQuestion();
		librarianLogin();
		System.out.println("Goodbye");
	}

}

