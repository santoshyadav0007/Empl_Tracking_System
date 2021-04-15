package help;


import java.util.*;  
import java.sql.*;

import java.math.*;

public class Well {
	
	
	
	public static Connection getConnection(){  
        Connection con=null;  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empData","noy","passwd");  
        }catch(Exception e){System.out.println(e);}  
        return con;
    } 
	
	
	public static void test() {
		System.out.println("ya enterd");
	}

	
	
	public static Emp getEmployeeByEmail(String email){  
        Emp e=new Emp();  
        
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from empProfile where emp_uname=?");  
            ps.setString(1,email);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){
            	
            	e.setMobile(rs.getString("emp_mobile"));
                e.setId(rs.getInt("emp_id"));  
                e.setEmail(rs.getString("emp_uname"));
                e.setPass(rs.getString("emp_pass"));
                e.setName(rs.getString("emp_name"));  
                e.setDep(rs.getString("emp_dep"));  
                e.setAddr(rs.getString("emp_addr"));  
            }  
            con.close();  
        }catch(Exception ex){System.out.println("error is here "+ex);}  
          
        return e;  
    }
	
	public static Emp getEmployeeById(int id){  
        Emp e=new Emp();  
        
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from empProfile where emp_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){
            	
            	e.setMobile(rs.getString("emp_mobile"));
                e.setId(rs.getInt("emp_id"));  
                e.setEmail(rs.getString("emp_uname"));
                e.setPass(rs.getString("emp_pass"));
                e.setName(rs.getString("emp_name"));  
                e.setDep(rs.getString("emp_dep"));  
                e.setAddr(rs.getString("emp_addr"));  
            }  
            con.close();  
        }catch(Exception ex){System.out.println("error is here "+ex);}  
          
        return e;  
    }
	
	public static work getWorkByEmail(String mail){  
        work e=new work();  
        Emp h=getEmployeeByEmail(mail);int id=h.getId();
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from work where emp_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){
            	            	
                e.setId(rs.getInt("emp_id")); e.setName(rs.getString("emp_name"));  
                
                e.setPname(rs.getString("project_name"));
                 
                e.setRole(rs.getString("role_name"));  
                e.setPerf(rs.getString("perf"));  
            }  
            con.close();  
        }catch(Exception ex){System.out.println("error is here "+ex);}  
          
        return e;  
    }
	
	public static work getWorkById(int id){  
        work e=new work();  
        
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from work where emp_id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){
            	            	
                e.setId(rs.getInt("emp_id")); e.setName(rs.getString("emp_name"));  
                
                e.setPname(rs.getString("project_name"));
                 
                e.setRole(rs.getString("role_name"));  
                e.setPerf(rs.getString("perf"));  
            }  
            con.close();  
        }catch(Exception ex){System.out.println("error is here "+ex);}  
          
        return e;  
    }
	
	public static List<Emp> getAllEmployees(){  
        List<Emp> list=new ArrayList<Emp>();  
          
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from empProfile");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Emp e=new Emp();
                e.setId(rs.getInt("emp_id"));  
                e.setName(rs.getString("emp_name"));  
                e.setPass(rs.getString("emp_pass"));  
                e.setEmail(rs.getString("emp_uname"));  
                e.setDep(rs.getString("emp_dep"));  
                e.setMobile(rs.getString("emp_mobile"));  
                e.setAddr(rs.getString("emp_addr"));
                list.add(e);  
            }  
            con.close();  
        }catch(Exception ee){System.out.println("error is here "+ee);}  
          
        return list;  
    }
	
	public static int update(Emp e){  
        int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("update empProfile set emp_pass=?,emp_name=?,emp_dep=?,emp_mobile=?,emp_addr=? where emp_id=?");  
            PreparedStatement p=con.prepareStatement("update work set emp_name=? where emp_id=?");
            
            p.setString(1,e.getName());p.setInt(2,e.getId());
            
            ps.setString(1,e.getPass());  
            ps.setString(2,e.getName());
            ps.setString(3,e.getDep());
            ps.setString(4,e.getMobile());
            ps.setString(5,e.getAddr());
            ps.setInt(6,e.getId());
            
            p.executeUpdate();
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){System.out.println("except "+ex);}  
          
        return status;  
    }
	
	public static int delete(int id) {
		int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from empProfile where emp_id=?");
            PreparedStatement p=con.prepareStatement("delete from work where emp_id=?");
            
            ps.setInt(1,id);p.setInt(1,id);  p.executeUpdate();
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){System.out.println(e);}  
          
        return status;
	}
	
	public static int addNewEmp(Emp e){  
        int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select check_acc(?,?,?,?,?)");  
            ps.setString(1,e.getEmail());  
            ps.setString(2,e.getPass());
            ps.setString(3,e.getName());
            ps.setInt(4,e.getId());
            ps.setString(5,e.getDep());
            
            ResultSet rs=ps.executeQuery();
            rs.next();
            if(rs.getString(1).equals("created")) status=1;
            else status=0;
              
            con.close();  
        }catch(Exception ex){System.out.println("except "+ex);}  
          
        return status;  
    }
	
	public static List<work> getAllWorks(){  
        List<work> list=new ArrayList<work>();  
          
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from work");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                work e=new work();
                e.setId(rs.getInt("emp_id"));
                e.setName(rs.getString("emp_name"));
                e.setPname(rs.getString("project_name"));  
                e.setRole(rs.getString("role_name"));
                e.setPerf(rs.getString("perf"));
                list.add(e);  
            }  
            con.close();  
        }catch(Exception ee){System.out.println("error is here "+ee);}  
          
        return list;  
    }
	
	public static int assign(work e){  
        int status=0;  
        try{  
            Connection con=getConnection();  
            PreparedStatement ps=con.prepareStatement("update work set project_name=?,role_name=?,perf=? where emp_id=?");             
            
            ps.setString(1,e.getPname());  
            ps.setString(2,e.getRole());
            ps.setString(3,e.getPerf());      
            ps.setInt(4,e.getId());
            
            
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){System.out.println("except "+ex);}  
          
        return status;  
    }
	
}

