package edu.unicauca.lisw2.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unicauca.lisw2.apirest.model.Producto;
import edu.unicauca.lisw2.apirest.service.IProductoService;

/**
 * Controlador REST que expone el CRUD de la entidad Producto.
 *
 * Verbos HTTP:
 *  - GET    /api/productos       -> listar todos los productos
 *  - GET    /api/productos/{id}  -> consultar un producto por id
 *  - POST   /api/productos       -> crear un nuevo producto
 *  - PUT    /api/productos/{id}  -> editar un producto existente
 *  - DELETE /api/productos/{id}  -> eliminar un producto
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevo = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        Producto actualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!productoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
