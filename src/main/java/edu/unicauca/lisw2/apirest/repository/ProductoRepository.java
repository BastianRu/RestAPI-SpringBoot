package edu.unicauca.lisw2.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unicauca.lisw2.apirest.model.Producto;

/**
 * Repositorio de acceso a datos para la entidad Producto.
 * Al extender JpaRepository ya se dispone de las operaciones CRUD
 * básicas (save, findById, findAll, deleteById, etc.) sin necesidad
 * de implementarlas manualmente.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
