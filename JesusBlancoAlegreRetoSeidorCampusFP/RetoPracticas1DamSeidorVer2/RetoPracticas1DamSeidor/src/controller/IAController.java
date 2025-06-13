package controller;

import model.ProductoOtaku;
import model.ProductoDAO;
import view.InterfazConsola;
import java.io.IOException;
import java.sql.SQLException;

public class IAController {
    private final ProductoDAO dao;
    private final InterfazConsola vista;
    private final LlmService llm;

    public IAController(ProductoDAO dao, InterfazConsola vista, LlmService llm) {
        this.dao = dao;
        this.vista = vista;
        this.llm = llm;
    }

    public void generarDescripcion() {
        try {
            ProductoOtaku producto = dao.obtenerProductoPorId(vista.leerId());
            if (producto == null) {
                vista.mostrarError("Producto no existe");
                return;
            }
            
            String prompt = String.format(
                "Genera una descripcion simple para %s (categoria: %s)",
                producto.getNombre(), producto.getCategoria());
            
            vista.mostrarDescripcionIA(llm.generarRespuesta(prompt));
        } catch (SQLException | IOException | InterruptedException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void sugerirCategoria() {
        try {
            vista.mostrarMensaje("Ingrese nombre del nuevo producto:");
            String nombre = vista.leerNombre();
            
            String prompt = String.format(
                "Sugiere categoria para '%s' (Figura, Manga, Póster, Llavero, Ropa)",
                nombre);
            
            vista.mostrarSugerenciaCategoria(llm.generarRespuesta(prompt));
        } catch (IOException | InterruptedException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}