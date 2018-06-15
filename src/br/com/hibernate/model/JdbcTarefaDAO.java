package br.com.hibernate.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.hibernate.dao.IdefaultDAO;

@Repository
public class JdbcTarefaDAO implements IdefaultDAO<Tarefa> {
	
	private Connection connection;
	
	@Autowired
	public JdbcTarefaDAO(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//Create
	public void adiciona(Tarefa tarefa) {
		String  sql = "insert into tarefas (descricao,finalizado) values (?,?)";
		
			try {
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setString(1,tarefa.getDescricao());
				stmt.setBoolean(2, tarefa.isFinalizado());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}	
	}
	
	//read
	public List<Tarefa> lista(){
		List<Tarefa> listTarefas = new ArrayList<Tarefa>();
		String  sql = "select * from tarefas";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				listTarefas.add(populaTarefa(rs));
			}
		
		rs.close();
		stmt.close();		
		return listTarefas;	
		
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
	}
	
	//update
	public void altera(Tarefa tarefa) {
		String sql = "update tarefas set descricao=?, finalizado=?, \"dataFinalizacao\"=? where id=?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2,tarefa.isFinalizado());
			
			stmt.setDate(3,tarefa.getDataFinalizacao()!=null?new Date(tarefa.getDataFinalizacao().getTimeInMillis()):null);
			stmt.setLong(4,tarefa.getId());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//delete
	public void remove(Long id) {
		String sql = "delete from tarefas where id=?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	public Tarefa buscaPorId(Long id) {
		String sql = "select* from tarefas where id=?";
	
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return populaTarefa(rs);
			}	
		rs.close();
		stmt.close();
		return null;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public Tarefa populaTarefa(ResultSet rs) throws SQLException {
			Tarefa tarefa = new Tarefa();
			
			tarefa.setId(rs.getLong("id"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setFinalizado(rs.getBoolean("finalizado"));
			Date data = null;
			data = rs.getDate("dataFinalizacao");
			
			if(data != null) {
				Calendar dataFinalizacao = Calendar.getInstance();
				dataFinalizacao.setTime(data);
				tarefa.setDataFinalizacao(dataFinalizacao);
			}
		return tarefa;
	}
	public void finaliza(Long id) {
		Tarefa tarefa = buscaPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		altera(tarefa);
	}
	@Override
	public boolean existe(Tarefa t) {
		// TODO Auto-generated method stub
		return false;
	}
}
