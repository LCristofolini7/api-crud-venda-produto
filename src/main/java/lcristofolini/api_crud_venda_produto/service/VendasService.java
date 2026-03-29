package lcristofolini.api_crud_venda_produto.service;

import jakarta.transaction.Transactional;
import lcristofolini.api_crud_venda_produto.entities.ItensVenda;
import lcristofolini.api_crud_venda_produto.entities.Produtos;
import lcristofolini.api_crud_venda_produto.entities.Venda;
import lcristofolini.api_crud_venda_produto.repository.ItensVendaRepository;
import lcristofolini.api_crud_venda_produto.repository.ProdutosRepository;
import lcristofolini.api_crud_venda_produto.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendasService {

    private final VendaRepository vendaRepo;
    private final ProdutosRepository produtosRepo;
    private final ItensVendaRepository itensVendaRepo;

    public VendasService(VendaRepository vendaRepo, ProdutosRepository produtosRepo, ItensVendaRepository itensVendaRepo) {
        this.vendaRepo = vendaRepo;
        this.produtosRepo = produtosRepo;
        this.itensVendaRepo = itensVendaRepo;
    }

    @Transactional
    public Venda criarVenda(Venda venda) {

        if (venda.getItensVendas() == null || venda.getItensVendas().isEmpty()) {
            // throw new futuro erro;
        }

        List<Long> ids = venda.getItensVendas().stream()
                .map(item -> item.getProduto().getId())
                .toList();
        if (ids.size() != ids.stream().distinct().count()) {
            // throw new futuro erro;
        }

        venda.setData(LocalDateTime.now());
        venda.setValor_total(0D);
        Venda vendaCriada = vendaRepo.save(venda);

        List<ItensVenda> itensCriados = new ArrayList<>();

        for (ItensVenda item : venda.getItensVendas()) {

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                // throw new futuro erro;
            }

            Produtos produto = produtosRepo.findById(item.getProduto().getId())
            // .orElseThrow ( () -> new etc;

            if (produto.getQtd_estoque().compareTo(item.getQuantidade()) < 0) {
                // throw new etc
            }

            produto.setQtd_estoque(produto.getQtd_estoque()
                    .subtract(item.getQuantidade()));
            produtosRepo.save(produto);

            item.setValor_unitario(produto.getPreco());
            item.setValor_total(produto.getQtd_estoque()
                    .multiply(item.getQuantidade()));

            item.setVenda(vendaCriada);

            itensCriados.add(item);
            totalVenda = totalVenda.add(item.getValor_total());
        }

        itensVendaRepo.saveAll(itensCriados);

        vendaCriada.setValor_total(totalVenda);
        return vendaRepo.save(vendaCriada);
    }

    public List<Venda> listarTodasVendas() {
        return vendaRepo.findAll();
    }

    public Venda listarPorId(Long id) {
        return vendaRepo.findById(id)
                //.orElseThrow new blabla
    }
}
