## Snippy

Barebones Scala bindings for the [nippy](https://github.com/ptaoussanis/nippy) Clojure serialization format.

### Examples

See the [examples](https://github.com/andrewberls/snippy/blob/master/examples/) directory.

```
import snippy.Snippy

Snippy.write("test.nippy", List(1,2,3))

Snippy.read("test.nippy").foreach { x =>
  println(x)
}
```
