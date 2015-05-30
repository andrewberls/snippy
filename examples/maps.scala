package examples

import scala.collection.JavaConversions._
import java.util.{HashMap => JHashMap}
import clojure.lang.{Keyword}

import snippy.Snippy

object MapsExample {
  def main(args: Array[String]) {
    val hello = Keyword.intern("hello")

    val m = Map(hello -> "there")
    val jm = new JHashMap[Any, Any](m)
    Snippy.write("test.nippy", List(jm))

    val x = Snippy.read("test.nippy").next
    println(x.asInstanceOf[APersistentMap].get(hello)) // "there": Any
  }
}
