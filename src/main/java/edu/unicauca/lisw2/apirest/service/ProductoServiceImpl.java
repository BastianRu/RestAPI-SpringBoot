package edu.unicauca.lisw2.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unicauca.lisw2.apirest.model.Producto;
import edu.unicauca.lisw2.apirest.repository.ProductoRepository;

/**
 * Implementación de la capa de servicio para Producto.
 * Delega el acceso a datos en el repositorio JPA.
 */
@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        return productoRepository.findById(id).map(existente -> {
            existente.setNombre(producto.getNombre());
            existente.setDescripcion(producto.getDescripcion());
            existente.setPrecio(producto.getPrecio());
            existente.setCantidad(producto.getCantidad());
            return productoRepository.save(existente);
        }).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return productoRepository.existsById(id);
    }
}
