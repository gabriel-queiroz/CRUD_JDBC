package funcionario;

public class Funcionario {

	private String nome;
	private String matricula;
	private double salario;
	
	
	public Funcionario(){
		
	}
	
	public Funcionario( double salario){
		
		
		this.salario = salario;
	
	}
	public Funcionario(String nome, String matricula,double salario){
		
		this.nome = nome;
		this.matricula = matricula;
		this.salario = salario;
	}
	
	public double calcAumento(){
		
		return this.salario+= (this.salario/100)*20;
		
	}
	public double calcAumento(int porc){
		return this.salario+= (this.salario/100)*porc;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", matricula=" + matricula + ", salario=" + salario + "]";
	}
	
	
}
