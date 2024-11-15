package org.example
import org.example.Comparator
import java.io.InputStreamReader
import java.io.Serializable


trait UserType extends Serializable {
  def typeName: String

  def create: AnyRef

  override def clone: AnyRef = null

  def readValue(in: InputStreamReader): AnyRef

  def parseValue(ss: String): AnyRef

  def getValue: Any

  def getTypeComparator: Comparator
}
