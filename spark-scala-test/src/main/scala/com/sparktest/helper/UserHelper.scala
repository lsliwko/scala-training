package com.sparktest.helper

import com.sparktest.domain.UserData
import org.apache.spark.sql.SparkSession

object UserHelper {

  def loadTestUserDatas(sparkSession: SparkSession) = {

    import sparkSession.implicits._

    sparkSession.read
      .option("header", "true")
      .option("charset", "UTF8")
      .option("inferSchema", "true") //Spark will automatically go through the csv file and infer the schema of each column
      //NOTE: slower (this requires an extra pass over the file)
      .csv(UserHelper.getClass.getResource("/users.csv").getPath())
      .as[UserData]
  }
}
