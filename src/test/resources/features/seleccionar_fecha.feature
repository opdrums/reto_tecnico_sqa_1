@web
Feature: Selección de fecha en un campo de formulario
  Background:
    Given que el usuario está en la página web CO

  @fecha
  Scenario: El usuario selecciona una fecha dentro del rango permitido
    Given el usuario hace clic en el campo de fecha
    When selecciona una fecha dentro del rango 20
    Then la fecha seleccionada debe aparecer correctamente en el campo de entrada

  @rangoMesAño
  Scenario:  El usuario selecciona una fecha y mes dentro del rango permitido
    Given el usuario seleccionar una caracteristica de la lista Display month & year menus
    When el usuario hace clic en el campo de fecha
    And Selecciona el mes 5 y año 2020
    And selecciona una fecha dentro del rango 20
    Then la fecha seleccionada debe aparecer correctamente en el campo de entrada
