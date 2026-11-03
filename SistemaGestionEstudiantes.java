import java.util.ArrayList;
import java.util.Scanner;
 
public class SistemaGestionEstudiantes {
 
    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static Scanner teclado = new Scanner(System.in);
 
    // Lleva la cantidad de estudiantes que se han registrado.
    private static int contadorEstudiantes = 0;
 
    // Controla si el programa debe terminar.
    private static boolean banderaSalir = false;
 
    public static void main(String[] args) {
        while (!banderaSalir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opcion: ");
 
            switch (opcion) {
                case 1:
                    agregarEstudiante();
                    break;
                case 2:
                    mostrarEstudiantes();
                    break;
                case 3:
                    buscarEstudiante();
                    break;
                case 4:
                    banderaSalir = true;
                    System.out.println("Gracias por utilizar el sistema.");
                    conteoRegresivo();
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
        teclado.close();
    }
 
    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTION DE ESTUDIANTES =====");
        System.out.println("1. Agregar estudiante");
        System.out.println("2. Mostrar estudiantes");
        System.out.println("3. Buscar estudiante por ID");
        System.out.println("4. Salir");
    }
 
    private static void agregarEstudiante() {
        System.out.println("\n--- AGREGAR ESTUDIANTE ---");
        int id = leerEnteroPositivo("Ingrese el ID: ");
 
        // Primera decision: revisar si el ID ya esta registrado.
        if (buscarPorId(id) == null) {
            System.out.print("Ingrese el nombre: ");
            String nombre = teclado.nextLine().trim();
 
            // Segunda decision dentro de la primera: validar el nombre.
            if (!nombre.isEmpty()) {
                int edad = leerEnteroPositivo("Ingrese la edad: ");
 
                Estudiante nuevo = new Estudiante(id, nombre, edad);
                estudiantes.add(nuevo);
                contadorEstudiantes++;
                System.out.println("Estudiante agregado correctamente.");
            } else {
                System.out.println("El nombre no puede estar vacio.");
            }
        } else {
            System.out.println("Ese ID ya se encuentra registrado.");
        }
    }
 
    private static void mostrarEstudiantes() {
        System.out.println("\n--- ESTUDIANTES REGISTRADOS ---");
 
        if (contadorEstudiantes == 0) {
            System.out.println("Todavia no hay estudiantes registrados.");
        } else {
            // El contador i aumenta: recorrido ascendente.
            for (int i = 0; i < estudiantes.size(); i++) {
                mostrarEstudiante(estudiantes.get(i));
            }
            System.out.println("Total de estudiantes: " + contadorEstudiantes);
        }
    }
 
    private static void buscarEstudiante() {
        System.out.println("\n--- BUSCAR POR ID ---");
        int id = leerEnteroPositivo("Ingrese el ID que desea buscar: ");
        Estudiante resultado = buscarPorId(id);
 
        if (resultado != null) {
            System.out.println("Estudiante encontrado:");
            mostrarEstudiante(resultado);
        } else {
            System.out.println("No existe un estudiante con ese ID.");
        }
    }
 
    private static Estudiante buscarPorId(int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }
 
    private static void mostrarEstudiante(Estudiante estudiante) {
        System.out.println("ID: " + estudiante.getId()
                + " | Nombre: " + estudiante.getNombre()
                + " | Edad: " + estudiante.getEdad());
    }
 
    // Repite la solicitud hasta que se escriba un numero entero.
    private static int leerEntero(String mensaje) {
        int numero = 0;
        boolean datoValido = false;
 
        do {
            System.out.print(mensaje);
            String entrada = teclado.nextLine();
 
            try {
                numero = Integer.parseInt(entrada.trim());
                datoValido = true;
            } catch (NumberFormatException error) {
                System.out.println("Debe ingresar un numero entero.");
            }
        } while (!datoValido);
 
        return numero;
    }
 
    // Repite la solicitud si el numero no es positivo.
    private static int leerEnteroPositivo(String mensaje) {
        int numero;
        do {
            numero = leerEntero(mensaje);
            if (numero <= 0) {
                System.out.println("El numero debe ser mayor que cero.");
            }
        } while (numero <= 0);
        return numero;
    }
 
    // Ejemplo de iteracion descendente.
    private static void conteoRegresivo() {
        for (int i = 3; i >= 1; i--) {
            System.out.println("Cerrando en " + i);
        }
    }
 
    private static class Estudiante {
        private int id;
        private String nombre;
        private int edad;
 
        public Estudiante(int id, String nombre, int edad) {
            this.id = id;
            this.nombre = nombre;
            this.edad = edad;
        }
 
        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public int getEdad() { return edad; }
    }
}
