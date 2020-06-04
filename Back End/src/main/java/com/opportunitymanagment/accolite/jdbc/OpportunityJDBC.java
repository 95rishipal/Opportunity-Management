package com.opportunitymanagment.accolite.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagment.accolite.models.Audit;
import com.opportunitymanagment.accolite.models.Opportunity;
import com.opportunitymanagment.accolite.models.User;

@Controller("Opportunity_JDBC")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OpportunityJDBC {
	private AuditJDBC auditjdbc;
//	-------------------- Connector ---------------------------------
	private static Connection con;	
	public OpportunityJDBC(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accolitedb","root","12345"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		auditjdbc = new AuditJDBC();
 	}
	
//	------------------------------------------- Get All Opportunities ---------------------------------
	@GetMapping("/oppo/getall")
	@ResponseBody
	public List<Opportunity> retrieveAllOpportunity(@RequestHeader(value = "Email", required=false) String email) {
		auditjdbc.addAudit( new Audit("Get All Opportunity","Get All", "",""),email);
		System.out.println("[JDBC] Opportunity All Request");
		PreparedStatement statement;
		List<Opportunity> list = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT * FROM opportunity;");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					Opportunity opp = new Opportunity(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8), rs.getInt(7));
					list.add(opp);
				}
				return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	---------------------- Get Opportunity by ID ------------------------------------------------
	@GetMapping("/oppo/get/{oppid}")
	@ResponseBody
	public Opportunity retrieveById(@PathVariable("oppid") int id,@RequestHeader(value = "Email", required=false) String email) {
		auditjdbc.addAudit( new Audit("Get Opportunity by id: "+id,"Get By ID", "",""),email);
		System.out.println("[JDBC] Opportunity By ID Request");
		PreparedStatement statement;
		List<Opportunity> list = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT * FROM opportunity where oppid = "+id+";");
				ResultSet rs = statement.executeQuery();
				rs.next();  
				Opportunity opp = new Opportunity(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				return opp;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	----------------- Search Opportuinty by Column and Place Value ----------------------------------------------
	@GetMapping(path = "/oppo/search/{col}/{place}")
	@ResponseBody
	public ResponseEntity  searchBy(@PathVariable("col") String col, @PathVariable("place") String place,@RequestHeader(value = "Email", required=false) String email) {
		auditjdbc.addAudit( new Audit("Search Opportunity Col: "+col+", Value: "+place ,"Search","",""),email);
		System.out.println("[JDBC] Opportunity Search Column and Place Holder");
		System.out.println(col+"---"+place);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		List<Opportunity> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			if(col.equals("description")) {
				statement =con.prepareStatement("SELECT * FROM Opportunity WHERE description LIKE '%"+place+"%';");
			}
			else if(col.equals("location")) {
				statement =con.prepareStatement("SELECT * FROM Opportunity  WHERE location LIKE '%"+place+"%';");
			}else if(col.equals("skills")) {
				statement =con.prepareStatement("SELECT * FROM Opportunity  WHERE skills LIKE '%"+place+"%';");
			}else {
				throw new Exception(); 
			}
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {  
				Opportunity opp = new Opportunity(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
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
	@PostMapping(path = "/oppo/add", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity  addOpportunity(@RequestBody Opportunity ele,  @RequestHeader(value = "Email", required=false) String email) {
		auditjdbc.addAudit( new Audit("Add Opportunity ","Insert", "",ele.toString()),email);
		System.out.println("[JDBC] Opportunity Create Opportunity");
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		int index = -1;
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("SELECT * FROM user WHERE email= '"+email+"';");
			ResultSet rs1 = statement.executeQuery();
			rs1.next();
			int userid = rs1.getInt(1);
			String SQL = "INSERT INTO opportunity (description, end_Date, location, skills, userid, demand, minxp) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ele.getdescription());
			pstmt.setDate(2,java.sql.Date.valueOf(ele.getEndDate()));
			pstmt.setString(3, ele.getLocation());
			pstmt.setString(4, ele.getSkills());
			pstmt.setInt(5, userid);
			pstmt.setInt(6, ele.getDemand() );
			pstmt.setInt(7, ele.getMinxp());
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        index = (int) rs.getLong(1);
                        httpstatus = HttpStatus.OK;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    ResponseEntity responseEntity = new ResponseEntity(index,responseHeaders,httpstatus);
		return responseEntity;
	}
	
//	----------------------------------- Del Opportunity ----------------------------------------------
	@DeleteMapping("/oppo/del/{id}")
	@ResponseBody
	public ResponseEntity deleteUserById(@PathVariable("id") int id,@RequestHeader(value = "Email", required=false) String email) {
		auditjdbc.addAudit( new Audit("Delete Opportunity id: "+id ,"Delete","",""),email);
		System.out.println("[JDBC] Opportunity Delete Opportunity Request");
		System.out.println(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		String SQL = "DELETE FROM opportunity WHERE oppid =?;";
		try {
					PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, id);
					int affectedRows = pstmt.executeUpdate();
					httpstatus = HttpStatus.OK;
            }catch(SQLException ex) {
                    System.out.println(ex.getMessage());
            }
	    ResponseEntity responseEntity = new ResponseEntity("",responseHeaders,httpstatus);
		return responseEntity;
	}

// ------------------------------------------- Edit Opportunity ------------------------------------------------
	@PostMapping("/oppo/edit/{id}")
	@ResponseBody
	public ResponseEntity  editOpportunity(@PathVariable("id") int id, @RequestBody Opportunity ele,@RequestHeader(value = "Email", required=false) String email) {

		System.out.println("[JDBC] Opportunity Edit Opportunity");
		System.out.println(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		int index = -1;
		try {
		PreparedStatement statement1;
		statement1 =con.prepareStatement("SELECT * FROM opportunity where oppid = "+id+";");
		ResultSet rs = statement1.executeQuery();
		rs.next();  
		Opportunity opp = new Opportunity(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
		
		PreparedStatement statement;
		String SQL = "UPDATE opportunity SET  description=?, end_Date=?, location=?, skills=? WHERE oppid=?;";
		
					PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, ele.getdescription());
					pstmt.setDate(2,java.sql.Date.valueOf(ele.getEndDate()));
					pstmt.setString(3, ele.getLocation());
					pstmt.setString(4, ele.getSkills());
					pstmt.setInt(5, id);
					int affectedRows = pstmt.executeUpdate();
					if (affectedRows > 0) {
						index =  affectedRows;
						auditjdbc.addAudit( new Audit("Update Opportunity ","Edit", opp.toString(),ele.toString()),email);
						 httpstatus= HttpStatus.OK;
					}
					
					System.out.println("Update Running");
            }catch(SQLException ex) {
                    System.out.println(ex.getMessage());
            }
	    ResponseEntity responseEntity = new ResponseEntity(index,responseHeaders,httpstatus);
		return responseEntity;
	}
	
}
