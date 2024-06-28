package br.edu.up;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;

public class Program {

    public static void main(String[] args) {
        String csvFile = "alunos.csv";
        String line;
        String csvSplitBy = ";";

        List<Aluno> alunos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Ler e ignorar a linha de cabeçalho
            br.readLine(); // Ignora a primeira linha

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                int matricula = Integer.parseInt(data[0].trim());
                String nome = data[1].trim();

                // Usar Locale para configurar o separador decimal
                Locale locale = new Locale("pt", "BR");
                NumberFormat nf = NumberFormat.getInstance(locale);
                double nota = nf.parse(data[2].trim()).doubleValue();

                Aluno aluno = new Aluno(matricula, nome, nota);
                alunos.add(aluno);
            }

            // Calcular estatísticas
            int quantidadeAlunos = alunos.size();
            int aprovados = 0;
            int reprovados = 0;
            double menorNota = Double.MAX_VALUE;
            double maiorNota = Double.MIN_VALUE;
            double somaDasNotas = 0.0;

            for (Aluno aluno : alunos) {
                if (aluno.getNota() >= 6.0) {
                    aprovados++;
                } else {
                    reprovados++;
                }

                if (aluno.getNota() < menorNota) {
                    menorNota = aluno.getNota();
                }

                if (aluno.getNota() > maiorNota) {
                    maiorNota = aluno.getNota();
                }

                somaDasNotas += aluno.getNota();
            }

            double mediaGeral = somaDasNotas / quantidadeAlunos;

            // Exibir resumo no console
            System.out.println("Quantidade de alunos: " + quantidadeAlunos);
            System.out.println("Aprovados: " + aprovados);
            System.out.println("Reprovados: " + reprovados);
            System.out.println("Menor nota: " + menorNota);
            System.out.println("Maior nota: " + maiorNota);
            System.out.println("Média geral: " + mediaGeral);

            // Gravar resumo no arquivo resumo.csv
            try (PrintWriter writer = new PrintWriter(new FileWriter("resumo.csv"))) {
                writer.println("Quantidade de alunos," + quantidadeAlunos);
                writer.println("Aprovados," + aprovados);
                writer.println("Reprovados," + reprovados);
                writer.println("Menor nota," + menorNota);
                writer.println("Maior nota," + maiorNota);
                writer.println("Média geral," + mediaGeral);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException | java.text.ParseException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
