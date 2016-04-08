package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jsonDtos.LoginDto;
import jsonDtos.PatHistoryDto;
import jsonDtos.PatInfoDto;
import jsonDtos.PatientDto;
import jsonDtos.StringDto;

public class DoctorService {

	
	public ArrayList findloc(PatientDto patientDto)
	{
		
ArrayList<String> loc;
loc=new ArrayList<String>();
        try{           
          
					Connection conn = new dbconnection.DbConnection().connect();
            PreparedStatement pst = conn.prepareStatement("Select * from patientimages where pid=? ");
            pst.setString(1, patientDto.getId()); 
            
            ResultSet rs = pst.executeQuery();                        
            while(rs.next())            
            {
            	loc.add(rs.getString("location"));
            }
                   return loc;
        }
        catch(Exception e){
            e.printStackTrace();
           
        }
		return loc;
	}
	public ArrayList<StringDto> findvloc(PatientDto patientDto)
	{
		
ArrayList<StringDto> loc;
loc=new ArrayList<StringDto>();

StringDto st;
        try{           
          
					Connection conn = new dbconnection.DbConnection().connect();
            PreparedStatement pst = conn.prepareStatement("Select * from patientvideos where pid=? ");
            pst.setString(1, patientDto.getId()); 
            
            ResultSet rs = pst.executeQuery();                        
            while(rs.next())            
            {
            	st=new StringDto();
            	st.setMessage(rs.getString("location"));
            
            	loc.add(st);
            }
                   return loc;
        }
        catch(Exception e){
            e.printStackTrace();
           
        }
		return loc;
	}
	public PatInfoDto getpatdet(PatientDto patientDto)
	{
		PatInfoDto patInfoDto=null;
		try{           
	          patInfoDto=new PatInfoDto();
			Connection conn = new dbconnection.DbConnection().connect();
    PreparedStatement pst = conn.prepareStatement("Select * from Patients where id=? ");
    pst.setString(1, patientDto.getId()); 
    ResultSet rs = pst.executeQuery();                        
    while(rs.next())            
    {
    	patInfoDto.setAddress(rs.getString("Address"));
    	patInfoDto.setSex(rs.getString("Sex"));
    	
    	patInfoDto.setAge(rs.getString("Age"));
    	
    	patInfoDto.setName(rs.getString("Pname"));
    	
    	patInfoDto.setPhoneno(rs.getString("Pnumber"));
    	
    	patInfoDto.setIllness(rs.getString("Illness"));
    	patInfoDto.setPrescription("Prescription");
    	
    }
           return patInfoDto;
		}
		 catch(Exception e){
	            e.printStackTrace();
	           
	        }
		
		 return patInfoDto;
		
	
	}
	public PatHistoryDto getpathistory(PatientDto patientDto)
	{
		PatHistoryDto patInfoDto=null;
		try{           
	          patInfoDto=new PatHistoryDto();
			Connection conn = new dbconnection.DbConnection().connect();
    PreparedStatement pst = conn.prepareStatement("Select * from patientHistory_"+patientDto.getId()+" where id=? ");
    pst.setString(1, patientDto.getId()); 
    ResultSet rs = pst.executeQuery();                        
    while(rs.next())            
    {
   
    	patInfoDto.setIllness(rs.getString("Illness"));
    	patInfoDto.setPrescription("Prescription");
    	
    }
           return patInfoDto;
		}
		 catch(Exception e){
	            e.printStackTrace();
	           
	        }
		
		 return patInfoDto;
		
	
	}
}
