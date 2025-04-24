package RRHH;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrearUsuariosSteps {

    private GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private Usuario nuevoUsuario;
    private List<Usuario> usuariosAntesDeCreacion;

    @Given("el sistema de gestión de usuarios está inicializado")
    public void el_sistema_de_gestion_de_usuarios_esta_inicializado() {
        System.out.println("[CrearUsuariosSteps - @Given] El sistema de gestión de usuarios está inicializado.");
    }

    @When("se intenta crear un usuario con nombre {string}, DNI {string}, email {string}, departamento {string}")
    public void se_intenta_crear_un_usuario_con_nombre_dni_email_departamento(String nombre, String dni, String email, String departamento) {
        nuevoUsuario = new Usuario(0, nombre, dni, email, "contraseña_temporal", departamento, new Date());
        usuariosAntesDeCreacion = new ArrayList<>(gestionUsuarios.getUsuarios()); // Crear una copia de la lista
        System.out.println("[CrearUsuariosSteps - @When] Usuarios antes de crear: " + usuariosAntesDeCreacion.size());
        gestionUsuarios.crearUsuario(nuevoUsuario);
        System.out.println("[CrearUsuariosSteps - @When] Usuarios después de crear: " + gestionUsuarios.getUsuarios().size());
    }

    @Then("el usuario {string} debería ser creado exitosamente")
    public void el_usuario_deberia_ser_creado_exitosamente(String nombre) {
        List<Usuario> usuariosDespuesDeCreacion = gestionUsuarios.getUsuarios();
        System.out.println("[CrearUsuariosSteps - @Then] Usuarios después de la creación (para assert): " + usuariosDespuesDeCreacion.size());
        Assertions.assertEquals(usuariosAntesDeCreacion.size() + 1, usuariosDespuesDeCreacion.size());
        Assertions.assertTrue(usuariosDespuesDeCreacion.stream().anyMatch(u -> u.getNombre().equals(nombre)));
    }

    @Given("existe un usuario con DNI {string}")
    public void existe_un_usuario_con_dni(String dniExistente) {
        Usuario usuarioExistente = new Usuario(1, "Usuario Existente", dniExistente, "existente@ejemplo.com", "clave", "Otro", new Date());
        gestionUsuarios.crearUsuario(usuarioExistente);
    }

    @Then("el sistema debería mostrar un error indicando que el DNI ya existe")
    public void el_sistema_deberia_mostrar_un_error_indicando_que_el_dni_ya_existe() {
        List<Usuario> usuariosDespuesDeCreacion = gestionUsuarios.getUsuarios();
        Assertions.assertEquals(usuariosAntesDeCreacion.size(), usuariosDespuesDeCreacion.size());
    }

    @Then("el usuario {string} no debería ser creado")
    public void el_usuario_no_deberia_ser_creado(String nombre) {
        List<Usuario> usuariosDespuesDeCreacion = gestionUsuarios.getUsuarios();
        Assertions.assertFalse(usuariosDespuesDeCreacion.stream().anyMatch(u -> u.getNombre().equals(nombre)));
    }
}