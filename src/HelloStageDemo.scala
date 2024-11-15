import javafx.collections.{FXCollections, ObservableList}
import org.example.{MyFloat, MyInt, MyLinkedList, MyString, Point}
import scalafx.application.JFXApp3
import scalafx.event
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, ChoiceBox, ComboBox, Label, TextField}
import scalafx.scene.input.ContextMenuEvent
import scalafx.scene.layout.FlowPane

import java.beans.EventHandler
import java.io.IOException

object HelloStageDemo extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Hello Stage"
      width = 1000
      height = 800
      scene = new Scene {
        var linkedList: MyLinkedList = null
        var myType = -1
        var flowPane = new FlowPane()
        flowPane.setLayoutX(400)
        flowPane.setLayoutY(0)
        flowPane.setMinWidth(400)
        flowPane.setMinHeight(1000)
        content.add(flowPane)
        var mainComponentText = new Label("Основные компоненты управления")
        mainComponentText.setLayoutX(25)
        mainComponentText.setLayoutY(25)
        content.add(mainComponentText)
        var types : Seq[String] =  Seq("MyInt","MyFloat","MyString","Point")
        var createListType = new ComboBox[String](types)
        createListType.setLayoutX(25)
        createListType.setLayoutY(50)
        createListType.setOnAction(ActionEvent => {
          if(createListType.getValue.equals("MyInt")){
            linkedList = new MyLinkedList
            myType = 1
          }
          if(createListType.getValue.equals("MyFloat")){
            linkedList = new MyLinkedList
            myType = 2
          }
          if(createListType.getValue.equals("MyString")){
            linkedList = new MyLinkedList
            myType = 3
          }
          if(createListType.getValue.equals("Point")){
            linkedList = new MyLinkedList
            myType = 4
          }
        })
        content.add(createListType)
        var mainValueTextField = new TextField()
        mainValueTextField.setPromptText("Введите значение:")
        mainValueTextField.setLayoutX(25)
        mainValueTextField.setLayoutY(80)
        content.add(mainValueTextField)
        var addInEndButton = new Button()
        addInEndButton.setText("Добавить значение в конец списка")
        addInEndButton.setOnAction(ActionEvent => {
          var str: String = mainValueTextField.getText
          myType match {
            case (-1) =>
              new Alert(AlertType.Information, "Нельзя добавить значение, список не создан").showAndWait()
            case 1 =>
              try {
                linkedList.add(new MyInt().parseValue(str).asInstanceOf[MyInt])
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 2 =>
              try {
                linkedList.add(new MyFloat().parseValue(str).asInstanceOf[MyFloat])
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 3 =>
              try {
                linkedList.add(new MyString().parseValue(str).asInstanceOf[MyString])
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 4 =>
              var x = 0.0D
              var y = 0.0D
              try {
                var arr = str.split(" +")
                x = arr(0).toDouble
                y = arr(1).toDouble
                linkedList.add(new Point(x, y))
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case _ =>

          }
        })
        addInEndButton.setLayoutX(25)
        addInEndButton.setLayoutY(110)
        content.add(addInEndButton)
        var indexTextField = new TextField()
        indexTextField.setPromptText("Введите индекс:")
        indexTextField.setLayoutX(25)
        indexTextField.setLayoutY(140)
        content.add(indexTextField)
        var addInIndexButton = new Button()
        addInIndexButton.setText("Добавить значение по индексу в список")
        addInIndexButton.setOnAction(ActionEvent=>{
          var str: String = mainValueTextField.getText
          var idx: Int = indexTextField.getText.toInt
          myType match {
            case (-1) =>
              new Alert(AlertType.Information, "Нельзя добавить значение, список не создан").showAndWait()
            case 1 =>
              try {
                linkedList.addNum(new MyInt().parseValue(str).asInstanceOf[MyInt], idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 2 =>
              try {
                linkedList.addNum(new MyFloat().parseValue(str).asInstanceOf[MyFloat],idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 3 =>
              try {
                linkedList.addNum(new MyString().parseValue(str).asInstanceOf[MyString],idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 4 =>
              var x = 0.0D
              var y = 0.0D
              try {
                var arr = str.split(" +")
                x = arr(0).toDouble
                y = arr(1).toDouble
                linkedList.addNum(new Point(x, y),idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case _ =>

          }
        })
        addInIndexButton.setLayoutX(25)
        addInIndexButton.setLayoutY(170)
        content.add(addInIndexButton)
        var replaceValueButton = new Button()
        replaceValueButton.text = "Заменить значение по индексу"
        replaceValueButton.setOnAction(ActionEvent=>{
          var str: String = mainValueTextField.getText
          var idx: Int = indexTextField.getText.toInt
          myType match {
            case (-1) =>
              new Alert(AlertType.Information, "Нельзя заменить значение, список не создан").showAndWait()
            case 1 =>
              try {
                linkedList.set(new MyInt().parseValue(str).asInstanceOf[MyInt], idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 2 =>
              try {
                linkedList.set(new MyFloat().parseValue(str).asInstanceOf[MyFloat],idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 3 =>
              try {
                linkedList.set(new MyString().parseValue(str).asInstanceOf[MyString],idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 4 =>
              var x = 0.0D
              var y = 0.0D
              try {
                var arr = str.split(" +")
                x = arr(0).toDouble
                y = arr(1).toDouble
                linkedList.set(new Point(x, y),idx)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case _ =>

          }
        })
        replaceValueButton.setLayoutX(25)
        replaceValueButton.setLayoutY(200)
        content.add(replaceValueButton)
        var removeValueButton = new Button()
        removeValueButton.text = "Удалить значение по индексу"
        removeValueButton.setOnAction(ActionEvent=>{
          var idx = indexTextField.getText.toInt
          if (linkedList != null){ linkedList.remove(idx)
            reshapeList(flowPane,linkedList)
          }
          else new Alert(AlertType.Information, "Списка не существует").showAndWait()
        })
        removeValueButton.setLayoutX(25)
        removeValueButton.setLayoutY(230)
        content.add(removeValueButton)
        var sortV1Button = new Button()
        sortV1Button.text = "Первая сортировка"
        sortV1Button.setOnAction(ActionEvent => {
          myType match {
            case (-1) =>
              new Alert(AlertType.Information, "Нельзя сортировать значения, список не создан").showAndWait()

            case 1 =>
              try {
                linkedList.quickSort(linkedList, 0, linkedList.getSize()-1, new MyInt().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 2 =>
              try {
                linkedList.quickSort(linkedList, 0, linkedList.getSize()-1, new MyFloat().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 3 =>
              try {
                linkedList.quickSort(linkedList, 0, linkedList.getSize()-1, new MyString().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 4 =>
              try {
                linkedList.quickSort(linkedList, 0, linkedList.getSize()-1, new Point().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case _ =>

          }
        })
        sortV1Button.setLayoutX(25)
        sortV1Button.setLayoutY(260)
        content.add(sortV1Button)
        var saveList = new Button()
        saveList.text = "Сохранить список"
        saveList.setOnAction(ActionEvent => {
          try {
            MyLinkedList.serializeToBinary(linkedList)
            reshapeList(flowPane,linkedList)
          } catch {
            case ioe: IOException =>
              new Alert(AlertType.Information, "Не удалось сериализовать список").showAndWait()
          }
        })
        saveList.setLayoutX(25)
        saveList.setLayoutY(290)
        content.add(saveList)
        var loadList = new Button()
        loadList.text = "Загрузить список"
        loadList.setOnAction(ActionEvent => {
          try {
            linkedList = MyLinkedList.deserializeFromBinary
            reshapeList(flowPane,linkedList)
          } catch {
            case ioe: IOException =>
              new Alert(AlertType.Information, "Не удалось найти нужный класс").showAndWait()
          }
        })
        loadList.setLayoutX(25)
        loadList.setLayoutY(320)
        content.add(loadList)
        var sortV2Button = new Button()
        sortV2Button.text = "Вторая сортировка"
        sortV2Button.setOnAction(ActionEvent => {
          myType match {
            case (-1) =>
              new Alert(AlertType.Information, "Нельзя сортировать значения, список не создан").showAndWait()

            case 1 =>
              try {
                linkedList = linkedList.quickSort2(linkedList, new MyInt().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 2 =>
              try {
                linkedList.quickSort2(linkedList, new MyFloat().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 3 =>
              try {
                linkedList.quickSort2(linkedList, new MyString().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case 4 =>
              try {
                linkedList.quickSort2(linkedList,  new Point().getTypeComparator)
                reshapeList(flowPane,linkedList)
              } catch {
                case nfe: NumberFormatException =>
                  new Alert(AlertType.Information, "Неправильно введённый тип данных").showAndWait()
              }

            case _ =>

          }
        })
        sortV2Button.setLayoutX(25)
        sortV2Button.setLayoutY(350)
        content.add(sortV2Button)
        def reshapeList(flowPane : FlowPane, myLinkedList: MyLinkedList ): Unit = {
          flowPane.children.clear()
          var i = 0
          var value = myLinkedList.getFirst
          while(i<myLinkedList.getSize()){
            var element = new Label()
            element.setText(value.getValue.getValue.toString+" ")
            flowPane.children.add(element)
            value = value.getNext
            i+=1
          }
        }
      }
    }
  }
}
