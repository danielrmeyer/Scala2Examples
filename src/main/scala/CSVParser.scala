// Script for parsing a csv file.  User supplies the header

import java.io.{BufferedReader, FileReader}
import scala.collection.mutable

object CSVParser {
  def parse(fileName: String, labels: List[String], delimiter: String = ","): Map[String, Array[String]] = {

    val file = new FileReader(fileName)
    val reader = new BufferedReader(file)

    try {
      val data = new mutable.ListBuffer[Array[String]]
      var line: String = null
      while ( {
        line = reader.readLine();
        line
      } != null) {
        if (line.nonEmpty) {
          val splitLine: Array[String] = line.split(delimiter).map(_.trim)
          data += splitLine

        }
      }

      val labeledData: mutable.Map[String, mutable.ListBuffer[String]] = mutable.Map()

      for ((label, index) <- labels.zipWithIndex) {
        labeledData(label) = mutable.ListBuffer[String]()
        for (row <- data) {
          labeledData(label) += row(index)
        }
      }

      var csvData: Map[String, Array[String]] = Map()

      for((label, a) <- labeledData)
        csvData += (label -> a.toArray)

      csvData
    }

    finally {
      reader.close()
    }
  }
}

object CSVParserMain extends App {
  if (args.length > 0) {
    val fileName = args(0)
    println(s"parsing $fileName")

    val labels = List("sepal length", "sepal width",
      "petal length", "petal width", "class")

    val csvData = CSVParser.parse(fileName, labels)
  }
  else
    Console.err.println("Please supply a file path.")
}

