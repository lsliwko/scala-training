package com.sparktest.spark

import org.apache.spark.sql._

trait SparkTester {

    val sparkSession: SparkSession = {
      SparkSession
        .builder()
        .master("local")
        .appName("Spark Test local")
        .config("spark.ui.enabled", "false")
        .config("spark.sql.shuffle.partitions", "1")
        .getOrCreate()
    }
}
