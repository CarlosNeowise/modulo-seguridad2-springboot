create table MASCOTAS (

	idMascota int,
	nombreMascota varchar(255),
	raza varchar(255),
	colorPelo varchar(20),
	fechaNacimiento varchar(50),
	vacunaciones int
)

ALTER TABLE MASCOTAS ADD PRIMARY KEY (idMascota) ;

ALTER TABLE MASCOTAS MODIFY COLUMN idMascota int auto_increment NOT NULL;


INSERT INTO MASCOTAS (nombreMascota,raza,colorPelo,fechaNacimiento,vacunaciones)
VALUES ('Doki','Rottweiler','Negro','26/04/2012',8);

INSERT INTO MASCOTAS (nombreMascota,raza,colorPelo,fechaNacimiento,vacunaciones)
VALUES ('Totoro','Dalmata','Blanco y Negro','25/02/2015',12);

INSERT INTO MASCOTAS (nombreMascota,raza,colorPelo,fechaNacimiento,vacunaciones)
VALUES ('Bianca','Shih Tzu','Blanco y Marron','12/09/2014',6);




create procedure eliminarMascotaPerId(in m_id INT)
	begin
		delete from MASCOTAS where idMascota = m_id;
	END


call  eliminarMascotaPerId (2);

select * from  mascotas