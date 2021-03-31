# Proyecto 5 Hibernate
### *Javier Moreno Cidoncha*

------------


##### Se han creado las entidades:
- TagColor
- Tag
- User
- Task
- BillingIngo

##### Se han generado las siguientes asociaciones:

- Un usuario tiene muchas tareas, una tarea solo un usuario.
- Una tarea puede tener muchas etiquetas, una etiqueta puede estar en mas de una tarea a la vez.
- Un usuario tiene una informacion de bacturacion y una informacion de facturacion solo puede pertenecer a un mismo usuario.

##### Se han generado los siguientes desarrollos:
- Crear entidades de cada tipo GET, POST, PUT utilizando EntityManager y desde clases DAO .
  Se han creado entidades:
  1. crear
  2. modificar
  3. buscar todas
  4. buscar una por id
  6. buscar todas por nombre

`No se han creado entidades POST y PUT a traves de repositorios`

- Los controladores llaman a capa de servicio y el servicio llama a los DAOs.
  `No se llama a ningun metodo de repositoio
  `
- Se han realizado pruebas para cada entidad satisfactoriamente y se ha exportado la coleccion postman a la carpeta raiz del proyecto, y hay un archivo data.sql con inserciones de ejemplo.
