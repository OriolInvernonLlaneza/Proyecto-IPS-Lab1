DROP TABLE ProductoPedido;
DROP TABLE Producto;
DROP TABLE OrdenTrabajo;
DROP TABLE Almacenero;
DROP TABLE Paquete;
DROP TABLE Pedido;
DROP TABLE Usuario;

CREATE TABLE Usuario(
	idUsuario VARCHAR(20),
	contraseña_usuario VARCHAR(30),
	usuario_nombre VARCHAR(100),
	usuario_apellidos VARCHAR(100),
	PRIMARY KEY(idUsuario),
	UNIQUE (usuario_nombre, usuario_apellidos)
);

CREATE TABLE Almacenero(
	idAlmacenero VARCHAR(20),
	contraseña_almacenero VARCHAR(30),
	almacenero_nombre VARCHAR(100),
	almacenero_apellidos VARCHAR(100),
	PRIMARY KEY (idAlmacenero),
	UNIQUE (almacenero_nombre, almacenero_apellidos)
);

CREATE TABLE Producto(
	idProducto VARCHAR (20),
	producto_nombre VARCHAR(50),
	descripcion_producto VARCHAR(500),
	codigo_barras VARCHAR(20),
	stock DECIMAL(4, 0),
	precio DECIMAL (6, 2),
	
	PRIMARY KEY(idProducto),
	UNIQUE (codigo_barras)
);

CREATE TABLE Pedido(
	idPedido VARCHAR(20),
	idUsuario VARCHAR(20),
	precio_pedido NUMBER(5,2),
	direccion VARCHAR(500),
	fecha TIMESTAMP,
	PRIMARY KEY (idPedido),
	FOREIGN KEY(idUsuario) references Usuario

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
	estado VARCHAR(70),
	CHECK (estado IN ('Empaquetada', 'Asignada', 'Incidencia', 'Marcada para empaquetar')),
	PRIMARY KEY (idPedido),
	FOREIGN KEY (idAlmacenero) REFERENCES almacenero,
	FOREIGN KEY (idPedido) REFERENCES pedido
);


CREATE TABLE Paquete(
	idPaquete VARCHAR(20),
	idPedido VARCHAR(20),
	fechaEnvoltura TIMESTAMP,
	PRIMARY KEY (idPaquete),
	FOREIGN KEY(idPedido) REFERENCES PEDIDO
);

INSERT INTO Almacenero VALUES ('alm01', 'cntrsAlmacenero01', 'Almacenero01', 'AlmaceneroApellido01');
INSERT INTO Almacenero VALUES ('alm02', 'cntrsAlmacenero02', 'Almacenero02', 'AlmaceneroApellido02');
INSERT INTO Almacenero VALUES ('alm03', 'cntrsAlmacenero03', 'Almacenero03', 'AlmaceneroApellido03');

INSERT INTO Producto VALUES ('prod01', 'Tobias silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 'cod1', 50, 100.00);
INSERT INTO Producto VALUES ('prod02',  'Gregor silla', 'La altura de la silla se puede regular y te ofrece la máxima comodidad',  'cod2', 20, 90.00);
INSERT INTO Producto VALUES ('prod03',  'Orfjall silla', 'Gracias a la espuma de alta densidad, la silla resultará cómoda durante muchos años.', 'cod3', 55, 110.00);
INSERT INTO Producto VALUES ('prod04',  'Orfjall / Sporren silla', 'Gracias a la espuma de alta densidad, la silla resultará cómoda durante muchos años.', 'cod4', 50, 100.00);
INSERT INTO Producto VALUES ('prod05',  'Molten silla', 'Con soporte lumbar para que tu espalda tenga más superficie de descanso y apoyo.', 'cod5', 10, 500.00);
INSERT INTO Producto VALUES ('prod06',  'Vagsberg silla', 'La altura de la silla se puede regular y te ofrece la máxima comodidad', 'cod6', 50, 100.00);
INSERT INTO Producto VALUES ('prod07',  'Jules silla', 'La altura de la silla se puede regular y te ofrece la máxima comodidad.', 'cod7', 50, 100.00);

INSERT INTO Producto VALUES ('prod08',  'Orjfall / Numben silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 'cod8', 10, 200.00);
INSERT INTO Producto VALUES ('prod09',  'Vilmar silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 'cod9', 54, 10.00);
INSERT INTO Producto VALUES ('prod10',  'Frode silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 'cod10', 70, 100.00);
INSERT INTO Producto VALUES ('prod11',  'Janinge silla', 'Gracias a la flexibilidad del asiento y el respaldo, resulta muy cómoda.', 'cod11', 20, 100.00);
INSERT INTO Producto VALUES ('prod12',  'Tobias silla', 'It just works!', 'cod12', 50, 100.00);


INSERT INTO Usuario VALUES ('us1', 'ps1', 'usuario1', 'apellido1');

INSERT INTO Usuario VALUES ('us2', 'ps2', 'usuario2', 'apellido2');

INSERT INTO Usuario VALUES ('us3', 'ps3', 'usuario3', 'apellido3');


INSERT INTO Pedido VALUES ('1', 'us1', 50, 'dsadasdasdasdasdasdaads', SYSDATE);
INSERT INTO Pedido VALUES ('2', 'us2', 50, 'dsadasdasdasdasdasdaads', SYSDATE);
INSERT INTO Pedido VALUES ('3', 'us3', 50, 'dsadasdasdasdasdasdaads', SYSDATE);
INSERT INTO Pedido VALUES ('4', 'us3', 50, 'dsadasdasdasdasdasdaads', SYSDATE);

//idproducto, idpedido, cantidad
INSERT INTO ProductoPedido VALUES ('prod01', '01', 2);
INSERT INTO ProductoPedido VALUES ('prod02', '01', 2);
INSERT INTO ProductoPedido VALUES ('prod03', '01', 2);

INSERT INTO ProductoPedido VALUES ('prod01', '02', 2);
INSERT INTO ProductoPedido VALUES ('prod02', '02', 7);
INSERT INTO ProductoPedido VALUES ('prod03', '02', 2);

INSERT INTO ProductoPedido VALUES ('prod01', '03', 2);
INSERT INTO ProductoPedido VALUES ('prod02', '03', 1);
INSERT INTO ProductoPedido VALUES ('prod12', '03', 7);

INSERT INTO ProductoPedido VALUES ('prod01', '04', 2);
INSERT INTO ProductoPedido VALUES ('prod06', '04', 2);
INSERT INTO ProductoPedido VALUES ('prod11', '04', 2);


GRANT ALL PRIVILEGES ON Producto TO UO244928;
GRANT ALL PRIVILEGES ON Almacenero TO UO244928;
GRANT ALL PRIVILEGES ON Ordentrabajo TO UO244928;
GRANT ALL PRIVILEGES ON Pedido TO UO244928;
GRANT ALL PRIVILEGES ON ProductoPedido TO UO244928;
GRANT ALL PRIVILEGES ON Usuario TO UO244928;
GRANT ALL PRIVILEGES ON Paquete TO UO244928;

GRANT ALL PRIVILEGES ON Producto TO UO245126;
GRANT ALL PRIVILEGES ON Almacenero TO UO245126;
GRANT ALL PRIVILEGES ON Ordentrabajo TO UO245126;
GRANT ALL PRIVILEGES ON Pedido TO UO245126;
GRANT ALL PRIVILEGES ON ProductoPedido TO UO245126;
GRANT ALL PRIVILEGES ON Usuario TO UO245126;
GRANT ALL PRIVILEGES ON Paquete TO UO245126;

GRANT ALL PRIVILEGES ON Producto TO UO245303;
GRANT ALL PRIVILEGES ON Almacenero TO UO245303;
GRANT ALL PRIVILEGES ON Ordentrabajo TO UO245303;
GRANT ALL PRIVILEGES ON Pedido TO UO245303;
GRANT ALL PRIVILEGES ON ProductoPedido TO UO245303;
GRANT ALL PRIVILEGES ON Usuario TO UO245303;
GRANT ALL PRIVILEGES ON Paquete TO UO245303;

COMMIT;
