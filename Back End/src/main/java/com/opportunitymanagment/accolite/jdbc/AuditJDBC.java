package com.opportunitymanagment.accolite.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagment.accolite.models.Audit;
import com.opportunitymanagment.accolite.models.Opportunity;

@Controller("Audit_JDBC")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuditJDBC {
		
//	-------------------- Connector ---------------------------------
	private static Connection con;	
	public AuditJDBC(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accolitedb","root","12345"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
//	------------------------------------------- Get All Opportunities ---------------------------------
	@GetMapping("/audit/getall")
	@ResponseBody
	public List<Audit> retrieveAllOpportunity() {
		System.out.println("[JDBC] Audit All");
		PreparedStatement statement;
		List<Audit> list = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT * FROM audit;");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					System.out.println(rs.getInt(1)+
							","+rs.getString(2)+
							","+rs.getString(3)+
							","+rs.getString(4)+
							","+ rs.getString(5)+
							","+rs.getString(6)+
							","+rs.getString(7)+
							","+rs.getString(8));
					Audit opp = new Audit(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
					list.add(opp);
				}
				Collections.reverse(list);
				return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	----------------- Search Opportuinty by Column and Place Value ----------------------------------------------
	@GetMapping(path = "/audit/search/{col}/{place}")
	@ResponseBody
	public ResponseEntity  searchBy(@PathVariable("col") String col, @PathVariable("place") String place,@RequestHeader(value = "Email", required=false) String email) {
		System.out.println("[JDBC] Audit Search Column and Place Holder");
		System.out.println(col+"---"+place);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		List<Audit> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			if(col.equals("date")) {
				statement =con.prepareStatement("SELECT * FROM audit WHERE date LIKE '%"+place+"%';");
			}
			else if(col.equals("operation")) {
				statement =con.prepareStatement("SELECT * FROM audit  WHERE operation LIKE '%"+place+"%';");
			}else if(col.equals("userid")) {
				statement =con.prepareStatement("SELECT * FROM audit  WHERE user_id LIKE '%"+place+"%';");
			}else if(col.equals("username")) {
				statement =con.prepareStatement("SELECT * FROM audit  WHERE user_name LIKE '%"+place+"%';");
			}else if(col.equals("type")) {
			statement =con.prepareStatement("SELECT * FROM audit  WHERE type LIKE '%"+place+"%';");
		}else {
				throw new Exception(); 
			}
			ResultSet rs = statement.executeQuery();
			while(rs.next()) { 
				
				Audit opp = new Audit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getInt(7), rs.getString(8));
				list.add(opp);
			}
			httpstatus= HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    ResponseEntity responseEntity = new ResponseEntity(list,responseHeaders,httpstatus);
		return responseEntity;
	}
	
//	---------------------------------------------  Create Opportunity --------------------------------------
	public boolean addAudit(Audit ele, String email) {
		System.out.println("[JDBC] Audit Opportunity");
		int index = -1;
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("SELECT * FROM user WHERE email= '"+email+"';");
			ResultSet rs1 = statement.executeQuery();
			rs1.next();
			int userid = rs1.getInt(1);
			String username = rs1.getString(2);
			String SQL = "INSERT INTO audit (date, new_values, old_values, operation,type, user_id, user_name) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ele.setDate(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
			pstmt.setString(1,ele.getDate());
			pstmt.setString(2, ele.getNewValues());
			pstmt.setString(3, ele.getOldValues());
			pstmt.setString(4, ele.getOperation());
			pstmt.setString(5, ele.getType());
			pstmt.setInt(6, userid);
			pstmt.setString(7, username );
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        index = (int) rs.getLong(1);
                        return true;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		} 
	  return false;
	}
	
	
	
}
