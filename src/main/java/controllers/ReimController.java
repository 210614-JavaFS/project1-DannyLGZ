package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.Reimbursement;
import services.ReimService;

public class ReimController {
	
	private static ReimService reimService = new ReimService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public void getAllReim(HttpServletResponse response) throws IOException {
		List<Reimbursement> allReimList = reimService.getAllReim();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String json = objectMapper.writeValueAsString(allReimList);
		
		System.out.println(json);
		
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	
	public void getPendingReim(HttpServletResponse response) throws IOException {
		List<Reimbursement> allPendingList = reimService.getPendingReim();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String json = objectMapper.writeValueAsString(allPendingList);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	public void getReimByAuthor(HttpServletResponse response, int userId) throws IOException {
		List<Reimbursement> allMyReimList = reimService.getReimByAutor(userId);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String json = objectMapper.writeValueAsString(allMyReimList);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	public void getReimById(HttpServletResponse response, int reimId) throws IOException {
		Reimbursement reimbursement = reimService.getReimById(reimId);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String json = objectMapper.writeValueAsString(reimbursement);
		response.getWriter().print(json);
		response.setStatus(200);
	}
	
	
	public void addReim(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Reimbursement reimbursement = objectMapper.readValue(body, Reimbursement.class);
		
		if(reimService.addReim(reimbursement)) {
			response.setStatus(201);
		}else {
			response.setStatus(406);
		}
	}
	
	
}
