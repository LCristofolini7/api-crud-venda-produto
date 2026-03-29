package lcristofolini.api_crud_venda_produto.service;

import lcristofolini.api_crud_venda_produto.entities.Produtos;
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

    public Produtos criar(Produtos produtos) {
        validarProduto(produtos);
        return produtosRepo.save(produtos);
    }

    public Produtos editarProduto(Long id, Produtos produtoEditado) {
        Produtos produtos = produtosRepo.findById(id)
                // .orElseThrow(() -> new "erro futuro"("Produto não encontrado!"));

        produtos.setDescricao(produtoEditado.getDescricao());
        produtos.setPreco(produtoEditado.getPreco());
        produtos.setQtd_estoque(produtoEditado.getQtd_estoque());

        validarProduto(produtos);
        return produtosRepo.save(produtos);
    }

    public List<Produtos> listarProdutos() {
        return produtosRepo.findAll();
    }

    public Optional<Produtos> buscarProduto(Long id) {
        return produtosRepo.findById(id)
                //.orElseThrow( () -> new "erro futuro"("Produto não encontrado");
    }

    public void deletarProduto(Long id) {
        if (!produtosRepo.existsById(id)) {
            // throw new erro futuro etc;
        }
        produtosRepo.deleteById(id);
    }

    private void validarProduto(Produtos produtos) {
        if (produtos.getDescricao() == null || produtos.getDescricao().isBlank()) {
            // throw new "futuro erro" ("Descrição não pode ser vazia");
        }
        if (produtos.getDescricao().length() > 50){
            // throw new "futuro erro" ("Descrição deve ter no máximo 50 caracteres");
        }
        if (produtos.getPreco() == null || produtos.getPreco() <= 0){
            // throw new "futuro erro" ("Preço deve ser maior que zero");
        }
        if (produtos.getQtd_estoque() == null || produtos.getQtd_estoque() < 0) {
            // throw new "futuro erro" ("Quantidade em Estoque não pode ser negativa");
        }
    }
}
