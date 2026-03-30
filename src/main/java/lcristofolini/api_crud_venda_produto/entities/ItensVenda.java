package lcristofolini.api_crud_venda_produto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_itensvenda")
public class ItensVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidade;
    private Double valor_unitario;
    private Double valor_total;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    @JsonBackReference(value = "venda-itens")
    private Venda venda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto")
    @JsonIgnoreProperties("itensVendas")
    private Produtos produto;

    public ItensVenda() {
    }

    public ItensVenda(Long id,Double quantidade, Double valor_unitario, Double valor_total) {
        this.id = id;
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
