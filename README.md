# 🌍 Geodata Service - Sistema de Gestión de Estudios Geológicos 🪨

[![GitHub license](https://img.shields.io/github/license/VeradelaCruz/geodata-services)](https://github.com/VeradelaCruz/geodata-services/blob/main/LICENSE)
[![GitHub last commit](https://img.shields.io/github/last-commit/VeradelaCruz/geodata-services)](https://github.com/VeradelaCruz/geodata-services/commits)
[![GitHub issues](https://img.shields.io/github/issues/VeradelaCruz/geodata-services)](https://github.com/VeradelaCruz/geodata-services/issues)

> Aplicación RESTful modular para gestionar estudios geológicos, muestras y geólogos, ideal para empresas de exploración o laboratorios.

---

## 🎯 Objetivo

Crear una plataforma modular que permita:

- Registrar y administrar **estudios geológicos**
- Gestionar las **muestras** de suelo y roca recolectadas
- Controlar la información y participación de los **geólogos** involucrados

---

## ✅ Funcionalidades CRUD

Crear, leer, actualizar y eliminar:

- Geólogos
- Estudios geológicos
- Muestras

Otras funcionalidades:

- Buscar estudios por ubicación o por nombre del geólogo
- Listar todas las muestras de un estudio

---

## 📁 Estructura del Proyecto

El proyecto está organizado en módulos bajo la carpeta raíz `geodata-services`:

- `geologist-service/` – Servicio para gestionar geólogos  
- `sample-service/` – Servicio para gestionar muestras  
- `study-service/` – Servicio para gestionar estudios geológicos  

Cada módulo funciona como un microservicio Spring Boot independiente.

Además, cuenta con servicios de infraestructura:

- `config-server/` – Configuración centralizada  
- `eureka-service/` – Descubrimiento de servicios  
- `api-gateway/` – Punto de entrada unificado  

---

## ⚙️ Tecnologías usadas

- Java 11+  
- Spring Boot  
- Spring Cloud (Eureka, Config Server, API Gateway)  
- Maven  
- RESTful APIs  
- Git / GitHub  


---

## 🚀 Instalación y uso

1. Clona este repositorio:

```
git clone https://github.com/tuUsuario/geodata-services.git
cd geodata-services
```

2. Levanta los microservicios y servicios de infraestructura en terminales separadas, por ejemplo:

```
# Config Server
cd config-server
mvn spring-boot:run
```

```
# Eureka Service
cd ../eureka-service
mvn spring-boot:run
```

```
# API Gateway
cd ../api-gateway
mvn spring-boot:run
```

```
# Geologist service
cd ../geologist-service
mvn spring-boot:run
```

```
# Sample service
cd ../sample-service
mvn spring-boot:run
```

```
# Study service
cd ../study-service
mvn spring-boot:run
```




3. El API Gateway estará disponible en `http://localhost:8083`, desde donde podrás consumir las APIs.



---

\## 📝 Modifica este README



Si quieres mejorar la documentación o añadir ejemplos usando HTML, CSS o JavaScript, ¡estás más que invitado a hacerlo!



Este archivo `README.md` es el primer punto de contacto para colaboradores y usuarios, así que cualquier mejora, ya sea con texto, formato o código, será bienvenida.



Si prefieres, simplemente modifica el archivo directamente y envía un pull request.



---



\## 🤝 Cómo contribuir



¡Gracias por tu interés en contribuir a este proyecto! 🛠️  

Valoramos mucho el aporte de la comunidad y creemos que juntos podemos hacerlo más sólido, útil y profesional.



Antes de comenzar, te recomendamos leer nuestra \[Guía de Contribución](CONTRIBUTING.md).  

Allí encontrarás:



\- Cómo clonar y ejecutar el proyecto localmente.

\- Estándares de código y buenas prácticas.

\- Cómo crear issues y enviar pull requests.

\- Recomendaciones para documentar nuevas funcionalidades.



No importa si eres principiante o experto, ¡toda mejora suma! Ya sea corrigiendo errores, mejorando la documentación, agregando funcionalidades o simplemente haciendo sugerencias.



---



\## 📄 Licencia



Este proyecto está licenciado bajo la \*\*MIT License\*\*. Consulta el archivo \[LICENSE](LICENSE.txt) para más detalles.



---



\## 📫 Contacto



Para preguntas o sugerencias, abre un issue o contáctame vía GitHub.



---



¡Gracias por interesarte en Geodata Service! 🎉

 

