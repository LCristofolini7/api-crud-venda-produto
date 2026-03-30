package lcristofolini.api_crud_venda_produto.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;
    private Double valor_total;

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "venda-itens")
    private List<ItensVenda> itensVendas;

    public Venda() {
    }

    public Venda(Long id, LocalDateTime data, Double valor_total) {
        this.id = id;
        this.data = data;
        this.valor_total = valor_total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public List<ItensVenda> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItensVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }
}
