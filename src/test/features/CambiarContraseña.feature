Feature: Gestión de Usuarios - Cambiar Contraseña

  Scenario: Cambiar la contraseña de un usuario existente
    Given el sistema de gestión de usuarios está inicializado con el siguiente usuario:
      | userID | Nombre  | DNI       | Email             | Password | Departamento |
      | 1      | Elena   | 11223344C | elena@ejemplo.com | antigua  | Marketing    |
    When se solicita cambiar la contraseña del usuario con ID 1 a "nueva"
    Then la contraseña del usuario con ID 1 debería ser "nueva"

  Scenario: Intentar cambiar la contraseña de un usuario no existente
    Given el sistema de gestión de usuarios está inicializado con el siguiente usuario:
      | userID | Nombre  | DNI       | Email             | Password | Departamento |
      | 1      | Elena   | 11223344C | elena@ejemplo.com | antigua  | Marketing    |
    When se solicita cambiar la contraseña del usuario con ID 2 a "nueva"
    Then el sistema debería indicar que el usuario con ID 2 no existe