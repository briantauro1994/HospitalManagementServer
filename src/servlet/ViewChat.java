package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jsonDtos.ChatDto;
import jsonDtos.StringDto;
import service.DoctorService;

/**
 * Servlet implementation class ViewChat
 */
@WebServlet("/ViewChat")
public class ViewChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewChat() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream	inputStream = new BufferedInputStream(request.getInputStream());
		 
	     String json = convertInputStreamToString(inputStream);
	     ChatDto chatDto = new Gson().fromJson(json,ChatDto.class);
	     
	     DoctorService doctorService=new DoctorService();
	    
	     ArrayList<StringDto> chatDtos=doctorService.viewchat(chatDto);
	     
	     
	     response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new Gson().toJson(chatDtos));
	     
	     
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
