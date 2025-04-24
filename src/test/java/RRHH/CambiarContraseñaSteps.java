package RRHH;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CambiarContraseñaSteps {

    private GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private Usuario usuarioEncontrado;
    private String mensajeError;

    @DataTableType
    public Usuario usuarioEntry(Map<String, String> entry) {
        return new Usuario(
                Integer.parseInt(entry.get("userID")),
                entry.get("Nombre"),
                entry.get("DNI"),
                entry.get("Email"),
                entry.get("Password"), // Usamos la contraseña plana para simplificar la prueba
                entry.get("Departamento"),
                new Date()
        );
    }

    @Given("el sistema de gestión de usuarios está inicializado con el siguiente usuario:")
    public void el_sistema_de_gestion_de_usuarios_esta_inicializado_con_el_siguiente_usuario(List<Usuario> usuarios) {
        gestionUsuarios = new GestionUsuarios();
        for (Usuario usuario : usuarios) {
            gestionUsuarios.crearUsuario(usuario);
        }
    }

    @When("se solicita cambiar la contraseña del usuario con ID {int} a {string}")
    public void se_solicita_cambiar_la_contraseña_del_usuario_con_ID_a(int userID, String nuevaPassword) {
        boolean cambioExitoso = gestionUsuarios.cambiarContraseña(userID, nuevaPassword);
        if (!cambioExitoso) {
            mensajeError = "Error: No se encontró usuario con ID " + userID + " para cambiar la contraseña.";
        } else {
            usuarioEncontrado = gestionUsuarios.obtenerUsuarioPorId(userID);
        }
    }

    @Then("la contraseña del usuario con ID {int} debería ser {string}")
    public void la_contraseña_del_usuario_con_ID_debería_ser(int userID, String nuevaPassword) {
        Usuario usuario = gestionUsuarios.obtenerUsuarioPorId(userID);
        Assertions.assertEquals(nuevaPassword, usuario.getPasswordHash());
    }

    @Then("el sistema debería indicar que el usuario con ID {int} no existe")
    public void el_sistema_debería_indicar_que_el_usuario_con_ID_no_existe(int userID) {
        Assertions.assertEquals("Error: No se encontró usuario con ID " + userID + " para cambiar la contraseña.", mensajeError);
    }

    // ... (otros métodos de GestionUsuariosSteps) ...
}