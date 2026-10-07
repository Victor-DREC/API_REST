package com.krakdev.apijdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	// Se importa de java.sql
	private static final Logger log = LogManager.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5433/apijdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Alexander2412";

	
	//se importa de java.sql
	public static Connection getConnection() {
		
	
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.error("CONEXION EXITOSA");
			return con;
		} catch (SQLException e) {
			log.error("Error en la coneccion: "+e.getMessage());
			
			//es para quitar el error de retornar algo al metodo
			throw new RuntimeException("No se pudo conectar", e);
		}
		
	}

}
