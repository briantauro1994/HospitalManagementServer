package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jsonDtos.LoginDto;
import servlet.Login;

public class LoginService {

	
	public boolean validateLogin(LoginDto login)
	{
		

        try{           
          
					Connection conn = new dbconnection.DbConnection().connect();
            PreparedStatement pst = conn.prepareStatement("Select * from doctors where username=? and password=?");
            pst.setString(1, login.getName()); 
            pst.setString(2, login.getPassword());
            ResultSet rs = pst.executeQuery();                        
            if(rs.next())            
                return true;    
                   
        }
        catch(Exception e){
            e.printStackTrace();
           
        }
		return false;   
	}
	
}
