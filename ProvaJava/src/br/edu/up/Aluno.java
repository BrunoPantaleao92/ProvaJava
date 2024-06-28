package br.edu.up;

public class Aluno {
    private int matricula;
    private String nome;
    private double nota;

    // Construtor
    public Aluno(int matricula, String nome, double nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    // Método para exibir informações do aluno
    @Override
    public String toString() {
        return "Aluno{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", nota=" + nota +
                '}';
    }

    public String toCSV() {
        return matricula + ";" + nome + ";" + nota;
    }
}
