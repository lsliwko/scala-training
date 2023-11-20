package com.sparktest.spark

import com.sparktest.domain.UserData
import com.sparktest.function.UserFunctions
import com.sparktest.helper.UserHelper
import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers._

class UserFunctionsTest extends AnyFlatSpec with SparkTester {

  "UserFunctions" should "filter users by email hostname" in {
    val usersDataset = UserHelper.loadTestUserDatas(sparkSession)

    val userFunctions = new UserFunctions(sparkSession)
    val resultDataset = userFunctions.filterByEmailHost(usersDataset, "gmail.com".r)

    resultDataset.count() should be(2)
    resultDataset.collect() should contain theSameElementsAs List(
      UserData("Les", "lsliwko@gmail.com", java.sql.Timestamp.valueOf("2000-12-01 00:00:00")),
      UserData("Louis", "louis@gmail.com", java.sql.Timestamp.valueOf("2015-06-15 00:00:00"))
    )
  }

  "UserFunctions" should "obfuscate users' emails" in {
    val usersDataset = UserHelper.loadTestUserDatas(sparkSession)

    val userFunctions = new UserFunctions(sparkSession)
    val resultDataset = userFunctions.obfuscateEmails(usersDataset)

    resultDataset.count() should be(3)
    resultDataset.collect() should contain theSameElementsAs List(
      UserData("Les", "lxxxxxx@gmail.com", java.sql.Timestamp.valueOf("2000-12-01 00:00:00")),
      UserData("Tom", "txx@facebook.com", java.sql.Timestamp.valueOf("2010-01-01 00:00:00")),
      UserData("Louis", "lxxxx@gmail.com", java.sql.Timestamp.valueOf("2015-06-15 00:00:00"))
    )
  }

}
