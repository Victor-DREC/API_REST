
CREATE TABLE videojuegos(
	codigo VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	plataforma VARCHAR(50) NOT NULL,
	precio DOUBLE PRECISION NOT NULL,
	disponible BOOLEAN NOT NULL,
	genero VARCHAR(50)
);

SELECT * FROM videojuegos;

INSERT INTO videojuegos VALUES('AAA-321','COD','PS5',81, true, 'Aventura');

