package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Logger;


@Repository
public interface LoggerRepo extends JpaRepository<Logger, Integer> {

	public List<Logger> findByClientType(String clientType);
	
	public List<Logger> findByClientIdAndClientType(int clientId, String clientType);
	
}
