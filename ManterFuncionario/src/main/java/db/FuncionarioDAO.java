package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import funcionario.Funcionario;

public class FuncionarioDAO {
	
	public List<Funcionario> listar(){
		
		List<Funcionario>  funcionarios = new ArrayList<Funcionario>();

		String sql = "select * from funcionario";


		try(Connection conexao = new ConnectionFactory().getConnection()){


			PreparedStatement ps =  conexao.prepareStatement(sql);


			ResultSet resultado = ps.executeQuery();


			while(resultado.next()){

				String nome = resultado.getString("nome");
				String matricula = resultado.getString("matricula");
				double salario = resultado.getShort("salario");


				funcionarios.add(new Funcionario(nome,matricula, salario));

			}

			resultado.close();
			ps.close();


		}catch(SQLException c){

			throw new RuntimeException(c);
		}

		return funcionarios;
		
	}
	
	public void cadastrarUsuario(Funcionario funcionario){
		
		
		try(Connection conexao = new ConnectionFactory().getConnection()){
			
		
		PreparedStatement ps = conexao.prepareStatement(
							"insert into funcionario (nome, matricula, salario) values (?,?,?)");	
		
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getMatricula());
		ps.setDouble(3, funcionario.getSalario());
		
		ps.execute();	
		ps.close();
		
		
		}catch(SQLException e){
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	
	public void excluirUsuario(String matricula){
		
		
		try(Connection conexao = new ConnectionFactory().getConnection()){
			
		
			PreparedStatement ps = conexao.prepareStatement("delete from funcionario where matricula = (?);");
			
			ps.setString(1, matricula);
			
			ps.executeUpdate();
			ps.close();
		
		}catch(SQLException c){
			
			throw new RuntimeException();
		}
		
		
	}
	
	public Funcionario pesquisarUsuario(String matricula){
		
		
		Funcionario f = null;
		
		try(Connection conexao = new ConnectionFactory().getConnection()){
			
			PreparedStatement ps = conexao.prepareStatement("select * from funcionario where matricula = (?);");
				
			ps.setString(1, matricula);
			
			ResultSet resultado = ps.executeQuery();
			
			
			
			while(resultado.next()){

				String nome = resultado.getString("nome");
				String matriculaa = resultado.getString("matricula");
				double salario = resultado.getShort("salario");
			

				f = new Funcionario(nome,matriculaa, salario);
			}
			
		}catch(SQLException c){
			
			throw new RuntimeException(c);
		}
		return f;
	}	
	
	public void atualizarFuncionario(String matricula,String nome, double salario){
		
		
		try(Connection conexao = new ConnectionFactory().getConnection()){
			
			
			
			String sql;
			
			if(nome == null){
			
				sql = "update funcionario set salario = (?) where matricula = (?) ;";
			}
			else{
				sql = "update funcionario set nome = (?) where matricula = (?) ;";
			}
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			if(nome == null){
				
				ps.setDouble(1, salario);
				ps.setString(2, matricula);
			}
			else{
				ps.setString(1, nome);
				ps.setString(2, matricula);
			}
			
			ps.executeUpdate();
			ps.close();
	
	
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
}
