En esta branch se implementara las funcionalidades de la HU9

**Objetivo:** Asignar a cada estudiante registrado en una clase una nota en una respectiva materia.

**Criterios de aceptación: **

- Dado que soy un Maestro.
- Cuando quiero agregar una nota de mi materia a una clase.
- Entonces selecciono un grupo de la lista de grupos registrados en cada materia.
- Entonces puedo agregar una nueva nota a la clase en mi materia haciendo click en "Agregar nota".
- Entonces ingreso el Nombre de la nota (Descripción Eje: Examen 1, Taller Emprendimiento, etc), La fecha y el Valor de la nota para cada uno de los estudiantes.
- Finalmente cuando ingrese las notas de todos los estudiantes inscritos en la clase, estas notas aparecen en la información de la cada grupo.

**Detalles:**
- Cada estudiante se le asigna una Lista de notas ( Nombre de la Nota, Nombre Materia, Fecha, Puntaje).
- Cada maestro podrá visualizar sólo las notas de sus propias materias.
- El maestro puede visualizar las notas de todas las materias cuando es el director de grupo.
- Las notas se manejan del 1 al 5 en decimal.

- Nombre de la nota: String