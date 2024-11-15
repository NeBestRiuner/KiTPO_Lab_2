package org.example
import org.example.{ForEach, UserType,Comparator,MyNode}

import java.io._


object MyLinkedList {
  @throws[IOException]
  def serializeToBinary(myLinkedList: MyLinkedList): Unit = {
    val file = new File("file.out")
    val fos = new FileOutputStream("file.out")
    val oos = new ObjectOutputStream(fos)
    oos.writeObject(myLinkedList)
    oos.flush()
    oos.close()
  }

  @throws[IOException]
  @throws[ClassNotFoundException]
  def deserializeFromBinary: MyLinkedList = {
    val fis = new FileInputStream("file.out")
    val oin = new ObjectInputStream(fis)
    oin.readObject.asInstanceOf[MyLinkedList]
  }
}

class MyLinkedList extends Serializable {
  first = null
  last = null
  private var first: MyNode = null
  private var last: MyNode = null
  private var size: Int = 0

  def add(value: UserType): Unit = {
    val newNode = new MyNode(null, value, null)
    if (size == 0) {
      last = newNode
      first = newNode
    }
    else {
      newNode.setPrev(last)
      last.setNext(newNode)
      last = newNode
    }
    size+=1
  }

  def get(index: Int): MyNode = {
    if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid index " + index)
    var cur: MyNode = null
    if (index < size / 2) {
      cur = first
      for (i <- 0 until index) {
        cur = cur.getNext
      }
    }
    else {
      cur = last
      for (i <- size - 1 until index by -1) {
        cur = cur.getPrev
      }
    }
    cur
  }

  def tail(num: Int): MyLinkedList = {
    var linkedList = new MyLinkedList()
    var i = 0
    var myNode = first
    if(myNode!=null){
      while(i<num){
        myNode = myNode.getNext
        i+=1
      }
      linkedList.add(myNode.getValue)
      while(i<size){
        myNode = myNode.getNext
        i+=1
        if(myNode!=null)
          linkedList.add(myNode.getValue)
      }
      return linkedList
    }else{
      return null
    }
  }

  def remove(index: Int): AnyRef = {
    val cur = this.get(index)
    if (cur.getPrev != null) cur.getPrev.setNext(cur.getNext)
    else this.first = cur.getNext
    if (cur.getNext != null) cur.getNext.setPrev(cur.getPrev)
    else this.last = cur.getPrev
    val value = cur.getValue
    cur.setValue(null)
    cur.setNext(null)
    cur.setPrev(null)
    size -= 1
    value
  }

  def addNum(value: UserType, index: Int): Unit = {
    if (index == size) this.add(value)
    else {
      val myNode = new MyNode(null, value, null)
      val cur = this.get(index)
      myNode.setNext(cur)
      if (cur.getPrev != null) {
        cur.getPrev.setNext(myNode)
        myNode.setPrev(cur.getPrev)
      }
      else first = myNode
      cur.setPrev(myNode)
      size += 1
    }
  }

  def set(value: UserType, index: Int): Unit = {
    val cur = this.get(index)
    cur.setValue(value)
  }

  def getSize(): Int = size

  def getFirst: MyNode = first

  def getLast: MyNode = last

  def print(): Unit = {
    var prev = first
    for (i <- 0 until size) {
      System.out.print(" " + prev.printValue + " ")
      prev = prev.getNext
    }
  }

  def quickSort(linkedList: MyLinkedList, low: Int, high: Int, comparator: Comparator): Unit = {
    if (linkedList.size == 0 || low >= high) return
    //выбираем опорный элемент  - средний
    val middle = low + (high - low) / 2
    val border = linkedList.get(middle).getValue
    //разделияем на подмассивы и меняем местами
    var i = low
    var j = high
    while (i <= j) {
      var valueI = linkedList.get(i)
      while (comparator.compare(valueI.getValue, border) < 0) {
        i += 1
        valueI = valueI.getNext
      }
      var valueJ = linkedList.get(j)
      while (comparator.compare(valueJ.getValue, border) > 0) {
        j -= 1
        valueJ = valueJ.getPrev
      }
      if (i <= j) {
        val swap = valueI.getValue
        valueI.setValue(valueJ.getValue)
        valueJ.setValue(swap)
        i += 1
        j -= 1
      }
    }
    //рекурсия для сортировки левой и правой части
    if (low < j) quickSort(linkedList, low, j, comparator)
    if (high > i) quickSort(linkedList, i, high, comparator)
  }
  def merge(left: MyLinkedList, right: MyLinkedList, comparator: Comparator): MyLinkedList = {
    var result = new MyLinkedList
    var i = 0
    var j = 0
    while(i < left.size&&j<right.size){
      if(comparator.compare(left.get(i).getValue,right.get(j).getValue)<=0){
        result.add(left.get(i).getValue)
        i+=1
      }else{
        result.add(right.get(j).getValue)
        j+=1
      }
    }
    while (i<left.size){
      result.add(left.get(i).getValue)
      i+=1
    }
    while (j<right.size){
      result.add(right.get(j).getValue)
      j+=1
    }
    return result
  }

  def quickSort2(myLinkedList: MyLinkedList, comparator: Comparator): MyLinkedList = {
    if (myLinkedList.size <= 1) return myLinkedList
    val mid = myLinkedList.size / 2
    var i = 0
    var prom = new MyLinkedList
    while(i<mid){
      prom.add(myLinkedList.get(i).getValue)
      i+=1
    }
    val left = quickSort2(prom,comparator)
    val right = quickSort2(myLinkedList.tail(mid),comparator)
    return merge(left, right, comparator)
  }

  def forEach(forEach: ForEach): Unit = {
    var cur = first
    while (cur != null) {
      forEach.toDo(cur.getValue)
      cur = cur.getNext
    }
  }
}
