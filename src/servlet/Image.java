package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;

import jsonDtos.LoginDto;
import jsonDtos.PatientDto;
import jsonDtos.StringDto;
import service.DoctorService;

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream	inputStream = new BufferedInputStream(request.getInputStream());
		   ArrayList<StringDto> sendimg=new ArrayList<StringDto>();
	     String json = convertInputStreamToString(inputStream);
	     PatientDto patientDto = new Gson().fromJson(json,PatientDto.class);
	     
	     DoctorService doctorService=new DoctorService();
	     ArrayList<String> floc=doctorService.findloc(patientDto);
	     StringDto s = null;
	     for(int i=0;i<floc.size();i++){
	     File myFile = new File(floc.get(i));
	    System.out.println("name"+floc.get(i));
	   
	    FileInputStream imageInFile = new FileInputStream(myFile);
	     byte imageData[] = new byte[(int) myFile.length()];
	     imageInFile.read(imageData);

	     /*
	     * Converting Image byte array into Base64 String
	     */
	     String imageDataString = encodeImage(imageData);
	     s=new StringDto();
	     s.setMessage(imageDataString);
	     sendimg.add(s);
	     }
	     response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new Gson().toJson(sendimg));
		
		
	     
	     
	     
	     
		
	}
	public static String encodeImage(byte[] imageByteArray) {
		  return Base64.encodeBase64String(imageByteArray);
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
