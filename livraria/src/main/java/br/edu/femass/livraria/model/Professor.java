package br.edu.femass.livraria.model;

public class Professor extends Academico{

    public Professor(String nome, String cpf, String matricula, Boolean livros_atrasados) {
        super(nome, cpf, matricula, livros_atrasados);
    }

    public void Alugar_Livro(Livro livro){
        System.out.println(super.getNome());
    };
    public void Retornar_Livro(Livro livro){
        System.out.println(super.getNome());
    };

    public void Get_Professor(){
        String atrasados;
        if(!super.getLivros_atrasados()){
            atrasados = " está";
        }else{
            atrasados = " não está";
        }
        System.out.println("O aluno " + super.getNome() + " com a matrícula " + super.getMatricula() + atrasados + " livros atrasados.");
    }
}
