package RRHH;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de GestionUsuarios
        GestionUsuarios gestionUsuarios = new GestionUsuarios();

        // Crear algunos objetos Usuario, asegurándonos de proporcionar los 7 argumentos
        Usuario usuario1 = new Usuario(1, "Alice Smith", "12345678Z", "alice.smith@example.com", "123 Main St", "password1", new Date());
        Usuario usuario2 = new Usuario(2, "Bob Johnson", "98765432Y", "bob.johnson@example.com", "456 Oak Ave", "secure2", new Date());
        Usuario usuario3 = new Usuario(3, "Alice Smith", "12345678Z", "alice.s@example.com", "789 Pine Ln", "anotherPass", new Date()); // Mismo DNI que usuario1

        // Intentar crear los usuarios
        gestionUsuarios.crearUsuario(usuario1);
        gestionUsuarios.crearUsuario(usuario2);
        gestionUsuarios.crearUsuario(usuario3); // Intentará crear un usuario con DNI duplicado

        // Intentar obtener un usuario por ID
        Usuario usuarioEncontrado = gestionUsuarios.obtenerUsuarioPorId(2);
        if (usuarioEncontrado != null) {
            System.out.println("\nUsuario con ID 2 encontrado: " + usuarioEncontrado.getNombre());
        } else {
            System.out.println("\nUsuario con ID 2 no encontrado.");
        }

        Usuario usuarioNoEncontrado = gestionUsuarios.obtenerUsuarioPorId(4);
        if (usuarioNoEncontrado != null) {
            System.out.println("Usuario con ID 4 encontrado: " + usuarioNoEncontrado.getNombre());
        } else {
            System.out.println("Usuario con ID 4 no encontrado.");
        }
    }
}