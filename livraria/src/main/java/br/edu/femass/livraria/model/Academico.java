package br.edu.femass.livraria.model;

import javafx.scene.control.Alert;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

import static br.edu.femass.livraria.model.Estado_Academico.Professor;

@Data
public class Academico {
    private String nome;
    private String cpf;
    private static Integer proxima_matricula = 1;
    private Estado_Academico estado_academico;
    private Integer matricula;
    private Boolean livros_atrasados = false;
    private List<Livro> livros_alugados = new ArrayList<>();
    private Integer dias;

    public Academico() {
        this.matricula = proxima_matricula;
        proxima_matricula++;
        if(this.getEstado_academico() == Professor){
            dias = 30;
        }
        else {
            dias = 5;
        }
    }

    public Academico(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = proxima_matricula;
        proxima_matricula++;
        if(this.getEstado_academico() == Professor){
            dias = 30;
        }
        else {
            dias = 5;
        }
    }

    @Override
    public String toString(){
        return(this.estado_academico.toString() + " " + this.nome);
    }

    public void Alugar_Livro(Livro livro){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        for (Livro livros: this.livros_alugados) {
            if(livro.getData_emprestimo().isAfter(livro.getData_prev_delovucao())){
                this.livros_atrasados = true;
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setHeaderText("Informativo.");
                infoAlert.setContentText("O livro " + livro.getNome() + "está atrasado.");
                infoAlert.showAndWait();
            }
        }
        if(this.livros_atrasados){
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("Empréstimo bloqueado, você tem livros atrasados!");
            errorAlert.showAndWait();
        }
        if(this.livros_alugados.size() >= 5){
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("Empréstimo bloqueado, você já têm 5 livros em sua posse!");
            errorAlert.showAndWait();
        } else if (livro.getEmprestimo()) {
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("Empréstimo bloqueado, o livro em questão já está em posse de outro.");
            errorAlert.showAndWait();
        }else if (!livro.getDisponibolidade()){
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("Empréstimo bloqueado, o livro em questão só está disponível para leitura.");
            errorAlert.showAndWait();
        }
        else {
            livro.setEmprestimo(true);
            livro.setData_emprestimo(LocalDate.now());
            livro.setData_prev_delovucao(LocalDate.now().plusDays(dias));
            this.livros_alugados.add(livro);
            Alert sucessAlert = new Alert(Alert.AlertType.CONFIRMATION);
            sucessAlert.setContentText("O livro " + livro.getNome() + " agora está alugado, o mesmo deve ser devolvido em " + dias + " dias.");
            sucessAlert.showAndWait();
        }
    }
    public void Retornar_Livro(Livro livro){
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        if(this.livros_alugados == null){return;}
        for (Livro livros: this.livros_alugados) {
            if(livros == livro){
                this.livros_alugados.remove(livro);
                Alert sucessAlert = new Alert(Alert.AlertType.CONFIRMATION);
                sucessAlert.setContentText("O livro " + livro.getNome() + "foi devolvido.");
                sucessAlert.showAndWait();
            }
            if(livro.getData_emprestimo().isAfter(livro.getData_prev_delovucao())){
                infoAlert.setHeaderText("Informativo.");
                infoAlert.setContentText("O livro " + livro.getNome() + "está atrasado.");
                infoAlert.showAndWait();
                break;
            }
            else{
                this.livros_atrasados = false;
                this.livros_alugados.remove(livro);
                infoAlert.setHeaderText("Informativo.");
                infoAlert.setContentText("Você está sem pendências.");
                infoAlert.showAndWait();
                break;
            }
        }
    }
}
