\# 🌍 Geodata Service - Sistema de Gestión de Estudios Geológicos 🪨



> Aplicación RESTful modular para gestionar estudios geológicos, muestras y geólogos, ideal para empresas de exploración o laboratorios.



---



\## 🎯 Objetivo



Crear una plataforma modular que permita:



\- Registrar y administrar \*\*estudios geológicos\*\*  

\- Gestionar las \*\*muestras\*\* de suelo y roca recolectadas  

\- Controlar la información y participación de los \*\*geólogos\*\* involucrados



---



\## 📁 Estructura del Proyecto



El proyecto está organizado en módulos bajo la carpeta raíz `geodata-services`:



\- `geologist/` – Servicio para gestionar geólogos  

\- `sample/` – Servicio para gestionar muestras  

\- `study/` – Servicio para gestionar estudios geológicos  



Cada módulo funciona como un microservicio Spring Boot independiente.



Además, cuenta con servicios de infraestructura:



\- `config-server/` – Configuración centralizada  

\- `eureka-server/` – Descubrimiento de servicios  

\- `api-gateway/` – Punto de entrada unificado  



---



\## ⚙️ Tecnologías usadas



\- Java 11+  

\- Spring Boot  

\- Spring Cloud (Eureka, Config Server, API Gateway)  

\- Maven  

\- RESTful APIs  

\- Git / GitHub  



---



\## 🚀 Instalación y uso



1\. Clona este repositorio:



&nbsp;  ```bash

&nbsp;  git clone https://github.com/tuUsuario/geodata-services.git

&nbsp;  cd geodata-services```

&nbsp;	

