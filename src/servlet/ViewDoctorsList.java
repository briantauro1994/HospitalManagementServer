package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jsonDtos.DoctorMessageDto;
import jsonDtos.PatientDto;
import jsonDtos.StringDto;
import service.DoctorService;


/**
 * Servlet implementation class ViewDoctorsList
 */
@WebServlet("/ViewDoctorsList")
public class ViewDoctorsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDoctorsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	System.out.println("get");
		DoctorService doctorService=new DoctorService();
		ArrayList<DoctorMessageDto> doctorMessageDto=doctorService.getdoctorlist();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(doctorMessageDto));
*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream	inputStream = new BufferedInputStream(request.getInputStream());
		 
	     String json = convertInputStreamToString(inputStream);
	     StringDto patientDto = new Gson().fromJson(json,StringDto.class);
		DoctorService doctorService=new DoctorService();
		ArrayList<DoctorMessageDto> doctorMessageDto=doctorService.getdoctorlist(patientDto);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(doctorMessageDto));
		   
	}
	private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }

}
