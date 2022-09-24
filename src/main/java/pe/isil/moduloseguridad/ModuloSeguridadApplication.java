package pe.isil.moduloseguridad;

import org.springframework.boot.SpringApplication;//SpringApplication.run
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ModuloSeguridadApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(ModuloSeguridadApplication.class, args);

		Class.forName("com.mysql.cj.jdbc.Driver");

		// STATEMENT - LISTAR
		//Conexión
		Connection conexion =
				DriverManager.getConnection("jdbc:mysql://localhost:3306/PETS",
						"root",
						"S0p0rt3");

		//Listar
		Statement smt = conexion.createStatement();
		ResultSet result =smt.executeQuery("Select * from MASCOTAS");
		System.out.println("-------Statement----------");

		//Resultado
		while (result.next()){
			System.out.println(result.getInt("idMascota") + "\n" +
								result.getString("nombreMascota") + "\n" +
								result.getString("raza") + "\n"+
								result.getString("colorPelo") + "\n" +
								result.getString("fechaNacimiento") + "\n" +
								result.getInt("vacunaciones"));
			System.out.println("----");
		}

		// PREPARED STATEMENT - INSERTAR
		Statement stCreate =conexion.createStatement();
		int filasAfectadas =stCreate.executeUpdate("Insert Into MASCOTAS Values (4,'Bandido','Bichon','Blanco con Marron','02/07/2020',6)");
		System.out.println("-------PreparedStatement----------");
		System.out.println("Filas Afectadas: " + filasAfectadas);


		PreparedStatement preparedStatement = conexion.prepareStatement("select * from MASCOTAS where nombreMascota=?");
		preparedStatement.setString(1,"Bandido");
		ResultSet resultSet1 = preparedStatement.executeQuery();

		while (resultSet1.next()){
			System.out.println(resultSet1.getInt("idMascota") + "\n" +
					resultSet1.getString("nombreMascota") + "\n" +
					resultSet1.getString("raza") + "\n"+
					resultSet1.getString("colorPelo") + "\n" +
					resultSet1.getString("fechaNacimiento") + "\n" +
					resultSet1.getInt("vacunaciones"));
		}



		// CallableStatement - Store Procedure

		CallableStatement callsp = conexion.prepareCall("{CALL eliminarMascotaPerId(?)}");
		callsp.setInt(1,2);
		ResultSet resultSetSp = callsp.executeQuery();
		System.out.println("-------CallableStatement----------");

		//System.out.println(resultSetSp.getString(callsp.getString("?")));
		System.out.println(resultSetSp);

		//Cerrar Conexión
		conexion.close();

	}


}
