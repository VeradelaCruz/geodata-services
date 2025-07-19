# 🤝 Guía de Contribución

Gracias por tu interés en contribuir a **GeoData Services** 🌍  
Este proyecto busca ser una solución completa para la gestión de estudios geológicos, muestras y geólogos. ¡Toda ayuda es bienvenida!

---

## 🧪 1. Clonar y ejecutar el proyecto localmente

Antes de comenzar a contribuir, es importante que puedas probar y ejecutar el proyecto en tu entorno local.

### Requisitos

- Java 17+
- Maven o Gradle
- Docker (opcional, para servicios externos como bases de datos)
- IDE recomendado: IntelliJ IDEA

### Pasos

```bash
# Clonar el repositorio
git clone https://github.com/tuUsuario/geodata-services.git
```

# Ingresar al directorio raíz
```bash
cd geodata-services
```
# Ejecutar los microservicios de forma individual (por ejemplo, geologist-service)
```bash
cd geologist-service
./mvnw spring-boot:run
```
También puedes ejecutar los servicios desde tu IDE.

---

✨ 2. Estándares de código
-------------------------

Para mantener la coherencia y calidad del código:

* Usa nombres de clases, métodos y variables descriptivos.  
* Sigue las convenciones de estilo de Java.  
* Añade javadoc en clases públicas y métodos importantes.  
* Mantén los controladores ligeros: la lógica debe estar en los servicios.  
* Si modificás algo, ¡probalo antes de subirlo!

---

🐛 3. Crear Issues y Pull Requests
----------------------------------

### ¿Detectaste un bug o querés proponer una mejora?

1. Abre un nuevo **issue** describiendo el problema o sugerencia claramente.  
2. Usa etiquetas como `bug`, `enhancement`
3. Si vas a trabajar en una solución, comentá en el issue para que lo asignemos.

### ¿Querés subir código?

1. Haz un fork del proyecto y trabajá en una rama con nombre descriptivo:
```bash
git checkout -b fix-geologist-validation
```
1. Escribe un mensaje de commit claro y conciso.  
2. Abre un **pull request** enlazando el issue correspondiente (si lo hay).  
3. Asegurate de que tu PR pase las pruebas automáticas (si están configuradas).

---

🧾 4. Documentación de nuevas funcionalidades
---------------------------------------------

Si agregás una funcionalidad:

* Documentá cómo se usa en el `README.md` o creá un archivo adicional si es más extenso.  
* Si agregaste una nueva ruta REST, especificá:

    * Método HTTP (`GET`, `POST`, etc.)  
    * URL  
    * Parámetros esperados  
    * Ejemplo de respuesta

Ejemplo:
```http
GET /study/location?city=San Juan

Respuesta:
[
  {
    "id": 1,
    "name": "Estudio de sedimentos",
    "location": "San Juan",
    "startDate": "2025-06-01"
  }
]
```

🙌 Gracias
Tu participación mejora este proyecto para toda la comunidad.
¡Esperamos tu contribución!



