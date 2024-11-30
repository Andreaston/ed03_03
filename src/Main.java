import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> productos = new ArrayList<>();
        List<Double> precios = new ArrayList<>();

        // Añadir productos y precios
        productos.add("Laptop");
        precios.add(1200.0);

        productos.add("Telefono");
        precios.add(600.0);

        productos.add("Auriculares");
        precios.add(150.0);

        // Intentar calcular el total de la compra
        double totalCompra = calcularTotalCompra(productos, precios, new int[]{1, 2, 1});

        // el total de la compra debería ser (1200 + 2*600 + 150)*0.85*1.21 es decir, 2622.675 (redondeamos al centimo, es decir, 2622.68)
        // Subtotal: 2550
        // Descuento: 2550*0.15 = 382.50
        // Impuestos (Tras descuento) (2550-382.5)*0.21 = 455.17
        // Total tras impuestos = 2550-382.5+455.175 = 2622.675
        System.out.println("Total de la compra con impuestos y descuento: $" + totalCompra);
    }

    // Calcula el total de una compra en base a los productos y cantidades
    public static double calcularTotalCompra(List<String> productos, List<Double> precios, int[] cantidades) {
        double subtotal = calcularSubtotal(productos, precios, cantidades);
        double descuento = aplicarDescuento(subtotal);
        //double totalConDescuento = subtotal - descuento;

        // Eliminanos el calculo totalConDescuento poruqe ya calculamos eso en la función anterior así que no nos hace ya falta
        double totalConImpuestos = calcularImpuestos(descuento);
        return totalConImpuestos;
    }

    // Calcula el subtotal de la compra
    // El array daba una vuelta más por lo que hacia que no coincidiese con nada en la lista, rompiendo el código.
        //Para solucionarlo tendenemos que hacer que recorra una vuelta de menos
    public static double calcularSubtotal(List<String> productos, List<Double> precios, int[] cantidades) {
        double subtotal = 0;
        for (int i = 0; i <= productos.size() - 1; i++) {

             subtotal += precios.get(i) * cantidades[i];

       }
        System.out.println("Subtotal: $" + subtotal);
        return subtotal;
    }

    // Aplica un descuento de acuerdo al subtotal
    // Aplica un 15% para compras de más de 1000 euros
    // Aplica un 10% para compras entre 500 y 1000 euros
    // No aplica ningún descuento si la compra es de menos de 500 euros
    public static double aplicarDescuento(double subtotal) {

        //Asignamos el subtotal a una variable auxiliar
        Double calculoDescuento = subtotal;

        if (subtotal > 1000) {
            //Guardamos el resultado de la operación en la variable auxiliar
            calculoDescuento = subtotal * 0.15;
            //Completamos la operación para realizar el descuento
            subtotal = subtotal - calculoDescuento;
            return subtotal; // Descuento del 15%
        } else if (subtotal > 500) {
            //Guardamos el resultado de la operación en la variable auxiliar
            calculoDescuento = subtotal * 0.10;
            //Completamos la operación para realizar el descuento
            subtotal = subtotal - calculoDescuento;
            return subtotal; // Descuento del 10%
        }
        // Error: sin descuento
        return 0.0;
    }

    // Calcula los impuestos aplicados al total con descuento
    public static double calcularImpuestos(double total) {
        final double IMPUESTO = 0.21; // Impuesto del 21%
        //Ponemos una variable auxiliar para el cálculo
        double calculoImpuestos = total * IMPUESTO;
        return total = total + calculoImpuestos; // Debe devolver total + impuestos
    }
}
