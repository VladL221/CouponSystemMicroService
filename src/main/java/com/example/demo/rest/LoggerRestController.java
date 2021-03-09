package com.example.demo.rest;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/printLog")
	public ResponseEntity<?> printLog(@RequestBody Logger logger){
		System.out.println(logger);
		System.out.println(logger.toString());
		return ResponseEntity.ok(logger);
	}
}
