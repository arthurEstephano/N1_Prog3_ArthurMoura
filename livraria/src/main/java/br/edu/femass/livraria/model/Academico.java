package br.edu.femass.livraria.model;

import javafx.scene.control.Alert;
import lombok.Data;

import java.io.IOException;
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
    private long dias;

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

    public Academico(String nome, String cpf, Estado_Academico estado_academico) {
        this.nome = nome;
        this.cpf = cpf;
        this.estado_academico = estado_academico;
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
        //TODO: Para usar os métodos de teste, deve-se comentar todas as linnhas na classe acadêmico que contém "Alert"
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Error 403");
        for (Livro livros: this.livros_alugados) {
                if(livros.getData_emprestimo() != null) {
                    if (livros.getData_prev_delovucao().isBefore(LocalDate.now())) {
                        this.livros_atrasados = true;
                        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                        infoAlert.setHeaderText("Informativo.");
                        infoAlert.setContentText("O livro " + livro.getNome() + " está atrasado.");
                        infoAlert.showAndWait();
                    }
                }
        }
        if(this.livros_atrasados){
            errorAlert.setContentText("Empréstimo bloqueado, você tem livros atrasados!");
            errorAlert.showAndWait();
        }
        else if(this.livros_alugados.size() >= 5){
            errorAlert.setContentText("Empréstimo bloqueado, você já têm 5 livros em sua posse!");
            errorAlert.showAndWait();
        } else if(livro.getEmprestimo()) {
            if(this.livros_alugados.contains(livro)){
                errorAlert.setContentText("Empréstimo bloqueado, o livro em questão já em sua posse.");
                errorAlert.showAndWait();
            }
            else{
                errorAlert.setContentText("Empréstimo bloqueado, o livro em questão já está em posse de outro.");
                errorAlert.showAndWait();
            }
        }else if(!livro.getDisponibolidade()){
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
            //TODO: Para usar os métodos de teste, deve-se comentar todas as linnhas na classe acadêmico que contém "Alert"
            if(this.livros_alugados == null){return;}
            List<Livro> atrasos = new ArrayList<>();
            for (Livro livros: this.livros_alugados) {
                    if (livros.getData_prev_delovucao().isBefore(LocalDate.now())) {
                        atrasos.add(livros);
                }
            }
            if(atrasos.size() >= 1 && !atrasos.contains(livro)){
                this.setLivros_atrasados(true);
            }
            else{
                this.setLivros_atrasados(false);
            }
            if(!this.livros_alugados.contains(livro)) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error 403");
                errorAlert.setContentText("Você não está com esse livro em sua posse.");
                errorAlert.showAndWait();
                }
            else {
                livro.setEmprestimo(false);
                livro.setData_emprestimo(null);
                livro.setData_prev_delovucao(null);
                livro.setData_devolucao(LocalDate.now());
                this.livros_alugados.remove(livro);
                Alert sucessAlert = new Alert(Alert.AlertType.CONFIRMATION);
                sucessAlert.setContentText("O livro " + livro.getNome() + " foi devolvido.");
                sucessAlert.showAndWait();
            }
        }
    }

