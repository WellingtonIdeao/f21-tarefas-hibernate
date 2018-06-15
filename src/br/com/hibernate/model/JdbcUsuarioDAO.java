package br.com.hibernate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.hibernate.dao.IdefaultDAO;

@Repository
public class JdbcUsuarioDAO implements IdefaultDAO<Usuario>{
	
	private Connection connection;
	
	@Autowired
	public JdbcUsuarioDAO(DataSource dataSource){
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	 
	
	public boolean existeUsuario(Usuario usuario){
		String sql = "select from usuarios where login=? and senha=?";
	
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}	
		rs.close();
		stmt.close();
		return false;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


	@Override
	public void adiciona(Usuario t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Usuario> lista() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void altera(Usuario t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Usuario buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void finaliza(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean existe(Usuario t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}	