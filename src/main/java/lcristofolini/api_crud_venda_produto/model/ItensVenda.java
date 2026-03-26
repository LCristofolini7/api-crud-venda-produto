package lcristofolini.api_crud_venda_produto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_itensvenda")
public class ItensVenda {

    @Id
    @Column(name = "itensvenda_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_venda")
    private Long id_venda;

    @Column(name = "id_produto")
    private Long id_produto;

    @Column(name = "quantidade")
    private Double quantidade;

    @Column(name = "valor_unitario")
    private Double valor_unitario;

    @Column(name = "valor_total")
    private Double valor_total;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;

    public ItensVenda() {
    }

    public ItensVenda(Long id, Long id_venda, Long id_produto, Double quantidade, Double valor_unitario, Double valor_total) {
        this.id = id;
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.valor_unitario = valor_unitario;
        this.valor_total = valor_total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_venda() {
        return id_venda;
    }

    public void setId_venda(Long id_venda) {
        this.id_venda = id_venda;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
