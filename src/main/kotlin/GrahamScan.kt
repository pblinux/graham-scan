package me.pblinux

class GrahamScan(
    val points: List<Point>,
    val grapher: Grapher
) {
    /*
    * operate
    *
    * Función de entrada, aquí se llamará a una función
    * convexHull que hará los cálculos.
    * */
    fun operate(): List<Point> {
        // Vemos la cantidad de elementos, si es menor a 3, no necesitamos calcular nada
        if (points.size <= 3) return points

        // Hay que encontrar el punto más bajo del listado de puntos (tanto en X como en Y)
        val sortedPoints = points.sortPoints()
        val initial = sortedPoints.first()

        // Ahora debemos encontrar el ángulo polar de nuestro punto más bajo respecto al resto de elementos
        // NOTA: removemos el primer elemento de la lista porque es nuestro inicial
        val sortedPointsByAngle = sortedPoints.drop(1).sortedWith { p1, p2 ->
            val angle = Point.cross(initial, p1, p2)
            when (angle) {
                0.0 -> {
                    Point.compareDistance(initial, p1, p2)
                }

                else -> {
                    -angle.compareTo(0)
                }
            }
        }

        // Ahora construimos el convex hull con los datos obtenidos
        return convexHull(sortedPointsByAngle, initial)
    }

    /*
    * convexHull
    *
    * A partir de los puntos previamente ordenaos y utilizando el punto más bajo, calculamos los puntos
    * que forman el convex con todos los puntos adentro.
    * */
    private fun convexHull(sortedPoints: List<Point>, initial: Point): List<Point> {
        // Lista de puntos que forman el convex, agregando al principio nuestro
        // punto inicial y el primer punto en nuestra lista de puntos ordenados por ángulo
        val convex = mutableListOf(initial, sortedPoints.first())
        grapher.draw(sortedPoints, convex, initial)

        // Recorremos cada uno de los puntos, descartando el primero, ya que ya fue agregado
        // al listado de convex
        sortedPoints.drop(1).forEach { current ->
            // Obtenemos el último y el penúltimo punto
            var top = convex.last()
            var nextToTop = convex[convex.size - 2]

            // Nuestro ciclo en el que compararemos y descartaremos los puntos que no nos sean
            // necesarios
            while (
                convex.size >= 2 &&
                Point.cross(nextToTop, top, current) <= 0
            ) {
                // Se elimina el último punto porque no mantiene la forma
                convex.removeAt(convex.lastIndex)

                // Asignamos los nuevos valores para la siguiente comprobación
                if (convex.size >= 2) {
                    top = convex.last()
                    nextToTop = convex[convex.size - 2]
                    grapher.draw(points, convex, initial)
                }
            }

            // Ya que no detectamos giros a la derecha, el punto current si puede
            // formar parte del convex, por lo que lo agregamos.
            convex.add(current)
            grapher.draw(points, convex, initial, current)
        }

        // Ya tenemos el convex hull, faltando únicamente agregar nuevamente
        // el punto inicial para cerrar la forma.
        convex.add(initial)
        grapher.draw(points, convex, initial)

        return convex
    }
}