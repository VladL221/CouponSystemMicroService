package com.example.demo.rest;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Logger;
import com.example.demo.services.LoggerService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoggerRestController {

	
	@Autowired
	private LoggerService loggerService;

	private List<Logger> logs;
	//posts a new logg in the data base
	@PostMapping("/storeLogger")
	public ResponseEntity<?> storeLogger(@RequestBody Logger logger) {
		loggerService.addLog(logger);
		return ResponseEntity.status(HttpStatus.OK).body("Log added");
	}

	//gets loggs from data base by cclient type
	@GetMapping("/getLoggsByClientType/{type}")
	public ResponseEntity<?> getLoggsByClientType(@PathVariable("type") String clientType) {
		logs = loggerService.getAllLogsByClientType(clientType);
		if (logs != null) {
			return ResponseEntity.status(HttpStatus.OK).body((List<Logger>) Hibernate.unproxy(logs));
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	//gets loggs by cleint id and client type
	@GetMapping("/getLoggsByClientIdAndClientType/{id}/{type}")
	public ResponseEntity<?> getLoggsByClientIdAndClientType(@PathVariable("id") int clientId,
			@PathVariable("type") String clientType) {
		logs = loggerService.getAllLogsByClientIdAndClientType(clientId, clientType);
		if (logs != null) {
			return ResponseEntity.status(HttpStatus.OK).body((List<Logger>) Hibernate.unproxy(logs));
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	// gets all loggs
	@GetMapping("/getAllLoggs")
	public ResponseEntity<?> getAllLoggs() {
		logs = loggerService.getAllLogs();
		if (logs != null) {
			return ResponseEntity.status(HttpStatus.OK).body((List<Logger>) Hibernate.unproxy(logs));
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
