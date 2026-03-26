package lcristofolini.api_crud_venda_produto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_produtos")
public class Produtos {

    @Id
    @Column(name = "produto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "qtd_estoque")
    private Double qtd_estoque;

    @OneToMany(mappedBy = "produto")
    private List<ItensVenda> itensVendas;

    public Produtos() {
    }

    public Produtos(Long id, String descricao, Double preco, Double qtd_estoque) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.qtd_estoque = qtd_estoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Double qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public List<ItensVenda> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItensVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }
}
