package lcristofolini.api_crud_venda_produto.service;

import jakarta.transaction.Transactional;
import lcristofolini.api_crud_venda_produto.entities.ItensVenda;
import lcristofolini.api_crud_venda_produto.entities.Produtos;
import lcristofolini.api_crud_venda_produto.entities.Venda;
import lcristofolini.api_crud_venda_produto.exceptions.BusinessRuleException;
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
    public Venda criarVenda(Venda venda) throws BusinessRuleException {

        if (venda.getItensVendas() == null || venda.getItensVendas().isEmpty()) {
            throw new BusinessRuleException("A venda deve conter pelo menos um item");
        }

        List<Long> ids = venda.getItensVendas().stream()
                .map(item -> item.getProduto().getId())
                .toList();
        if (ids.size() != ids.stream().distinct().count()) {
            throw new BusinessRuleException("Não é permitido adicionar o mesmo produto mais de uma vez");
        }

        venda.setData(LocalDateTime.now());
        venda.setValor_total(0D);
        Venda vendaCriada = vendaRepo.save(venda);

        List<ItensVenda> itensCriados = new ArrayList<>();

        Double totalVenda = 0.0;
        for (ItensVenda item : venda.getItensVendas()) {

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new BusinessRuleException("Quantidade do item deve ser maior do que zero.");
            }

            Produtos produto = produtosRepo.findById(item.getProduto().getId())
                    .orElseThrow(() -> new BusinessRuleException(
                            "Produto: id -> " + item.getProduto().getId() + " não encontrado!"));

            if (produto.getQtd_estoque() < item.getQuantidade()) {
                throw new BusinessRuleException(
                        "Estoque insuficiente para o produto: " + produto.getDescricao() +
                                ". Disponível: " + produto.getQtd_estoque());
            }

            produto.setQtd_estoque(produto.getQtd_estoque() - item.getQuantidade());
            produtosRepo.save(produto);

            item.setValor_unitario(produto.getPreco());
            item.setValor_total(produto.getPreco() * item.getQuantidade());

            item.setVenda(vendaCriada);

            itensCriados.add(item);
            totalVenda += item.getValor_total();
        }

        itensVendaRepo.saveAll(itensCriados);

        vendaCriada.setValor_total(totalVenda);
        return vendaRepo.save(vendaCriada);
    }

    public List<Venda> listarTodasVendas() {
        return vendaRepo.findAll();
    }

    public Venda listarPorId(Long id) throws BusinessRuleException {
        return vendaRepo.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Produto não encontrado!"));
    }
}
