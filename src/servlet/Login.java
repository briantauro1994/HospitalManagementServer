package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import jsonDtos.LoginDto;
import jsonDtos.StringDto;
import service.LoginService;
import service.StaticObjects;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	LoginService loginService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	InputStream	inputStream = new BufferedInputStream(request.getInputStream());

	     String json = convertInputStreamToString(inputStream);
	     
	       LoginDto login = new Gson().fromJson(json,LoginDto.class);
	      
	//	LoginDto login=new Gson().fromJson(json, LoginDto.class);
    
		loginService=new LoginService();
System.out.println(login.getName());
		String pnum;
		StringDto s=new StringDto();
		if((pnum=loginService.validateLogin(login))!=""){
		
		s.setMessage(pnum);}
		else
		{
			s.setMessage("Failure");
		}
		
		
		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new Gson().toJson(s));
		
		
		
		
		
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
