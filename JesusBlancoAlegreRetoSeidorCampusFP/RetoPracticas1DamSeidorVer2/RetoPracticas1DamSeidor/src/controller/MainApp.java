package controller;

import config.DatabaseConnection;
import model.ProductoDAO;
import model.SetupDatos;
import view.InterfazConsola;

public class MainApp {
    public static void main(String[] args) {
        try {
            // Inicialización
            DatabaseConnection.checkDatabase();
            ProductoDAO dao = new ProductoDAO();
            SetupDatos.cargarDatosIniciales(dao);
            
            InterfazConsola vista = new InterfazConsola();
            LlmService llm = new LlmService();
            
            // Controladores
            ProductoController productoCtrl = new ProductoController(dao, vista);
            IAController iaCtrl = new IAController(dao, vista, llm);
            
            // Bucle principal
            int opcion;
            do {
                opcion = vista.mostrarMenu();
                switch (opcion) {
                    case 1 -> productoCtrl.agregarProducto();
                    case 2 -> productoCtrl.buscarPorId();
                    case 3 -> productoCtrl.listarProductos();
                    case 4 -> productoCtrl.actualizarProducto();
                    case 5 -> productoCtrl.eliminarProducto();
                    case 6 -> productoCtrl.buscarPorNombre();
                    case 7 -> iaCtrl.generarDescripcion();
                    case 8 -> iaCtrl.sugerirCategoria();
                    case 9 -> vista.mostrarMensaje("Saliendo...");
                    default -> vista.mostrarError("Opción inválida");
                }
            } while (opcion != 9);
            
        } catch (Exception e) {
            System.err.println("Error fatal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
