## Diseño y Arquitectura de Software

### Problemas Identificados

#### Clase `EmployeeOperations`

La interfaz `EmployeeOperations` viola el Principio de Responsabilidad Única (SRP) ya que incluye métodos con responsabilidades distintas:
- `calculateSalary`: Cálculo de salarios
- `saveToFile`: Persistencia de datos

Para cumplir con el SRP, se recomienda separar estas responsabilidades en interfaces distintas. Esto podría implicar:
- Una interfaz para operaciones relacionadas con agregar y eliminar empleados.
- Otra interfaz para la persistencia de datos
- Otra para el cálculo de salarios.

#### Clase `EmployeeManager`

La clase `EmployeeManager` hereda de la interfaz `EmployeeOperations`, y por lo tanto, arrastra los mismos problemas de SRP. Además, `EmployeeManager` implementa varias funcionalidades, incluyendo:
- Creación y gestión de empleados
- Cálculo de salarios
- Persistencia de datos

Estas responsabilidades deberían ser gestionadas por clases o interfaces separadas para adherirse al SRP.

#### Clase `ReportGenerator`

La clase `ReportGenerator` también presenta una violación del SRP, ya que maneja métodos para tipos diferentes de reportes. Para resolver esto, se ha implementado una solución que utiliza Twilio y Aldeamo, aumentando la flexibilidad y reduciendo el acoplamiento a terceros. Se debe asegurar que `ReportGenerator` dependa de abstracciones en lugar de implementaciones concretas.

#### Clase `CalculateSalary`

La clase `CalculateSalary` actualmente crea instancias de `EmployeeManager` directamente, lo cual va en contra del Principio de Inversión de Dependencias (DIP). Esta clase no debería manejar la creación de objetos ni otras tareas no directamente relacionadas con el cálculo de salarios. En lugar de ello, debe recibir una instancia de `EmployeeOperations` a través del constructor, permitiendo que funcione con cualquier implementación de `EmployeeOperations`.

#### Clase `Employee y PartTimeEmployee`

Para estas dos clases se identificó una violación al principio de sustitución de Liskov ya que este nos dice que no debe importar si cambiamos al padre por la que hereda debe tener el mismo funcionamiento, esto no pasa al momento ya que en la clase `partTimeOperations` apenas se llama al método "getName" se lanza un error. No se tenía muy claro cual podía ser la implementación necesaria para poder solventar esto, así que lo que hicimos fue volver `Employee` una interfaz y `partTimeOperations` una clase que ejecute los dos métodos de Employee

### Soluciones Propuestas

1. **Separar Interfaces**: Divide `EmployeeOperations` en interfaces con responsabilidades bien definidas:
   - Una para agregar y eliminar empleados.
   - Otra para persistencia de datos y cálculo de salarios.

2. **Reducir Acoplamiento**: Implementar el patrón de diseño de inyección de dependencias para permitir que las clases dependan de abstracciones.

3. **Flexibilidad en Reportes**: Utilizar servicios como Twilio y Aldeamo para generar reportes de manera más flexible y desacoplada.

4. **Dependencias de Abstracción**: Asegurarse de que `CalculateSalary` reciba una instancia de `EmployeeOperations` en lugar de crear instancias directamente, permitiendo la inyección de dependencias y promoviendo una arquitectura más flexible y mantenible.
