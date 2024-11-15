package org.example
import org.example.{UserType,Comparator}

import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.Random


class MyInt extends UserType {
  private var value: Integer = null

  def this(value: Int) {
    this()
    this.value = value
  }

  override def typeName: String = {
    return String.valueOf(this.getClass)
  }

  override def create: AnyRef = {
    val rnd: Random = new Random
    value = rnd.nextInt(100)
    return new MyInt(value)
  }

  override def clone: AnyRef = {
    val myClone: MyInt = new MyInt
    myClone.setValue(this.value)
    return myClone
  }

  override def readValue(in: InputStreamReader): AnyRef = {
    return null
  }

  override def parseValue(ss: String): AnyRef = {
    return new MyInt(ss.toInt)
  }

  override def getValue: Any = {
    return value
  }

  def setValue(value: Int): Unit = {
    this.value = value
  }

  override def getTypeComparator: Comparator = {
    return new Comparator() {
      override def compare(o1: Any, o2: Any): Int = {
        //System.out.println( ((MyInt)o2).value.intValue());
        return ((o1.asInstanceOf[MyInt]).value.intValue - (o2.asInstanceOf[MyInt]).value.intValue)
      }
    }
  }
}

