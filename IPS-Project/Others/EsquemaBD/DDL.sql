CREATE TABLE Usuario(
	idUsuario VARCHAR(20),
	contraseña_usuario VARCHAR(30)
	usuario_nombre VARCHAR(100),
	usuario_apellidos VARCHAR(100),
	PRIMARY KEY(idUsuario)
);

CREATE TABLE Pedido(
	idPedido VARCHAR(20),
	precio_pedido NUMBER(5,2),
	fecha DATE,
	FOREIGN KEY(idUsuario) references Usuario

);

CREATE TABLE Almacenero(
	idAlmacenero VARCHAR(20),
	contraseña_almacenero VARCHAR(30),
	almacenero_nombre VARCHAR(100),
	almacenero_apellidos VARCHAR(100),
	PRIMARY KEY (idAlmacenero)
);