package RRHH;

import RRHH.GestionUsuarios;
import RRHH.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuariosCrearUsuarioJUnitTest {

    @Test
    void testCrearUsuarioNuevo() {
        // Arrange (Preparación)
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        Usuario nuevoUsuario = new Usuario(1, "Alicia", "12345678A", "alicia@ejemplo.com", "contraseñaHasheada", "IT", new Date());

        // Act (Acción)
        gestionUsuarios.crearUsuario(nuevoUsuario);

        // Assert (Verificación)
        List<Usuario> usuarios = obtenerListaUsuarios(gestionUsuarios);
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("Alicia", usuarios.get(0).getNombre());
        assertEquals("12345678A", usuarios.get(0).getDni());
    }

    @Test
    void testCrearUsuarioDuplicadoPorDni() {
        // Arrange (Preparación)
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        Usuario usuarioExistente = new Usuario(1, "Roberto", "98765432B", "roberto@ejemplo.com", "otraContraseña", "RRHH", new Date());
        gestionUsuarios.crearUsuario(usuarioExistente);
        Usuario nuevoUsuarioConMismoDni = new Usuario(2, "Ricardo", "98765432B", "ricardo@ejemplo.net", "claveDiferente", "Finanzas", new Date());

        // Act (Acción)
        gestionUsuarios.crearUsuario(nuevoUsuarioConMismoDni);

        // Assert (Verificación)
        List<Usuario> usuarios = obtenerListaUsuarios(gestionUsuarios);
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("Roberto", usuarios.get(0).getNombre());
        assertEquals("98765432B", usuarios.get(0).getDni());
    }


    private List<Usuario> obtenerListaUsuarios(GestionUsuarios gestionUsuarios) {
        try {
            java.lang.reflect.Field campoUsuarios = GestionUsuarios.class.getDeclaredField("usuarios");
            campoUsuarios.setAccessible(true);
            return (List<Usuario>) campoUsuarios.get(gestionUsuarios);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("No se pudo acceder a la lista de usuarios: " + e.getMessage());
            return null;
        }
    }
}