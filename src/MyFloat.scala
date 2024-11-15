package org.example
import org.example.{UserType,Comparator}

import java.io.InputStreamReader

import java.util.Random


class MyFloat extends UserType {
  private var value : Float = 0.0F

  def this(value: Float) {
    this()
    this.value = value
  }

  override def typeName: String = String.valueOf(this.getClass)

  override def create: AnyRef = {
    val rnd = new Random
    value = rnd.nextFloat(100)
    new MyFloat(value)
  }

  override def clone: AnyRef = {
    val myClone = new MyFloat
    myClone.setValue(this.value)
    myClone
  }

  override def readValue(in: InputStreamReader): AnyRef = null

  override def parseValue(ss: String) = new MyFloat(ss.toFloat)

  override def getTypeComparator: Comparator= new Comparator() {
    override def compare(o1: Any, o2: Any): Int = return (o1.asInstanceOf[MyFloat].value - o2.asInstanceOf[MyFloat].value).toInt
  }

  override def getValue: Any = value

  def setValue(value: Float): Unit = {
    this.value = value
  }
}
