import com.sparktest.function.UserFunctions
import com.sparktest.helper.{LoggerHelper, UserHelper}
import org.apache.spark.SparkConf
import org.apache.spark.sql._

import java.nio.file.{Files, Path}

object LocalClusterMainApp extends App {

  //sbt
  val LIBRARY_FILE = "./target/scala-2.12/spark-scala-test_2.12-1.0.0-SNAPSHOT.jar"

  //mvn
  //val LIBRARY_FILE = "./target/spark-scala-test_2.12-1.0.0-SNAPSHOT.jar"

  if (!Files.exists(Path.of(LIBRARY_FILE)))
    throw new RuntimeException(s"Library file ${LIBRARY_FILE} does not exist. Build project first byt 'sbt clean package'")

  val sparkSession = SparkSession.builder
    .master("local[4]")   //local[4] starts local master with 4 cores
    .appName("Spark Test")
    .config(new SparkConf().setJars(Seq(LIBRARY_FILE)))
    .getOrCreate()

  //NOTE: remember to run 'sbt clean package' to create new library jar for Spark


  import sparkSession.implicits._

// get me all articles from authors who wrote about taxation

  //MAP-REDUCE (Hadoop)
  //1: get me all articles about taxation (MAP on all workers)
  //2: get me all authors from those articles (REDUCE on master node)
  //(another query)
  //3: find all articles from those authors (MAP) (MAP on all workers)
  //4: show me list of all those articles (REDUCE on my master node)

  //MAP-SHUFFLE-REDUCE (Spark)
  //1: get me all articles about taxation (MAP on all workers)
  //2: I take list of all authors and send it to all workers ( DATA-SHUFFLING between nodes)
  //3: find all articles from those authors (MAP on all workers)
  //4: show me list of all those articles (REDUCE on my master node)


  val usersDataset = UserHelper.loadTestUserDatas(sparkSession)
  usersDataset.show(1000)


  val filteredUsersDataset = new UserFunctions(sparkSession).filterByEmailHost(usersDataset, ".*ail.com".r)
  filteredUsersDataset.show(1000)

  val obfuscatedUsersDataset = new UserFunctions(sparkSession).obfuscateEmails(filteredUsersDataset)
  obfuscatedUsersDataset.show(1000)

}
