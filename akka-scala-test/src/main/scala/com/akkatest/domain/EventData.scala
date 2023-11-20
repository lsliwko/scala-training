package com.akkatest.domain

import scala.beans.BeanProperty

class EventData(
  @BeanProperty var eventId : String,
  @BeanProperty var objectId : String,
  @BeanProperty var variableName : String,
  @BeanProperty var variableValue : String
) {
  def this() = this(null,null,null,null)
}
