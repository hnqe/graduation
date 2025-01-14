package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {

	Connection con;
	String sql;
	PreparedStatement pst;
	ResultSet rs;
	
	public void salvar(Aluno a) throws SQLException {
		sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,?, ?)";
		
		try {
			con = db.getConexao();
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf( a.getDt_nasc() );
			pst.setDate(3, dataSql);
			pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			pst.close();
		}
	}
	
	public List<Aluno> listar() throws SQLException{
		List<Aluno> alunos = new ArrayList<>();
		sql = "SELECT * FROM aluno";

		try {
			con = db.getConexao();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Aluno al = new Aluno(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDate("dt_nasc").toLocalDate()
				);
				alunos.add(al);
			}
	
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			rs.close();
			pst.close();
		}
		return alunos;
	}
	
	public void alterar(Aluno a) throws SQLException {
		try {
			con = db.getConexao();
			sql = "UPDATE aluno SET nome= ?, sexo= ?, dt_nasc = ? WHERE id = ?";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
	        pst.setDate(3, Date.valueOf(a.getDt_nasc()));
			pst.setInt(4, a.getId());
			pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			pst.close();
		}
	}
	
	public void apagar(int id) throws SQLException {
		try {
			con = db.getConexao();
			sql = "DELETE FROM aluno WHERE id = ?";
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, id);
			pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			pst.close();
		}
	}
	
}
