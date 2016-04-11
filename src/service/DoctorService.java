package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsonDtos.ChatDto;
import jsonDtos.DoctorMessageDto;
import jsonDtos.LoginDto;
import jsonDtos.PatHistoryDto;
import jsonDtos.PatInfoDto;
import jsonDtos.PatientDto;
import jsonDtos.StringDto;

public class DoctorService {

	public ArrayList findloc(PatientDto patientDto) {

		ArrayList<String> loc;
		loc = new ArrayList<String>();
		try {

			Connection conn = new dbconnection.DbConnection().connect();
			PreparedStatement pst = conn.prepareStatement("Select * from patientimages where pid=? ");
			pst.setString(1, patientDto.getId());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				loc.add(rs.getString("location"));
			}
			return loc;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return loc;
	}

	public ArrayList<StringDto> findvloc(PatientDto patientDto) {

		ArrayList<StringDto> loc;
		loc = new ArrayList<StringDto>();

		StringDto st;
		try {

			Connection conn = new dbconnection.DbConnection().connect();
			PreparedStatement pst = conn.prepareStatement("Select * from patientvideos where pid=? ");
			pst.setString(1, patientDto.getId());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				st = new StringDto();
				st.setMessage(rs.getString("location"));

				loc.add(st);
			}
			return loc;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return loc;
	}

	public PatInfoDto getpatdet(PatientDto patientDto) {
		PatInfoDto patInfoDto = null;
		try {
			patInfoDto = new PatInfoDto();
			Connection conn = new dbconnection.DbConnection().connect();
			PreparedStatement pst = conn.prepareStatement("Select * from Patients where id=? ");
			pst.setString(1, patientDto.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				patInfoDto.setAddress(rs.getString("Address"));
				patInfoDto.setSex(rs.getString("Sex"));

				patInfoDto.setAge(rs.getString("Age"));

				patInfoDto.setName(rs.getString("Pname"));

				patInfoDto.setPhoneno(rs.getString("Pnumber"));

				patInfoDto.setIllness(rs.getString("Illness"));
				patInfoDto.setPrescription("Prescription");

			}
			return patInfoDto;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return patInfoDto;

	}

	public PatHistoryDto getpathistory(PatientDto patientDto) {
		PatHistoryDto patInfoDto = null;
		try {
			patInfoDto = new PatHistoryDto();
			Connection conn = new dbconnection.DbConnection().connect();
			PreparedStatement pst = conn
					.prepareStatement("Select * from patientHistory_" + patientDto.getId() + " where id=? ");
			pst.setString(1, patientDto.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				patInfoDto.setIllness(rs.getString("Illness"));
				patInfoDto.setPrescription("Prescription");

			}
			return patInfoDto;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return patInfoDto;

	}

	public ArrayList<DoctorMessageDto> getdoctorlist(StringDto stringDto) {
		ArrayList<DoctorMessageDto> doctorMessageDtos = new ArrayList<DoctorMessageDto>();
		DoctorMessageDto doctorMessageDto = null;
		try {

			Connection conn = new dbconnection.DbConnection().connect();
			PreparedStatement pst = conn.prepareStatement("Select * from doctors where phoneno!="+stringDto.getMessage());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				doctorMessageDto = new DoctorMessageDto();
				doctorMessageDto.setPhoneno(rs.getString("phoneno"));
				doctorMessageDto.setName(rs.getString("DocName"));
				doctorMessageDtos.add(doctorMessageDto);
			}
			return doctorMessageDtos;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return doctorMessageDtos;

	}

	public void savechat(ChatDto chatDto) {

		Connection conn = new dbconnection.DbConnection().connect();

		String query = "insert into doctor_" + chatDto.getDid() + "(did,source,message) values(?, ?,?)";
		String query1 = "insert into doctor_" + chatDto.getSource() + "(did,source,message) values(?, ?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chatDto.getDid());
			pstmt.setString(2, chatDto.getSource());
			pstmt.setString(3, chatDto.getMessage());

			pstmt.executeUpdate();
			PreparedStatement pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, chatDto.getSource());
			pstmt1.setString(2, chatDto.getDid());
			pstmt1.setString(3, chatDto.getMessage());

			pstmt1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public ArrayList<StringDto> viewchat(ChatDto chatDto) {
ArrayList<StringDto> chatDtos=new ArrayList<StringDto>();
StringDto chatDto2=new StringDto();
		Connection conn = new dbconnection.DbConnection().connect();
		PreparedStatement pst;
		try {
			pst = conn
					.prepareStatement("Select * from doctor_" + chatDto.getDid() + " where source=? ");
	
			pst.setString(1, chatDto.getSource());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
	chatDto2=new StringDto();
				chatDto2.setMessage(rs.getString("message"));
				chatDtos.add(chatDto2);
			}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		

		}
return chatDtos;
	}

}
