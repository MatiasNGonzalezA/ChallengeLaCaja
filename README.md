
Guía para Ejecutar y Probar la Aplicación de Gestión de Personas


1. Acceso a la API de Postman:
   Para facilitar las pruebas, puedes acceder a ella importando el siguiente enlace en el postman:
   https://api.postman.com/collections/15035035-430dbae3-a7d3-47e6-b433-4c10793b5b65?access_key=PMAT-01GWAK8Y6V393MY6FZ231XZT0G


2. Procesar una Persona:
Método: [POST]
URL: localhost:8080/api/procesar
Acción: Dentro del cuerpo (Body) en Postman, inserta los datos de la persona en formato JSON.


3. Obtener Lista de Personas Procesadas:
Método: [GET]
URL: localhost:8080/api/personas

4. Obtener una Persona por su ID:
Método: [GET]
URL: localhost:8080/api/personas/{id} (reemplaza {id} con el número específico)

5. Actualizar una Persona:
Método: [PATCH]
URL: localhost:8080/api/personas/editar/{id} (reemplaza {id} con el número específico)
Acción: Dentro del cuerpo (Body) en Postman, inserta los datos actualizados de la persona en formato JSON.


6. Eliminar una Persona por su ID:
Método: [DELETE]
URL: localhost:8080/api/personas/eliminar/{id} (reemplaza {id} con el número específico)

7. Obtener Estadísticas de Edades de Personas Procesadas:
Método: [GET]
URL: localhost:8080/api/estadisticas

Nota: Asegúrate de tener la aplicación en ejecución antes de realizar estas acciones.

¡Eso es todo! Ahora podes explorar y probar las diferentes funciones de la aplicación de gestión de personas. Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en consultarme. ¡Gracias por su atención!