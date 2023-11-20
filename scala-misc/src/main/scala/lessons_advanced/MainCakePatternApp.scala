package lessons_advanced

import com.google.common.cache.{CacheBuilder, CacheLoader, LoadingCache}

import java.util.concurrent.TimeUnit


trait Logging {
  lazy val logger = new Logger

  val DEBUG = "debug"
  val INFO = "info"
  val ERROR = "error"

  class Logger {
    def log(level: String, message: String): Unit =
      println(s"$level: $message")
  }
}

trait Config {
  lazy val configLogFilePath: String = System.getenv("config-log-file")
  lazy val configA: String = System.getenv("configA")
  lazy val configB: String = System.getenv("configB")
}

trait Cache {

  //Google Guava
  lazy val cache : LoadingCache[String, String] =
    CacheBuilder.newBuilder()
      .expireAfterAccess(15, TimeUnit.MINUTES) //cached for 15 min
      .maximumSize(10000) //always provide max size
      .build(
        new CacheLoader[String, String] {
          override def load(key: String): String = {
            println(s"Computing value for key ${key}...")
            s"value for key ${key}"
          }
        })

}



object MainTraitApp extends App
  //Cake pattern
  with Logging
  with Config
  with Cache
{

  //logger is 'injected' = Cake pattern
  //config is 'injected' = Cake pattern
  logger.log(DEBUG,s"I am alive! Config = ${configA}")


  println {
    cache.get("1")
  }

  println {
    cache.get("1")
  }

  println {
    cache.get("1")
  }

}
