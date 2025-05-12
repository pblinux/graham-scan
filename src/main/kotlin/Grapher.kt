package me.pblinux

import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import org.knowm.xchart.XYSeries
import org.knowm.xchart.style.markers.SeriesMarkers
import java.awt.BasicStroke
import java.awt.Color

/*
* Grapher
*
* Clase para poder visualizar la gráfica generada
* Utiliza xchart por detrás para mostrar los puntos y las uniones del
* convex hull
* */
class Grapher(
    val chart: XYChart,
    val wrapper: SwingWrapper<XYChart>,
    val delay: Long = 800,
) {

    /*
    * Draw
    *
    * Se debe llamar cada vez que queramos dibujar algo en pantalla.
    * Dibuja los puntos, el convex hull y el punto inicial.
    * */
    fun draw(
        points: List<Point>,
        convexHull: List<Point>,
        initial: Point,
        selected: Point? = null,
    ) {
        chart.seriesMap.clear()

        chart.addSeries("Puntos", points.map { it.x }, points.map { it.y }).apply {
            marker = SeriesMarkers.CIRCLE
            markerColor = Color.GRAY
            lineStyle = null
            xySeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Scatter
        }

        chart.addSeries(
            "Punto inicial", listOf(initial.x), listOf(initial.y)
        ).apply {
            marker = SeriesMarkers.DIAMOND
            markerColor = Color.GREEN
            lineStyle = null
            xySeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Scatter
        }

        selected?.let {
            chart.addSeries(
                "Punto actual", listOf(it.x), listOf(it.y)
            ).apply {
                marker = SeriesMarkers.CIRCLE
                markerColor = Color.BLUE
                lineStyle = null
                xySeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Scatter
            }
        }

        if (convexHull.size >= 2) {
            val hx = convexHull.map { it.x }
            val hy = convexHull.map { it.y }
            chart.addSeries(
                "Convex Hull", hx, hy
            ).apply {
                lineColor = Color.BLUE
                marker = SeriesMarkers.NONE
                lineStyle = BasicStroke()
                xySeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Line
            }
        }

        wrapper.repaintChart()
        Thread.sleep(delay)
    }
}