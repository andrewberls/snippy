package snippy

import java.io.{BufferedInputStream, BufferedOutputStream,
                DataInputStream, DataOutputStream,
                File, FileInputStream, FileOutputStream,
                IOException}
import clojure.java.api.Clojure
import clojure.lang.{Keyword}

object Snippy {
  val require = Clojure.`var`("clojure.core", "require")
  require.invoke(Clojure.read("taoensso.nippy"))
  val freezeToStream = Clojure.`var`("taoensso.nippy", "freeze-to-stream!")
  val thawFromStream = Clojure.`var`("taoensso.nippy", "thaw-from-stream!")

  def write(ostream: DataOutputStream, xs: Seq[Any]): DataOutputStream = {
    xs.foreach { x => freezeToStream.invoke(ostream, x) }
    ostream.close()
    ostream
  }

  def write(f: File, xs: Seq[Any]): DataOutputStream = {
    val istream = new BufferedOutputStream(new FileOutputStream(f, true))
    write(new DataOutputStream(istream), xs)
  }

  def write(path: String, xs: Seq[Any]): DataOutputStream =
    write(new File(path), xs)

  //

  def read(istream: DataInputStream): Iterator[Any] = {
    new Iterator[Any] {
      var nextObj: Option[Any] = None

      private def readNext = {
        try {
          Some(thawFromStream.invoke(istream))
        } catch {
          case ex: IOException => {
            istream.close()
            None
          }
        }
      }

      def hasNext = { nextObj = readNext; nextObj.isDefined }

      def next = nextObj.get
    }
  }

  def read(f: File): Iterator[Any] = {
    val istream = new BufferedInputStream(new FileInputStream(f))
    read(new DataInputStream(istream))
  }

  def read(path: String): Iterator[Any] =
    read(new File(path))
}
