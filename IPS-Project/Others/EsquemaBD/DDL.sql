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

CREATE TABLE Producto(
	idProducto VARCHAR (20),
	descripcion_producto VARCHAR(500),
	stock NUMERIC(4, 0),
	precio NUMERIC (4, 2),
	
	PRIMARY KEY idProducto
);

CREATE TABLE ProductoPedido(
	idProducto VARCHAR(20),
	idPedido VARCHAR(20),
	cantidad NUMERIC(4, 0),
	
	FOREIGN KEY(idProducto) REFERENCES Producto,
	FOREIGN KEY (idPedido) REFERENCES Pedido
);

CREATE TABLE OrdenTrabajo(
	idAlmacenero VARCHAR(20),
	idPedido VARCHAR(20),
	estado VARCHAR(15) 
	CHECK (estado IN ('Empaquetada', 'Asignada', 'Incidencia'))
	FOREIGN KEY (idAlmacenero) REFERENCES almacenero
	FO
)