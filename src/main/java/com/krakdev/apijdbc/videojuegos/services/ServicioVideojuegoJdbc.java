package com.krakdev.apijdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakdev.apijdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {

	public Videojuego crear(Videojuego juego) {

		Videojuego juegoRecuperado = VideojuegoJdbc.insertar(juego.getCodigo(), juego.getNombre(),
				juego.getPlataforma(), juego.getPrecio(), juego.isDisponible(), juego.getGenero());
		return juegoRecuperado;
	}

	public List<Videojuego> listar() {
		return VideojuegoJdbc.listar();
	}

	public Videojuego buscarPorCodigo(String codigo) {
		return VideojuegoJdbc.buscar(codigo);
	}

	public Videojuego actualizar(String codigo, Videojuego juegoActualizado) {
		return VideojuegoJdbc.actualizar(codigo, juegoActualizado.getNombre(), juegoActualizado.getPlataforma(),
				juegoActualizado.getPrecio(), juegoActualizado.isDisponible(), juegoActualizado.getGenero());
	}

	public boolean eliminar(String codigo) {
		return VideojuegoJdbc.eliminar(codigo);
	}

}
