# Sistema de Gestión de Empresas y Empleados — Backend

API REST desarrollada con Spring Boot para la gestión de empresas y empleados, con una relación uno a muchos entre ambas entidades.

---

## Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.6 |
| Spring Data JPA | — |
| Hibernate | 7.2.12 |
| Base de datos | H2 (en memoria) |
| Maven | Wrapper incluido |

---

## Modelo entidad-relación

```
EMPRESA (1) ────────────── (N) EMPLEADO
```

**Empresa**

| Campo | Tipo | Restricción |
|---|---|---|
| id | Long | PK, autogenerado |
| nombre | String | Obligatorio |
| nit | String | Obligatorio |
| ciudad | String | Obligatorio |
| sector | String | Obligatorio |

**Empleado**

| Campo | Tipo | Restricción |
|---|---|---|
| id | Long | PK, autogenerado |
| nombre | String | Obligatorio |
| cargo | String | Obligatorio |
| correo | String | Obligatorio |
| empresa_id | Long | FK → Empresa.id |

---

## Ejecutar el proyecto

```bash
.\mvnw.cmd spring-boot:run
```

El servidor inicia en: `http://localhost:8080`

---

## Endpoints disponibles — Empresa

| Método | URL | Descripción |
|---|---|---|
| `GET` | `/api/empresas` | Listar todas las empresas |
| `GET` | `/api/empresas/{id}` | Consultar empresa por ID |
| `POST` | `/api/empresas` | Crear una empresa |
| `PUT` | `/api/empresas/{id}` | Actualizar una empresa |
| `DELETE` | `/api/empresas/{id}` | Eliminar una empresa |

### Ejemplo de cuerpo para POST / PUT

```json
{
  "nombre": "Tech SAS",
  "nit": "900123456-1",
  "ciudad": "Bogotá",
  "sector": "Tecnología"
}
```

### Respuesta exitosa

```json
{
  "id": 1,
  "nombre": "Tech SAS",
  "nit": "900123456-1",
  "ciudad": "Bogotá",
  "sector": "Tecnología"
}
```

---

## Consola H2 — Evidencia de base de datos

Accede a la consola de base de datos en: `http://localhost:8080/h2-console`

Credenciales de conexión:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:testdb` |
| User Name | `sa` |
| Password | *(vacío)* |

### Verificar tablas creadas

```sql
SHOW TABLES;
```

### Consultar relación uno a muchos

```sql
SELECT e.id, e.nombre AS empleado, e.cargo, e.correo,
       emp.nombre AS empresa, emp.ciudad
FROM EMPLEADO e
JOIN EMPRESA emp ON e.empresa_id = emp.id;
```

### Insertar datos de prueba

```sql
-- Insertar empresa
INSERT INTO EMPRESA (nombre, nit, ciudad, sector)
VALUES ('Tech SAS', '900123456-1', 'Bogotá', 'Tecnología');

-- Insertar empleado vinculado a la empresa
INSERT INTO EMPLEADO (nombre, cargo, correo, empresa_id)
VALUES ('Juan Pérez', 'Desarrollador', 'juan@tech.com', 1);
```

---

## Estructura del proyecto

```
src/
└── main/
    ├── java/com/backend/web/
    │   ├── WebApplication.java
    │   ├── controller/
    │   │   └── EmpresaController.java
    │   ├── service/
    │   │   └── EmpresaService.java
    │   ├── repository/
    │   │   ├── EmpresaRepository.java
    │   │   └── EmpleadoRepository.java
    │   └── model/
    │       ├── Empresa.java
    │       └── Empleado.java
    └── resources/
        └── application.properties
```

---

## Pruebas con Postman

### 1. Crear empresa

- **Método:** `POST`
- **URL:** `http://localhost:8080/api/empresas`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "nombre": "Tech SAS",
  "nit": "900123456-1",
  "ciudad": "Bogotá",
  "sector": "Tecnología"
}
```

### 2. Listar empresas

- **Método:** `GET`
- **URL:** `http://localhost:8080/api/empresas`

### 3. Consultar por ID

- **Método:** `GET`
- **URL:** `http://localhost:8080/api/empresas/1`

### 4. Actualizar empresa

- **Método:** `PUT`
- **URL:** `http://localhost:8080/api/empresas/1`
- **Body:**
```json
{
  "nombre": "Tech SAS Actualizada",
  "nit": "900123456-1",
  "ciudad": "Medellín",
  "sector": "Tecnología"
}
```

### 5. Eliminar empresa

- **Método:** `DELETE`
- **URL:** `http://localhost:8080/api/empresas/1`
- **Respuesta esperada:** `204 No Content`

---

## Configuración CORS

El backend acepta peticiones del frontend en `http://localhost:60571`.
