import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {        // Utiliza os setters para validar os dados na construção do objeto
        setNome(nome);
        setIdade(idade);
    }
    
    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido!");
        } 
    }
    
    public int getIdade() { 
        return idade; 
    }
    
    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade; 
        } else {
            throw new IllegalArgumentException("Idade não pode ser negativa!");
        }
    }

    @Override 
    public String toString(){
       return "Nome: " + nome + ", Idade: " + idade; 
    }
}

public class Main {
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do { 
            System.out.println("\n1. Adicionar Pessoa");
            System.out.println("2. Exibir Pessoas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarPessoa();
                    break;
                case 2:
                    exibirPessoas();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    private static void adicionarPessoa() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        int idade = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print("Idade: ");
            if (scanner.hasNextInt()){
                idade = scanner.nextInt();
                scanner.nextLine(); 
                entradaValida = true;
            } else {
                System.out.println("Valor de idade não é um número válido!");
                scanner.nextLine(); // Descarta a entrada inválida
            }
        }

        try {
            Pessoa pessoa = new Pessoa(nome, idade);
            pessoas.add(pessoa);
            System.out.println("Pessoa adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirPessoas() { // Implementado opções de classificar por nome ou idade e a ferramenta de limpar a lista 
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Exibir por ordem alfabética (Nome)");
            System.out.println("2. Exibir por idade");
            System.out.println("3. Limpar lista de pessoas");
            System.out.print("Opção: ");
            int opcaoOrdenacao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcaoOrdenacao) {  // Cria uma cópia da lista e ordena por nome
                case 1:
                    ArrayList<Pessoa> listaPorNome = new ArrayList<>(pessoas);
                    listaPorNome.sort(Comparator.comparing(Pessoa::getNome));
                    System.out.println("\nLista de Pessoas (Ordenadas por Nome):");
                    for (Pessoa p : listaPorNome) {
                        System.out.println(p);
                    }
                    break;
                case 2: // Cria uma cópia da lista e ordena por idade
                    ArrayList<Pessoa> listaPorIdade = new ArrayList<>(pessoas);
                    listaPorIdade.sort(Comparator.comparingInt(Pessoa::getIdade));
                    System.out.println("\nLista de Pessoas (Ordenadas por Idade):");
                    for (Pessoa p : listaPorIdade) {
                        System.out.println(p);
                    }
                    break;
                case 3: // Limpa a lista de pessoas
                    pessoas.clear();
                    System.out.println("Lista de pessoas foi limpa.");
                    break;
                default:
                    System.out.println("Opção inválida. Retornando ao menu principal.");
            }
        }
    }
}
