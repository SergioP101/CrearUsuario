package RRHH;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GestionUsuariosIntegracionMockitoTest {

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    @Test
    void testCrearUsuario_UsuarioSeAgregaALista() {
        // Arrange
        Usuario nuevoUsuario = new Usuario(1, "Alicia", "12345678A", "alicia@ejemplo.com", "contraseña", "IT", new Date());

        // Act
        gestionUsuarios.crearUsuario(nuevoUsuario);

        // Assert
        List<Usuario> usuarios = gestionUsuarios.getUsuarios();
        assertEquals(1, usuarios.size());
        assertTrue(usuarios.stream().anyMatch(u -> u.getDni().equals("12345678A")));
    }

    @Test
    void testCrearUsuarioDuplicado_UsuarioNoSeAgrega() {
        // Arrange
        Usuario usuarioExistente = new Usuario(1, "Roberto", "98765432B", "roberto@ejemplo.com", "clave", "RRHH", new Date());
        Usuario nuevoUsuarioDuplicado = new Usuario(2, "Ricardo", "98765432B", "ricardo@ejemplo.net", "otraClave", "Finanzas", new Date());
        gestionUsuarios.crearUsuario(usuarioExistente);

        // Act
        gestionUsuarios.crearUsuario(nuevoUsuarioDuplicado);

        // Assert
        List<Usuario> usuarios = gestionUsuarios.getUsuarios();
        assertEquals(1, usuarios.size());
        assertTrue(usuarios.stream().anyMatch(u -> u.getDni().equals("98765432B")));
        assertFalse(usuarios.stream().anyMatch(u -> u.getDni().equals("ricardo@ejemplo.net")));
    }

    @Test
    void testObtenerUsuarioPorId_UsuarioEncontrado() {
        // Arrange
        Usuario usuario1 = new Usuario(1, "Elena", "11223344C", "elena@ejemplo.com", "segura", "Marketing", new Date());
        Usuario usuario2 = new Usuario(2, "Javier", "55667788D", "javier@ejemplo.com", "fuerte", "Ventas", new Date());
        gestionUsuarios.crearUsuario(usuario1);
        gestionUsuarios.crearUsuario(usuario2);

        // Act
        Usuario usuarioObtenido = gestionUsuarios.obtenerUsuarioPorId(2);

        // Assert
        assertEquals(usuario2, usuarioObtenido);
    }

    @Test
    void testObtenerUsuarioPorId_UsuarioNoEncontrado() {
        // Arrange
        Usuario usuario1 = new Usuario(1, "Elena", "11223344C", "elena@ejemplo.com", "segura", "Marketing", new Date());
        gestionUsuarios.crearUsuario(usuario1);

        // Act
        Usuario usuarioObtenido = gestionUsuarios.obtenerUsuarioPorId(3);

        // Assert
        assertNull(usuarioObtenido);
    }

    @Test
    void testActualizarUsuario_UsuarioSeActualiza() {
        // Arrange
        Usuario usuarioOriginal = new Usuario(1, "Carmen", "99001122E", "carmen@ejemplo.com", "antigua", "Legal", new Date());
        Usuario usuarioActualizado = new Usuario(1, "Carmela", "99001122E", "carmela@ejemplo.org", "nueva", "Legal", new Date());
        gestionUsuarios.crearUsuario(usuarioOriginal);

        // Act
        gestionUsuarios.actualizarUsuario(usuarioActualizado);

        // Assert
        Usuario usuarioObtenido = gestionUsuarios.obtenerUsuarioPorId(1);
        assertEquals("Carmela", usuarioObtenido.getNombre());
        assertEquals("carmela@ejemplo.org", usuarioObtenido.getEmail());
    }

    @Test
    void testEliminarUsuario_UsuarioSeElimina() {
        // Arrange
        Usuario usuarioAEliminar = new Usuario(1, "Daniel", "33445566F", "daniel@ejemplo.com", "secreto", "Desarrollo", new Date());
        gestionUsuarios.crearUsuario(usuarioAEliminar);

        // Act
        gestionUsuarios.eliminarUsuario(1);

        // Assert
        assertNull(gestionUsuarios.obtenerUsuarioPorId(1));
        assertEquals(0, gestionUsuarios.getUsuarios().size());
    }

    @Test
    void testListarUsuarios_DevuelveTodosLosUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario(1, "Sofía", "77889900G", "sofia@ejemplo.com", "uno", "Diseño", new Date());
        Usuario usuario2 = new Usuario(2, "Mateo", "22334455H", "mateo@ejemplo.net", "dos", "Calidad", new Date());
        gestionUsuarios.crearUsuario(usuario1);
        gestionUsuarios.crearUsuario(usuario2);

        // Act
        List<Usuario> listaDeUsuarios = gestionUsuarios.listarUsuarios();

        // Assert
        assertNotNull(listaDeUsuarios);
        assertEquals(2, listaDeUsuarios.size());
        assertTrue(listaDeUsuarios.stream().anyMatch(u -> u.getNombre().equals("Sofía")));
        assertTrue(listaDeUsuarios.stream().anyMatch(u -> u.getNombre().equals("Mateo")));
    }
}