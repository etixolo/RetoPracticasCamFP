package controller;

import model.ProductoOtaku;
import model.ProductoDAO;
import view.InterfazConsola;
import java.sql.SQLException;

public class ProductoController {
    private final ProductoDAO dao;
    private final InterfazConsola vista;

    public ProductoController(ProductoDAO dao, InterfazConsola vista) {
        this.dao = dao;
        this.vista = vista;
    }

    public void agregarProducto() {
        try {
            dao.agregarProducto(vista.leerDatosProducto());
            vista.mostrarMensaje("Producto agregado");
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void buscarPorId() {
        try {
            vista.mostrarProducto(dao.obtenerProductoPorId(vista.leerId()));
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void listarProductos() {
        try {
            vista.mostrarLista(dao.obtenerTodosLosProductos());
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void actualizarProducto() {
        try {
            int id = vista.leerId();
            ProductoOtaku actual = dao.obtenerProductoPorId(id);
            if (actual == null) {
                vista.mostrarError("Producto no existe");
                return;
            }
            
            ProductoOtaku nuevo = vista.leerDatosProducto();
            nuevo.setId(id);
            
            if (dao.actualizarProducto(nuevo)) {
                vista.mostrarMensaje("Producto actualizado");
            } else {
                vista.mostrarError("No se pudo actualizar");
            }
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void eliminarProducto() {
        try {
            if (dao.eliminarProducto(vista.leerId())) {
                vista.mostrarMensaje("Producto eliminado");
            } else {
                vista.mostrarError("No se pudo eliminar");
            }
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void buscarPorNombre() {
        try {
            vista.mostrarLista(dao.buscarProductosPorNombre(vista.leerNombre()));
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}