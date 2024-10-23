import java.util.LinkedList
import java.util.Queue
import java.util.Stack

// Clase que representa una Pupusa
data class Pupusa(val tipo: String, val cantidad: Int)

// Clase que representa una Orden
data class Orden(val cliente: String, val pupusas: List<Pupusa>)

// Clase que gestiona las órdenes de la Pupusería
class Pupuseria {
    private val ordenesPendientes: Queue<Orden> = LinkedList()
    private val ordenesDespachadas: Stack<Orden> = Stack()

    // Registrar una nueva orden
    fun registrarOrden() {
        println("Ingrese el nombre del cliente:")
        val cliente = readLine() ?: return

        println("¿Cuántos tipos de pupusas desea ordenar?")
        val tipos = readLine()?.toIntOrNull() ?: return
        val pupusas = mutableListOf<Pupusa>()

        for (i in 1..tipos) {
            println("Ingrese el tipo de pupusa #$i:")
            val tipoPupusa = readLine() ?: return

            println("Ingrese la cantidad de pupusas de $tipoPupusa:")
            val cantidad = readLine()?.toIntOrNull() ?: return

            pupusas.add(Pupusa(tipoPupusa, cantidad))
        }

        val nuevaOrden = Orden(cliente, pupusas)
        ordenesPendientes.add(nuevaOrden)
        println("Orden registrada para $cliente: ${pupusas.joinToString(", ") { "${it.cantidad} pupusas de ${it.tipo}" }}.")
    }

    // Ver órdenes pendientes
    fun verOrdenesPendientes() {
        if (ordenesPendientes.isEmpty()) {
            println("No hay órdenes pendientes.")
        } else {
            println("Órdenes pendientes:")
            ordenesPendientes.forEachIndexed { index, orden ->
                println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString(", ") { "${it.cantidad} pupusas de ${it.tipo}" }}")
            }
        }
    }

    // Despachar la primera orden
    fun despacharOrden() {
        if (ordenesPendientes.isEmpty()) {
            println("No hay órdenes pendientes para despachar.")
        } else {
            val ordenDespachada = ordenesPendientes.poll()
            ordenesDespachadas.push(ordenDespachada)
            println("Despachando la orden de: ${ordenDespachada.cliente}: ${ordenDespachada.pupusas.joinToString(", ") { "${it.cantidad} pupusas de ${it.tipo}" }}.")
        }
    }

    // Ver órdenes despachadas
    fun verOrdenesDespachadas() {
        if (ordenesDespachadas.isEmpty()) {
            println("No hay órdenes despachadas.")
        } else {
            println("Órdenes despachadas:")
            ordenesDespachadas.forEachIndexed { index, orden ->
                println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString(", ") { "${it.cantidad} pupusas de ${it.tipo}" }}")
            }
        }
    }

    // Menú principal
    fun mostrarMenu() {
        while (true) {
            println("""
                |Bienvenido a la Pupusería "El Comalito"
                |Seleccione una opción:
                |1. Registrar una nueva orden
                |2. Ver órdenes pendientes
                |3. Despachar orden
                |4. Ver órdenes despachadas
                |5. Salir
            """.trimMargin())

            when (readLine()) {
                "1" -> registrarOrden()
                "2" -> verOrdenesPendientes()
                "3" -> despacharOrden()
                "4" -> verOrdenesDespachadas()
                "5" -> {
                    println("Saliendo del sistema...")
                    break
                }
                else -> println("Opción no válida. Intente nuevamente.")
            }
        }
    }
}

// Función principal
fun main() {
    val pupuseria = Pupuseria()
    pupuseria.mostrarMenu()
}
