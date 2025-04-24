Feature: Gestión de Usuarios - Creación

  Scenario: Crear un nuevo usuario exitosamente
    Given el sistema de gestión de usuarios está inicializado
    When se intenta crear un usuario con nombre "Elena", DNI "12345678A", email "elena@ejemplo.com", departamento "IT"
    Then el usuario "Elena" debería ser creado exitosamente

  Scenario: Intentar crear un usuario con DNI duplicado
    Given el sistema de gestión de usuarios está inicializado
    And existe un usuario con DNI "98765432B"
    When se intenta crear un usuario con nombre "Javier", DNI "98765432B", email "javier@ejemplo.net", departamento "RRHH"
    Then el sistema debería mostrar un error indicando que el DNI ya existe
    And el usuario "Javier" no debería ser creado