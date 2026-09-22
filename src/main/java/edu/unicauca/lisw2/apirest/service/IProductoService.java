package edu.unicauca.lisw2.apirest.service;

import java.util.List;
import java.util.Optional;

import edu.unicauca.lisw2.apirest.model.Producto;

/**
 * Contrato de la capa de servicio para la entidad Producto.
 * La capa de servicio concentra la lógica de negocio y hace de
 * intermediaria entre el controlador y el repositorio.
 */
public interface IProductoService {

    List<Producto> listar();

    Optional<Producto> buscarPorId(Long id);

    Producto guardar(Producto producto);

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);

    boolean existe(Long id);
}
