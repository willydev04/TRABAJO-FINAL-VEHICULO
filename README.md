# Control de Flota de Vehículos

Proyecto en Java con programación orientada a objetos. Gestiona una flota de vehículos desde un menú en consola.

## Estructura

```
control-flota-vehiculos/
├── src/
│   ├── Vehiculo.java       # Clase del objeto
│   └── GestionFlota.java   # Clase administradora (main + menú)
├── .gitignore
└── README.md
```

## Clase `Vehiculo`

| Atributo (privado) | Tipo | Descripción |
|---|---|---|
| `placa` | String | 6 caracteres alfanuméricos |
| `combustible` | double | Litros disponibles (0 a 50) |
| `rendimiento` | double | Kilómetros por litro (> 0) |
| `kilometraje` | double | Kilómetros acumulados (>= 0) |

- **Constructor por defecto:** `"AAA000"`, 20, 40, 0
- **Constructor con parámetros:** placa, combustible, rendimiento, kilometraje
- **4 getters y 4 setters** con validaciones (lanzan `IllegalArgumentException` si el dato es inválido)
- **Método 1 – `recorrer(double km)`:** calcula el combustible requerido (`km / rendimiento`), verifica que haya suficiente y actualiza combustible y kilometraje.
- **Método 2 – `tanquear(double litros)`:** valida que los litros sean positivos y que no se supere la capacidad máxima de 50 L.

## Clase `GestionFlota`

Contiene `main()`, usa `Scanner` y un `switch` para el menú, y guarda los vehículos en un `ArrayList<Vehiculo>`.

```
1. Crear objeto por defecto
2. Crear objeto con parámetros
3. Modificar atributos del objeto
4. Visualizar objetos registrados
5. Ejecutar método de comportamiento 1 (recorrer)
6. Ejecutar método de comportamiento 2 (tanquear)
7. Salir
```

El menú se repite hasta elegir **7. Salir**. En las opciones 3, 5 y 6 se pide la posición del objeto y se verifica que exista antes de operar.

## Cómo compilar y ejecutar

Requiere JDK 8 o superior.

```bash
# Compilar
javac -encoding UTF-8 -d out src/*.java

# Ejecutar
java -cp out GestionFlota
```


## Autor

WILMAR SAID CUERVO RUEDA -
SAMUEL ROSERO POSADA
