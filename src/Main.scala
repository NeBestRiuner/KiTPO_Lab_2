package org.example
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.InputMismatchException
import java.util.Scanner


object Main {
  @throws[IOException]
  def main(args: Array[String]): Unit = {
    var linkedList: MyLinkedList = null
    var myType = -1
    val scanner = new Scanner(System.in)
    val printStream = new PrintStream(System.out, true, "UTF-8")
    try while (true) {
      printStream.println("Введите номер действия: \n" + "1. Создать новый двусвязный список\n" + "2. Добавить значение в конец списка\n" + "3. Добавить значение в список по индексу\n" + "4. Удалить значение из списка\n" + "5. Заменить значение в списке\n" + "6. Вывести длину списка\n" + "7. Отсортировать список\n" + "8. Вывести список\n" + "9. Сериализовать список в файл\n" + "10. Десериализовать список из файла\n" + "11. Выйти\n")
      val num = scanner.nextInt
      num match {
        case 1 =>
          printStream.println("Введите номер поддействия:\n" + "1. Создать двусвязный список Int\n" + "2. Создать двусвязный список Float\n" + "3. Создать двусвязный список String\n" + "4. Создать двусвязный список Point\n")
          val podNum = scanner.nextInt
          podNum match {
            case 1 =>
              linkedList = new MyLinkedList
              myType = 1

            case 2 =>
              linkedList = new MyLinkedList
              myType = 2

            case 3 =>
              linkedList = new MyLinkedList
              myType = 3

            case 4 =>
              linkedList = new MyLinkedList
              myType = 4

            case _ =>

          }

        case 2 =>
          var str: String = null
          myType match {
            case (-1) =>
              printStream.println("Нельзя добавить значение, список не создан")

            case 1 =>
              try {
                printStream.println("Введите целочисленное значение:")
                str = scanner.nextLine
                str = scanner.nextLine
                linkedList.add(new MyInt().parseValue(str).asInstanceOf[MyInt])
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 2 =>
              printStream.println("Введите значение типа float:")
              try {
                str = scanner.nextLine
                str = scanner.nextLine
                linkedList.add(new MyFloat().parseValue(str).asInstanceOf[MyFloat])
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 3 =>
              printStream.println("Введите строковое значение:")
              try {
                str = scanner.nextLine
                str = scanner.nextLine
                linkedList.add(new MyString().parseValue(str).asInstanceOf[MyString])
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 4 =>
              var x = 0.0D
              var y = 0.0D
              try {
                printStream.println("Введите координату точки X:")
                str = scanner.nextLine
                str = scanner.nextLine
                x = str.toDouble
                printStream.println("Введите координату точки Y:")
                str = scanner.nextLine
                y = str.toDouble
                linkedList.add(new Point(x, y))
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case _ =>

          }

        case 3 =>
          var str: String = null
          var idx = scanner.nextInt
          myType match {
            case (-1) =>
              printStream.println("Нельзя добавить значение, список не создан")

            case 1 =>
              try {
                printStream.println("Введите целочисленное значение:")
                str = scanner.nextLine
                str = scanner.nextLine
                printStream.println("Введите индекс:")
                val idx = scanner.nextInt
                linkedList.addNum(new MyInt().parseValue(str).asInstanceOf[MyInt], idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 2 =>
              printStream.println("Введите значение типа float:")
              try {
                str = scanner.nextLine
                str = scanner.nextLine
                printStream.println("Введите индекс:")
                val idx = scanner.nextInt
                linkedList.addNum(new MyFloat().parseValue(str).asInstanceOf[MyFloat], idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 3 =>
              printStream.println("Введите строковое значение:")
              try {
                str = scanner.nextLine
                str = scanner.nextLine
                printStream.println("Введите индекс:")
                val idx = scanner.nextInt
                linkedList.addNum(new MyString().parseValue(str).asInstanceOf[MyString], idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 4 =>
              var x = 0.0
              var y = 0.0
              try {
                printStream.println("Введите координату точки X:")
                str = scanner.nextLine
                str = scanner.nextLine
                x = str.toDouble
                printStream.println("Введите координату точки Y:")
                str = scanner.nextLine
                y = str.toDouble
                printStream.println("Введите индекс:")
                val idx = scanner.nextInt
                linkedList.addNum(new Point(x, y), idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case _ =>

          }

        case 4 =>
          printStream.println("Введите индекс значения, которое хотите удалить:")
          var idx = scanner.nextInt
          if (linkedList != null) linkedList.remove(idx)
          else printStream.println("Списка несуществует")

        case 5 =>
          var str: String = null
          var idx = scanner.nextInt
          myType match {
            case (-1) =>
              printStream.println("Нельзя изменить значение, список не создан")

            case 1 =>
              try {
                printStream.println("Введите целочисленное значение:")
                str = scanner.nextLine
                str = scanner.nextLine
                printStream.println("Введите индекс:")
                idx = scanner.nextInt
                linkedList.set(new MyInt().parseValue(str).asInstanceOf[MyInt], idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 2 =>
              printStream.println("Введите значение типа float:")
              try {
                str = scanner.nextLine
                str = scanner.nextLine
                printStream.println("Введите индекс:")
                idx = scanner.nextInt
                linkedList.set(new MyFloat().parseValue(str).asInstanceOf[MyFloat], idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 3 =>
              printStream.println("Введите строковое значение:")
              try {
                str = scanner.nextLine
                str = scanner.nextLine
                printStream.println("Введите индекс:")
                idx = scanner.nextInt
                linkedList.set(new MyString().parseValue(str).asInstanceOf[MyString], idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 4 =>
              var x = 0.0
              var y = 0.0
              try {
                printStream.println("Введите координату точки X:")
                str = scanner.nextLine
                str = scanner.nextLine
                x = str.toDouble
                printStream.println("Введите координату точки Y:")
                str = scanner.nextLine
                y = str.toDouble
                printStream.println("Введите индекс:")
                idx = scanner.nextInt
                linkedList.set(new Point(x, y), idx)
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case _ =>

          }

        case 6 =>
          if (linkedList != null) printStream.println("Длина списка: " + linkedList.getSize())
          else printStream.println("Списка несуществует")

        case 7 =>
          myType match {
            case (-1) =>
              printStream.println("Нельзя сортировать значения, список не создан")

            case 1 =>
              try {
                printStream.println("Список до сортировки: ")
                linkedList.print()
                printStream.println("Список после сортировки: ")
                linkedList.quickSort(linkedList, 0, linkedList.getSize(), new MyInt().getTypeComparator)
                linkedList.print()
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 2 =>
              try {
                printStream.println("Список до сортировки: ")
                linkedList.print()
                printStream.println("Список после сортировки: ")
                linkedList.quickSort(linkedList, 0, linkedList.getSize(), new MyFloat().getTypeComparator)
                linkedList.print()
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 3 =>
              try {
                printStream.println("Список до сортировки: ")
                linkedList.print()
                printStream.println("Список после сортировки: ")
                linkedList.quickSort(linkedList, 0, linkedList.getSize(), new MyString().getTypeComparator)
                linkedList.print()
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case 4 =>
              try {
                printStream.println("Список до сортировки: ")
                linkedList.print()
                printStream.println("Список после сортировки: ")
                linkedList.quickSort(linkedList, 0, linkedList.getSize(), new Point().getTypeComparator)
                linkedList.print()
              } catch {
                case nfe: NumberFormatException =>
                  printStream.println("Неправильно введённый тип данных")
              }

            case _ =>

          }

        case 8 =>
          if (linkedList != null) linkedList.print()
          else printStream.println("Списка несуществует")

        case 9 =>
          try {
            printStream.println("Список сохраняется: ")
            if (linkedList != null) linkedList.print()
            else printStream.println("Списка несуществует")
            MyLinkedList.serializeToBinary(linkedList)
          } catch {
            case ioe: IOException =>
              printStream.println("Неудалось сериализовать список")
          }

        case 10 =>
          try {
            linkedList = MyLinkedList.deserializeFromBinary
            printStream.println("Загрузка из сохранения: ")
            if (linkedList != null) linkedList.print()
            else printStream.println("Списка несуществует")
          } catch {
            case cnfe: ClassNotFoundException =>
              printStream.println("Не удалось найти класс")
          }
          if (linkedList != null && linkedList.getSize() != 0) {
            val myNode = linkedList.getFirst
            val cl = myNode.getValue.getClass
            if (cl == new MyInt().getClass) myType = 1
            if (cl == new MyFloat().getClass) myType = 2
            if (cl == new MyString().getClass) myType = 3
            if (cl == new Point().getClass) myType = 4
          }

        case 11 =>
          return
        case _ =>

      }
    }
    catch {
      case ime: InputMismatchException =>
        printStream.println("Неправильно введённый тип данных")
    }
  }
}