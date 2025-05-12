package me.pblinux

/*
* Ordenamos una lista de puntos, del punto más bajo al más alto (primero en y, luego en x)
* */
fun List<Point>.sortPoints(): List<Point> {
    return this.sortedWith(compareBy<Point> { it.y }.thenBy { it.x })
}
