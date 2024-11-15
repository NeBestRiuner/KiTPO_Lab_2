package org.example
import org.example.{ UserType,Comparator}

import java.io.InputStreamReader

import java.util.Random


class MyString extends UserType {
  private var value: String = null

  def this(value: String) {
    this()
    this.value = value
  }

  override def typeName: String = String.valueOf(this.getClass)

  override def create: AnyRef = {
    val rnd = new Random
    value = Integer.toString(rnd.nextInt(20)) + Integer.toString(rnd.nextInt(20)) + " string"
    new MyString(value)
  }

  override def clone: AnyRef = {
    val myClone = new MyString
    myClone.setValue(this.value)
    myClone
  }

  override def readValue(in: InputStreamReader): AnyRef = null

  override def parseValue(ss: String) = new MyString(ss)

  override def getTypeComparator: Comparator = new Comparator() {
    override def compare(o1: Any, o2: Any): Int = return o1.asInstanceOf[MyString].value.length - o2.asInstanceOf[MyString].value.length
  }

  override def getValue: Any = value

  def setValue(value: String): Unit = {
    this.value = value
  }
}
