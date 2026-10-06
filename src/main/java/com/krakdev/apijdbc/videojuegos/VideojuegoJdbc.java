package com.krakdev.apijdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.apijdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	// metodo crear cliente
	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {

		Connection con = null;
		PreparedStatement ps = null;
		Videojuego juego = null;

		try {

			con = Conexion.getConnection();
			String sql = """
						INSERT INTO clientes(codigo, nombre, plataforma, precio, disponible, genero) VALUES (?,?,?,?,?,?)
					""";

			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			juego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);

			int filas = ps.executeUpdate();
			log.info("Videojuego insertado: " + filas);

		} catch (Exception e) {
			log.error("Videojuego NO insertado: " + e.getMessage());

			throw new RuntimeException("Error al insertar " + e.getMessage());

		} finally {

			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return juego;

	}

	// metodo para listar
	public static List<Videojuego> listar() {

		List<Videojuego> juego = new ArrayList<>();

		Connection con = null;

		try {

			con = Conexion.getConnection();

			String sql = """
						SELECT * FROM videojuegos
					""";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Videojuego c = new Videojuego(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("plataforma"), rs.getDouble("precio"), rs.getBoolean("disponible"),
						rs.getString("genero"));

				juego.add(c);

			}

		} catch (Exception e) {
			log.error("Error al listar " + e.getMessage());
			throw new RuntimeException("Error al listar " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return juego;

	}

	// metodo buscar por codigo
	public static Videojuego buscar(String codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = """
					SELECT * FROM videojuegos WHERE codigo = ?
				""";
		ResultSet rs = null;
		Videojuego juego = null;

		try {

			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			rs = ps.executeQuery();

			if (rs.next()) {
				juego = new Videojuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"),
						rs.getDouble("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
			}

		} catch (Exception e) {
			log.error("Error al buscar " + e.getMessage());
			throw new RuntimeException("Error al recuperar " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return juego;
	}
	
	
	//actualizar un registro
		public static Videojuego actualizar(String codigo, String nuevoNombre, String nuevaPlataforma, double nuevoPrecio, boolean nuevoDisponible,
				String nuevoGenero) {
			
			Connection con = null;
			PreparedStatement ps = null;
			String sql = """
						UPDATE videojuegos SET nombre = ?, plataforma = ?, precio = ?, disponible = ?, genero = ? WHERE codigo = ?
					""";
			Videojuego juego = null;
			
			try {
				
				con = Conexion.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, nuevoNombre);
				ps.setString(2, nuevaPlataforma);
				ps.setDouble(3, nuevoPrecio);
				ps.setBoolean(4, nuevoDisponible);
				ps.setString(5, nuevoGenero);
				ps.setString(6, codigo);
				
				int fila = ps.executeUpdate();
				log.info("Videojuego actualizado: " + fila);
				juego = new Videojuego(codigo, nuevoNombre, nuevaPlataforma, nuevoPrecio, nuevoDisponible, nuevoGenero);
				
			}catch (Exception e) {
				log.error("Error al actualizar: "+e.getMessage());
				throw new RuntimeException("Error al actualizar "+e.getMessage());
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return juego;
			
		}
		
		
		
		//metodo eliminar datos
		public static boolean eliminar(String codigo) {
			
			Connection con = null;
			PreparedStatement ps = null;
			String sql = """
						DELETE FROM videojuegos WHERE codigo = ?
					""";
			
			try {
				
				con = Conexion.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, codigo);
				
				return true;
				
			}catch(Exception e) {
				log.error("Error al eliminar: "+e.getMessage());
				throw new RuntimeException("Error al eliminar "+e.getMessage());
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

}
