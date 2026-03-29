package lcristofolini.api_crud_venda_produto.controller;

import lcristofolini.api_crud_venda_produto.entities.Venda;
import lcristofolini.api_crud_venda_produto.exceptions.BusinessRuleException;
import lcristofolini.api_crud_venda_produto.service.VendasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    private final VendasService vendasService;
    public VendasController(VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @PostMapping
    public ResponseEntity<Venda> criar(@RequestBody Venda venda) throws BusinessRuleException {
        Venda salva = vendasService.criarVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        return ResponseEntity.ok(vendasService.listarTodasVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> listarPorId(@PathVariable Long id) throws BusinessRuleException {
        return ResponseEntity.ok(vendasService.listarPorId(id));
    }
}
