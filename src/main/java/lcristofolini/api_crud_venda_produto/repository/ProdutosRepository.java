package lcristofolini.api_crud_venda_produto.repository;

import lcristofolini.api_crud_venda_produto.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {}
