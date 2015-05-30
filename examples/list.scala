package examples

import snippy.Snippy

object ListExample {
  def main(args: Array[String]) {
    Snippy.write("test.nippy", List(1,2,3))

    Snippy.read("test.nippy").foreach { x =>
      println(x)
    }
  }
}
