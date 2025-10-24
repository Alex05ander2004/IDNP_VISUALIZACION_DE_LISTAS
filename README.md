# Proyecto: Lista de Cursos en Jetpack Compose

## Descripción general
Esta aplicación muestra cómo crear una lista dinámica y reactiva en Jetpack Compose, donde se pueden modificar los datos de los elementos directamente desde campos de texto.

Flujo:
1. Se ingresa un **ID** y un **nuevo nombre**.
2. Se presiona el botón **Modificar**.
3. El nombre del curso correspondiente se actualiza automáticamente en la lista.

## Estructura de la interfaz
La pantalla principal está construida dentro de un único `Column`, y contiene:

1. Dos `OutlinedTextField`
   - Uno para ingresar el **ID** del curso.
   - Otro para ingresar el **nuevo nombre**.

2. Dos `Button`
   - **Modificar**: cambia el nombre del curso con el ID especificado.
   - **Ver lista**: placeholder para acciones futuras.

3. Una `LazyColumn`
   - Muestra los cursos con su **ID**, **nombre** y **descripción**.
   - Incluye scroll automático para manejar muchos elementos.

## Lógica del estado y actualización automática

### Lista observable
La lista se crea con `mutableStateListOf`, lo que permite que Compose detecte y reaccione a los cambios:

```kotlin
val cursos = remember {
    mutableStateListOf<Curso>().apply {
        for (i in 1..10) {
            add(Curso(i, "Nombre $i", "Descripción del curso $i"))
        }
    }
}
```

- `remember` conserva el estado mientras el composable esté activo.
- `mutableStateListOf()` genera una lista observable integrada con el sistema de recomposición de Compose.

### Modificación del elemento
Al presionar el botón **Modificar**, se actualiza el nombre del curso con el ID indicado:

```kotlin
Button(onClick = {
    val id = idInput.toIntOrNull()
    if (id != null && id in 1..cursos.size) {
        val index = id - 1
        cursos[index] = cursos[index].copy(nombre = nombreInput)
    }
}) {
    Text("Modificar")
}
```

Explicación:
- Se convierte el texto ingresado a entero (`idInput.toIntOrNull()`).
- Se busca el elemento con ese índice.
- Se reemplaza por una nueva instancia del objeto usando `copy()`.
- Al reemplazar la instancia, Compose detecta el cambio y vuelve a dibujar automáticamente los elementos afectados.

### Por qué se actualiza automáticamente
1. `mutableStateListOf` notifica a Compose cada vez que su contenido cambia.
2. Al usar `copy()`, se reemplaza el objeto anterior por una nueva referencia.
3. Compose detecta esa nueva referencia y recompone la parte de la interfaz que muestra la lista.
4. El cambio se refleja instantáneamente en pantalla, sin necesidad de refrescar la interfaz manualmente.

## LazyColumn
Ejemplo de uso:

```kotlin
LazyColumn {
    items(cursos) { curso ->
        Text(text = "ID: ${curso.id} - ${curso.nombre} - ${curso.descripcion}")
    }
}
```

- `LazyColumn` solo crea los elementos visibles y algunos adicionales en buffer.
- Esto optimiza el rendimiento en listas grandes.
- Si los datos cambian, Compose actualiza solo los elementos afectados.

## Buenas prácticas aplicadas
- Uso de inmutabilidad con `data class` y `copy()`.
- Estados recordados con `remember` y `mutableStateListOf`.
- Diseño vertical: toda la UI está dentro de un único `Column`.
- Reacción inmediata sin llamadas manuales de refresco.

## Explicación breve
La actualización automática ocurre porque la lista está definida con `mutableStateListOf`, que es observable por Compose. Cuando se reemplaza un elemento (mediante `copy()`), Compose detecta el cambio de referencia y vuelve a dibujar la parte de la UI que depende de esa lista. De esta forma, el cambio se refleja al instante sin necesidad de refrescar la interfaz manualmente.

## Ejecución
1. Crear un proyecto "Empty Compose Activity" en Android Studio.
2. Reemplazar el contenido de `MainActivity.kt` con el código de ejemplo.
3. Ejecutar en un emulador o dispositivo físico.
4. Probar modificando valores y observando la actualización automática.

## Autor
Nombre: Huisa Perez Willy Alexander
Curso: Desarrollo de Aplicaciones Móviles
Lenguaje: Kotlin + Jetpack Compose
Fecha: Octubre 2025
