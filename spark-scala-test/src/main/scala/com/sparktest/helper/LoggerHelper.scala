package com.sparktest.helper

import org.apache.log4j.Logger

object LoggerHelper extends Serializable {
  @transient lazy val log = Logger.getLogger(getClass.getName)
}