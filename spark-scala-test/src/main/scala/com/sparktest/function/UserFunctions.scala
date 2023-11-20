package com.sparktest.function

import com.sparktest.domain.UserData
import org.apache.spark.sql._

import scala.util.matching.Regex

class UserFunctions(sparkSession: SparkSession) extends Serializable {

  import sparkSession.implicits._

  private def extractUsernameAndHost(email : String) =
    Option(email)
      .map { email =>
        val Array(username, host) = email.trim.split("@",2) //in Scala 3: Tuple.fromArray
        (username, host)
      }

  private def obfuscateEmail(email : String) =
    extractUsernameAndHost(email).map { case (username, host) =>
      val obfuscatedUsername = username.zipWithIndex.map { case (char, index) => if (index == 0) char else 'x' }.mkString
      s"$obfuscatedUsername@$host"
    }

  def obfuscateEmails(users: Dataset[UserData]): Dataset[UserData] =
    users.map { user => user.copy(email = obfuscateEmail(user.email).orNull) }

  def filterByEmailHost(users: Dataset[UserData], hostRegex: Regex): Dataset[UserData] =
    users.filter { user =>
      extractUsernameAndHost(user.email).map { emailPair => hostRegex.findFirstIn(emailPair._2).isDefined }.getOrElse(false)
    }

}
