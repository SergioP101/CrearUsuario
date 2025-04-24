package RRHH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestionUsuarios {
    private List<Usuario> usuarios;
    private String ultimoError;

    public GestionUsuarios() {
        this.usuarios = new ArrayList<>();
        this.ultimoError = null;
    }

    public void crearUsuario(Usuario nuevoUsuario) {
        if (usuarios.stream().noneMatch(u -> u.getDni().equals(nuevoUsuario.getDni()))) {
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario " + nuevoUsuario.getNombre() + " creado.");
        } else {
            System.out.println("Error: El usuario con DNI " + nuevoUsuario.getDni() + " ya existe.");
        }
    }

    public Usuario obtenerUsuarioPorId(int usuarioID) {
        return usuarios.stream()
                .filter(u -> u.getUserID() == usuarioID)
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(this.usuarios);
    }

    public void actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUserID() == usuarioActualizado.getUserID()) {
                usuarios.set(i, usuarioActualizado);
                System.out.println("Usuario con ID " + usuarioActualizado.getUserID() + " actualizado.");
                return;
            }
        }
        System.out.println("Error: No se encontró usuario con ID " + usuarioActualizado.getUserID() + " para actualizar.");
    }

    public void eliminarUsuario(int usuarioID) {
        boolean removed = usuarios.removeIf(usuario -> usuario.getUserID() == usuarioID);
        if (removed) {
            System.out.println("Usuario con ID " + usuarioID + " eliminado.");
        } else {
            System.out.println("Error: No se encontró usuario con ID " + usuarioID + " para eliminar.");
        }
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public boolean cambiarContraseña(int userID, String nuevaPassword) {
        Usuario usuario = obtenerUsuarioPorId(userID);
        if (usuario != null) {
            usuario.cambiarContraseña(nuevaPassword);
            this.ultimoError = null;
            return true;
        } else {
            this.ultimoError = "Error: No se encontró usuario con ID " + userID + " para cambiar la contraseña.";
            System.out.println(this.ultimoError);
            return false;
        }
    }
        public String getUltimoError() {
            return ultimoError;
        }


}