package org.example
import org.example.{UserType,Comparator}

import java.io.InputStreamReader
import java.util.Random


class Point extends UserType {
  private var x = .0
  private var y = .0

  def this(x: Double, y: Double) {
    this()
    this.x = x
    this.y = y
  }

  override def typeName: String = String.valueOf(this.getClass)

  override def create: AnyRef = {
    val rnd = new Random
    x = rnd.nextDouble
    y = rnd.nextDouble
    new Point(x, y)
  }

  override def clone: AnyRef = {
    val myClone = new Point
    myClone.setX(this.x)
    myClone.setY(this.y)
    myClone
  }

  override def readValue(in: InputStreamReader): AnyRef = null

  override def parseValue(ss: String): AnyRef = {
    val words = ss.split(" ")
    val x = words(0).toDouble
    val y = words(1).toDouble
    new Point(x, y)
  }

  override def getTypeComparator: Comparator = new Comparator() {
    override def compare(o1: Any, o2: Any): Int = {
      val diff = Math.sqrt(Math.pow(o2.asInstanceOf[Point].x, 2) + Math.pow(o2.asInstanceOf[Point].y, 2)) - Math.sqrt(Math.pow(o1.asInstanceOf[Point].x, 2) + Math.pow(o1.asInstanceOf[Point].y, 2))
      if (diff > 0) {
        if (diff < 1) return 1
        diff.round.toInt
      }
      else {
        if (diff == 0) return 0
        else if (diff > -1) return -1
        diff.round.toInt
      }
    }
  }

  override def getValue: Any = x + " " + y

  def setX(x: Double): Unit = {
    this.x = x
  }

  def setY(y: Double): Unit = {
    this.y = y
  }
}
