CREATE TABLE Usuario(
	idUsuario VARCHAR(20),
	contraseña_usuario VARCHAR(30)
	usuario_nombre VARCHAR(100),
	usuario_apellidos VARCHAR(100),
	PRIMARY KEY(idUsuario)
);

CREATE TABLE Pedido(
	idPedido VARCHAR(20),
	idUsuario VARCHAR(20),
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
	producto_nombre VARCHAR(50),
	descripcion_producto VARCHAR(500),
	stock DECIMAL(4, 0),
	precio DECIMAL (6, 2),
	
	PRIMARY KEY(idProducto)
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
	FOREIGN KEY (idPedido) REFERENCES pedido
)


INSERT INTO Almacenero VALUES ('alm01', 'cntrsAlmacenero01', 'Almacenero01', 'AlmaceneroApellido01');
INSERT INTO Almacenero VALUES ('alm02', 'cntrsAlmacenero02', 'Almacenero02', 'AlmaceneroApellido02');
INSERT INTO Almacenero VALUES ('alm03', 'cntrsAlmacenero03', 'Almacenero03', 'AlmaceneroApellido03');

INSERT INTO Producto VALUES ('prod01', 'Tobias silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 50, 100.00);
INSERT INTO Producto VALUES ('prod02',  'Gregor silla', 'La altura de la silla se puede regular y te ofrece la máxima comodidad', 20, 90.00);
INSERT INTO Producto VALUES ('prod03',  'Orfjall silla', 'Gracias a la espuma de alta densidad, la silla resultará cómoda durante muchos años.', 55, 110.00);
INSERT INTO Producto VALUES ('prod04',  'Orfjall / Sporren silla', 'Gracias a la espuma de alta densidad, la silla resultará cómoda durante muchos años.', 50, 100.00);
INSERT INTO Producto VALUES ('prod05',  'Molten silla', 'Con soporte lumbar para que tu espalda tenga más superficie de descanso y apoyo.', 10, 500.00);
INSERT INTO Producto VALUES ('prod06',  'Vagsberg silla', 'La altura de la silla se puede regular y te ofrece la máxima comodidad', 50, 100.00);
INSERT INTO Producto VALUES ('prod07',  'Jules silla', 'La altura de la silla se puede regular y te ofrece la máxima comodidad.', 50, 100.00);

INSERT INTO Producto VALUES ('prod08',  'Orjfall / Numben silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 10, 200.00);
INSERT INTO Producto VALUES ('prod09',  'Vilmar silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 54, 10.00);
INSERT INTO Producto VALUES ('prod10',  'Frode silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 70, 100.00);
INSERT INTO Producto VALUES ('prod11',  'Janinge silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 20, 100.00);
INSERT INTO Producto VALUES ('prod12',  'Tobias silla', 'It just works!', 50, 100.00);

GRANT ALL PRIVILEGES ON Producto TO UO245303;
GRANT ALL PRIVILEGES ON Producto TO UO245303;
GRANT ALL PRIVILEGES ON Producto TO UO245303;
