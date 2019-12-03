import java.io.File

import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler
import scala.collection.mutable.ListBuffer

object Fern {
  def main(args: Array[String]): Unit = {
    val sources = ListBuffer.empty[File]
    var isOption = true
    for (arg <- args.dropRight(1)) { // last parameter - destination
      if (isOption && arg.length > 5 && arg.charAt(0) == '-' && arg.charAt(4) == '=') {
        // do nothing
      } else {
        isOption = false
        if (!arg.startsWith("-e=")) {
          val file = new File(arg)
          if (file.exists()) sources += file
          else println(s"warn: missing '$arg', ignored")
        }
      }
    }
    if (sources.isEmpty) {
      println("error: no sources given")
      return
    }
    if (sources.length > 1) {
      println("""pls run: find <src_dir> -type f -name "*.class" -exec fernflower-cli {} <dest> \;""")
      return
    }
    val dest = new File(args.last)
    if (dest.exists() && !dest.isDirectory) {
      System.out.println(s"error: destination '$dest' is not a directory")
      return
    }
    val newDest = dest.toPath.resolve(sources.head.getParent).toFile
    newDest.mkdirs()
    args.update(args.length - 1, newDest.getPath)
    ConsoleDecompiler.main(args)
  }
}
