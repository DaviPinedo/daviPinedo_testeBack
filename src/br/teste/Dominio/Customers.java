package br.teste.Dominio;

public class Customers {
    private int id;
   private String cpf_cnpj;
   private String nome;
   private boolean ativo;
   private double vl_total;

    public Customers(boolean ativo) {
        this.ativo = ativo;
    }

    public Customers(int id, String cpf_cnpj, String nome, boolean ativo, double vl_total) {
        this.id = id;
        this.cpf_cnpj = cpf_cnpj;
        this.nome = nome;
        this.ativo = ativo;
        this.vl_total = vl_total;
    }

    public int getId() {
        return id;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public double getVl_total() {
        return vl_total;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setVl_total(double vl_total) {
        this.vl_total = vl_total;
    }
}
