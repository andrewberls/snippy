## Snippy

Barebones Scala bindings for the [nippy](https://github.com/ptaoussanis/nippy) Clojure serialization format.

### Examples

See the [examples](https://github.com/andrewberls/snippy/blob/master/examples/) directory.

```scala
def write(f: File, xs: Seq[Any]): DataOutputStream
def write(path: String, xs: Seq[Any]): DataOutputStream

def read(f: File): Iterator[Any]
def read(path: String): Iterator[Any]
```

```scala
import snippy.Snippy

Snippy.write("test.nippy", List(1,2,3))

Snippy.read("test.nippy").foreach { x =>
  println(x)
}
```
