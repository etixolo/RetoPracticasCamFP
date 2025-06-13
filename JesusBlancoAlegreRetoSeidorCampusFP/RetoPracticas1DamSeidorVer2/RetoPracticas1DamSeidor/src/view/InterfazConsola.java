package view;

import model.ProductoOtaku;
import java.util.List;
import java.util.Scanner;

public class InterfazConsola {
    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== MENÚ OTAKU ===");
        System.out.println("1. Añadir producto");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todos");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Buscar por nombre");
        System.out.println("7. Generar descripción IA");
        System.out.println("8. Sugerir categoría IA");
        System.out.println("9. Salir");
        System.out.print("Elige: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public ProductoOtaku leerDatosProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        return new ProductoOtaku(0, nombre, categoria, precio, stock);
    }

    public int leerId() {
        System.out.print("ID del producto: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String leerNombre() {
        System.out.print("Nombre: ");
        return scanner.nextLine();
    }

    public void mostrarProducto(ProductoOtaku producto) {
        if (producto != null) {
            System.out.printf("\nID: %d | %s (%s) | $%.2f | Stock: %d\n",
                producto.getId(), producto.getNombre(), producto.getCategoria(),
                producto.getPrecio(), producto.getStock());
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    public void mostrarLista(List<ProductoOtaku> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos");
            return;
        }
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        productos.forEach(this::mostrarProducto);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.err.println("Error: " + error);
    }

    public void mostrarDescripcionIA(String descripcion) {
        System.out.println("\nDescripción generada:\n" + descripcion);
    }

    public void mostrarSugerenciaCategoria(String sugerencia) {
        System.out.println("\nCategoría sugerida: " + sugerencia);
    }
}