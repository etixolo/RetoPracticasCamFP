package model;

import java.sql.SQLException;

public class SetupDatos {
    public static void cargarDatosIniciales(ProductoDAO dao) throws SQLException {
        if (dao.obtenerTodosLosProductos().isEmpty()) {
            dao.agregarProducto(new ProductoOtaku(0, "Figura de Anya Forger", "Figura", 59.95, 8));
            dao.agregarProducto(new ProductoOtaku(0, "Manga Chainsaw Man Vol.1", "Manga", 9.99, 20));
            dao.agregarProducto(new ProductoOtaku(0, "Póster Studio Ghibli", "Póster", 15.50, 15));
        }
    }
}