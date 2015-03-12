CREATE TABLE Usuarios (
	id varchar(200) PRIMARY KEY,
	nomeApelidos varchar(500) NOT NULL,
	contrasinal varchar(100) NOT NULL
);

CREATE TABLE Amigos (
	idUsuario varchar(200) REFERENCES Usuarios(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	idAmigo varchar(200) REFERENCES Usuarios(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	PRIMARY KEY (idUsuario, idAmigo)
);

CREATE TABLE Peticions (
	idUsuario varchar(200) REFERENCES Usuarios(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	idPeticion varchar(200) REFERENCES Usuarios(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	PRIMARY KEY (idUsuario, idPeticion)
);