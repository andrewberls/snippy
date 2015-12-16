package snippy

import java.io.{BufferedInputStream, BufferedOutputStream,
                DataInputStream, DataOutputStream,
                File, FileInputStream, FileOutputStream,
                IOException}
import scala.collection.immutable.Stream
import scala.util.{Try, Success, Failure}
import clojure.java.api.Clojure

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

  def read(istream: DataInputStream): Stream[Any] = {
    Try(thawFromStream.invoke(istream)) match {
      case Success(x) => x #:: read(istream)
      case Failure(e: IOException) => {
        istream.close()
        Stream.empty
      }
      case Failure(e) => throw e
    }
  }

  def read(f: File): Stream[Any] = {
    val istream = new BufferedInputStream(new FileInputStream(f))
    read(new DataInputStream(istream))
  }

  def read(path: String): Stream[Any] =
    read(new File(path))
}
