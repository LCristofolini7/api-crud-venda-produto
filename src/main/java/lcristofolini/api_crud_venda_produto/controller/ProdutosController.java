package lcristofolini.api_crud_venda_produto.controller;

import lcristofolini.api_crud_venda_produto.entities.Produtos;
import lcristofolini.api_crud_venda_produto.exceptions.BusinessRuleException;
import lcristofolini.api_crud_venda_produto.service.ProdutosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @PostMapping
    public ResponseEntity<Produtos> criar(@RequestBody Produtos produtos) throws BusinessRuleException {
        Produtos salvo = produtosService.criar(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> editar(@PathVariable Long id, @RequestBody Produtos produtos) throws BusinessRuleException {
        Produtos atualizado = produtosService.editarProduto(id, produtos);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> listarTodos() {
        return ResponseEntity.ok(produtosService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produtos>> listarPorId(@PathVariable Long id) throws BusinessRuleException {
        return ResponseEntity.ok(produtosService.buscarProduto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletar(@PathVariable Long id) throws BusinessRuleException {
        produtosService.deletarProduto(id);
        return ResponseEntity.ok(Map.of("mensagem", "Deletado com Sucesso!"));
    }
}
