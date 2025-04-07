@web
Feature: Selección de fecha en un campo de formulario

  @ingresoFecha
  Scenario Outline: El usuario selecciona una fecha dentro del rango permitido
    Given el cliente omar ingresa al navegador
    When el usuario ingresa el mes <month> y el año <age> en el campo de fecha
    Then el sistema debería mostrar la fecha seleccionada

    Examples:
      | month | age  |
      | Apr | 2023 |

