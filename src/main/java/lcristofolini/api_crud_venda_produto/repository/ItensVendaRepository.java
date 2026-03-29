package lcristofolini.api_crud_venda_produto.repository;

import lcristofolini.api_crud_venda_produto.entities.ItensVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensVendaRepository extends JpaRepository<ItensVenda, Long>{
}
