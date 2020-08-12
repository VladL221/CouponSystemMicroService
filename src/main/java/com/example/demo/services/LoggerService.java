package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Logger;
import com.example.demo.repo.LoggerRepo;

@Service
public class LoggerService {

	@Autowired
	private LoggerRepo loggerRepository;
	
	public void addLog (Logger logger) {
		loggerRepository.save(logger);
	}
	
	public Logger getLog(int id) {
		return loggerRepository.findById(id).orElse(null);
	}
	
	public List<Logger> getAllLogsByClientType(String clientType){
		if(loggerRepository.count() > 0) {
			return loggerRepository.findByClientType(clientType);
		}else {
			return null;
		}
	}
	
	public List<Logger> getAllLogsByClientIdAndClientType(int clientId, String clientType){
		if(loggerRepository.count() > 0) {
			return loggerRepository.findByClientIdAndClientType(clientId, clientType);
		}else {
			return null;
		}
	}
	
	public List<Logger> getAllLogs(){
		if(loggerRepository.count() > 0) {
			return loggerRepository.findAll();
		}else {
			return null;
		}
	}
}
