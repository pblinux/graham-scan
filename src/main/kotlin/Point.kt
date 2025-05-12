package me.pblinux

import kotlin.math.pow

/*
* Point
*
* Clase para representar los puntos y coordenadas
* */
class Point(
    val x: Double,
    val y: Double
) {

    companion object {
        /*
        * Producto cruzado de vectores
        *
        * Utilizando el punto a como referencia, se calculan los ángulos que se forman
        * con el punto b y el punto c.
        *
        * La fórmula para hacer el cálculo es:
        * ab * ac = ((bx - ax) * (cy - ay)) - ((by - ay) * (cx - ax))
        * */
        fun cross(a: Point, b: Point, c: Point): Double {
            return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)
        }

        /*
        * Cálculo de distancia
        *
        * Cuando tenemos ángulos iguales para el punto de referencia y los otros dos puntos
        * el criterio será la distancia más cercana.
        * */
        fun compareDistance(a: Point, b: Point, c: Point): Int {
            val dist1 = (a.x - b.x).pow(2) + (a.y - b.y).pow(2)
            val dist2 = (a.x - c.x).pow(2) + (a.y - c.y).pow(2)
            return dist1.compareTo(dist2)
        }
    }
}