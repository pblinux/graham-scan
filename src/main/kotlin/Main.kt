package me.pblinux

import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.XYSeries

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val chart = XYChartBuilder()
        .width(1000).height(800)
        .title("Graham Scam")
        .xAxisTitle("X").yAxisTitle("Y").build()

    chart.styler.defaultSeriesRenderStyle = XYSeries.XYSeriesRenderStyle.Line
    chart.styler.markerSize = 8

    val wrapper = SwingWrapper(chart)
    wrapper.displayChart()

    val convexHull = GrahamScan(
        dataSet3(),
        Grapher(
            chart, wrapper, 800
        ),
    ).operate()

    println("Puntos del Convex Hull:")
    for (p in convexHull) {
        println("(${p.x}, ${p.y})")
    }
}
