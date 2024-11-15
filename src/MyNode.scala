package org.example
import org.example.{UserType}

import java.io.Serializable


class MyNode (private var prev: MyNode, private var value: UserType, private var next: MyNode) extends Serializable {
  def getNext: MyNode = next

  def getPrev: MyNode = prev

  def getValue: UserType = value

  def setNext(next: MyNode): Unit = {
    this.next = next
  }

  def setPrev(prev: MyNode): Unit = {
    this.prev = prev
  }

  def setValue(value: UserType): Unit = {
    this.value = value
  }

  def printValue: Any = value.getValue
}
