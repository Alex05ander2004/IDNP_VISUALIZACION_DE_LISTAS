# Proyecto: Lista de Cursos en Jetpack Compose

Este proyecto implementa una interfaz con campos de texto, botones y una lista dinámica utilizando Jetpack Compose.  
Permite modificar el nombre de un curso ingresando su ID y el nuevo nombre, actualizando automáticamente la lista en pantalla sin necesidad de recargar ni realizar llamadas adicionales.

---

## Estructura de la interfaz

La interfaz está compuesta por:

1. Dos `OutlinedTextField`  
   - Uno para el ID del curso.  
   - Otro para el nuevo nombre del curso.

2. Dos botones  
   - **Modificar**: actualiza el nombre del curso correspondiente al ID ingresado.  
   - **Ver lista**: reservado para una funcionalidad futura.

3. Una lista (`LazyColumn`)  
   - Muestra los cursos con su ID, nombre y descripción.  
   - Cada elemento se renderiza dentro de un componente `Card`.

Todos los elementos están dispuestos en una única columna (`Column`).

---

## Principio de funcionamiento: reactividad en Jetpack Compose

Jetpack Compose utiliza un modelo declarativo y reactivo.  
Esto significa que la interfaz se actualiza automáticamente cada vez que cambia un estado observado por el framework.

Para ello se utilizan estructuras de datos reactivas como:
- `mutableStateOf()`
- `mutableStateListOf()`
- `derivedStateOf()`

Cuando uno de estos valores cambia, Compose detecta la modificación y vuelve a ejecutar las funciones `@Composable` que dependen de ellos.

---

## Lista reactiva con `mutableStateListOf`

La lista de cursos se declara así:

```kotlin
val cursos = remember {
    mutableStateListOf<Curso>().apply {
        for (i in 1..10) {
            add(Curso(i, "Nombre $i", "Descripción del curso $i"))
        }
    }
}
