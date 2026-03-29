package lcristofolini.api_crud_venda_produto.service;

import lcristofolini.api_crud_venda_produto.entities.Produtos;
import lcristofolini.api_crud_venda_produto.exceptions.BusinessRuleException;
import lcristofolini.api_crud_venda_produto.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private final ProdutosRepository produtosRepo;

    public ProdutosService(ProdutosRepository produtosRepo) {
        this.produtosRepo = produtosRepo;
    }

    public Produtos criar(Produtos produtos) throws BusinessRuleException {
        validarProduto(produtos);
        return produtosRepo.save(produtos);
    }

    public Produtos editarProduto(Long id, Produtos produtoEditado) throws BusinessRuleException {
        Produtos produtos = produtosRepo.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Produto não encontrado!"));

        produtos.setDescricao(produtoEditado.getDescricao());
        produtos.setPreco(produtoEditado.getPreco());
        produtos.setQtd_estoque(produtoEditado.getQtd_estoque());

        validarProduto(produtos);
        return produtosRepo.save(produtos);
    }

    public List<Produtos> listarProdutos() {
        return produtosRepo.findAll();
    }

    public Optional<Produtos> buscarProduto(Long id) throws BusinessRuleException {
        return Optional.of(produtosRepo.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Produto não encontrado!")));
    }

    public void deletarProduto(Long id) throws BusinessRuleException {
        if (!produtosRepo.existsById(id)) {
            throw new BusinessRuleException("Produto não existe!");
        }
        produtosRepo.deleteById(id);
    }

    private void validarProduto(Produtos produtos) throws BusinessRuleException {
        if (produtos.getDescricao() == null || produtos.getDescricao().isBlank()) {
            throw new BusinessRuleException("Descrição não pode ser vazia");
        }
        if (produtos.getDescricao().length() > 50) {
            throw new BusinessRuleException("Descrição deve ter no máximo 50 caracteres");
        }
        if (produtos.getPreco() == null || produtos.getPreco() <= 0) {
            throw new BusinessRuleException("Preço deve ser maior que zero");
        }
        if (produtos.getQtd_estoque() == null || produtos.getQtd_estoque() < 0) {
            throw new BusinessRuleException("Quantidade em Estoque não pode ser negativa");
        }
    }
}
