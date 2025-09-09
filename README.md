# desarrolloWeb# soberbio
-------------------------------------------------------------------------------
# Diagrama de navegacion
```mermaid
flowchart LR
  L[inicio.html]
  N[nosotros.html]
  A[awards.html]
  C[contacto.html]
  P[prensa.html]
  G[gallery.html]
  M[menu.html]
  R[reservar.html]

  L --> N
  L --> A
  L --> C
  L --> P
  L --> G
  L --> M
  L --> R

  M --> R
  G --> R

  N -.-> L
  A -.-> L
  C -.-> L
  P -.-> L
  G -.-> L
  M -.-> L
  R -.-> L
```
  -------------------------------------------------------------------------------
# UML
```mermaid
classDiagram
  class Cliente {
    -id : Long
    -nombre : String
    -apellido : String
    -correo : String
    -contraseña : String
    -teléfono : String
    -dirección : String
    -registradoEn : DateTime
    
    +registrar(nombre:String, apellido:String, correo:String, contraseña:String, teléfono:String, dirección:String)
    +login(correo:String, contraseña:String)
    +actualizarPerfil(teléfono:String, dirección:String)
    +realizarPedido(p:Pedido)
  }

  class Operador {
    -id : Long
    -nombre : String
    -usuario : String
    -contraseña : String
    
    +login(usuario:String, contraseña:String)
    +cambiarEstadoPedido(pedido:Pedido, estado:EstadoPedido)
    +asignarDomiciliario(pedido:Pedido, d:Domiciliario)
  }

  class Domiciliario {
    -id : Long
    -nombre : String
    -cédula : String
    -celular : String
    -disponible : Boolean
    
    +marcarDisponible()
    +marcarOcupado()
  }

  class Producto {
    -id : Long
    -nombre : String
    -precio : BigDecimal
    -descripción : String
    -imagenUrl : String
    -activo : Boolean
    
    +consultarDetalle()
  }

  class Adicional {
    -id : Long
    -nombre : String
    -precio : BigDecimal
    -activo : Boolean
    
    +consultar()
  }

  class Pedido {
    -id : Long
    -fechaCreación : DateTime
    -fechaEntrega : DateTime
    -direcciónEntrega : String
    -total : BigDecimal
    -estado : String
    
    +calcularTotal()
    +marcarRecibido()
    +marcarCocinando()
    +marcarEnviado(d:Domiciliario)
    +marcarEntregado()
  }

  class ItemPedido {
    -id : Long
    -cantidad : int
    -precioUnitario : BigDecimal
    
    +subtotal() : BigDecimal
  }

  class ItemPedidoAdicional {
    -id : Long
  }

  class CarritoLocal {
    -uuid : String
    -createdAt : DateTime
    
    +addItem(p:Producto, cantidad:int)
    +removeItem(p:Producto)
    +clear()
  }

  Cliente "1" --> "*" Pedido : realiza
  Pedido "1" --> "*" ItemPedido : tiene
  Producto "1" --> "*" ItemPedido
  ItemPedido "1" --> "*" ItemPedidoAdicional : tiene
  Adicional "1" --> "*" ItemPedidoAdicional
  Domiciliario "0..1" --> "*" Pedido : tiene
  Operador "1" --> "*" Pedido
  Cliente "1" --> "0..1" CarritoLocal
```
  -------------------------------------------------------------------------------
# ERD
```mermaid
erDiagram
  CLIENTE {
    BIGINT id PK
    VARCHAR nombre
    VARCHAR apellido
    VARCHAR correo UK
    VARCHAR contrasena_hash
    VARCHAR telefono
    VARCHAR direccion
    TIMESTAMP registrado_en
  }

  OPERADOR {
    BIGINT id PK
    VARCHAR nombre
    VARCHAR usuario UK
    VARCHAR contrasena_hash
  }

  DOMICILIARIO {
    BIGINT id PK
    VARCHAR nombre
    VARCHAR cedula UK
    VARCHAR celular
    BOOLEAN disponible
  }

  PRODUCTO {
    BIGINT id PK
    VARCHAR nombre
    DECIMAL precio
    VARCHAR descripcion
    VARCHAR imagen_url
    BOOLEAN activo
  }

  ADICIONAL {
    BIGINT id PK
    VARCHAR nombre
    DECIMAL precio
    BOOLEAN activo
  }

  PEDIDO {
    BIGINT id PK
    BIGINT cliente_id FK
    BIGINT domiciliario_id FK
    VARCHAR estado
    TIMESTAMP fecha_creacion
    TIMESTAMP fecha_entrega
    VARCHAR direccion_entrega
    DECIMAL total
  }

  ITEM_PEDIDO {
    BIGINT id PK
    BIGINT pedido_id FK
    BIGINT producto_id FK
    INT cantidad
    DECIMAL precio_unitario
  }

  ITEM_PEDIDO_ADICIONAL {
    BIGINT id PK
    BIGINT item_pedido_id FK
    BIGINT adicional_id FK
  }

  CLIENTE ||--o{ PEDIDO : realiza
  PEDIDO ||--o{ ITEM_PEDIDO : contiene
  PRODUCTO ||--o{ ITEM_PEDIDO : referencia
  ITEM_PEDIDO ||--o{ ITEM_PEDIDO_ADICIONAL : tiene
  ADICIONAL ||--o{ ITEM_PEDIDO_ADICIONAL : adicional
  DOMICILIARIO o|--o{ PEDIDO : asignado
```
  -------------------------------------------------------------------------------
## 🎨 Paleta de colores
Basando en la pagina web del restaurantr [El Chato](https://elchato.co) 

### Brand color
- `#FFF` → **texto (Blanco)**

### Supporting colors
- `#19191B` → **fondo (gris)**
- `#212529` → **texto (gris claro)**

### Ocacional
- `#ffc107` → **imagenes (amarillo opaco)**
