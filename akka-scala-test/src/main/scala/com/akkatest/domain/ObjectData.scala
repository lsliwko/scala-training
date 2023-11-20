package com.akkatest.domain

import scala.beans.BeanProperty

class ObjectData(
  @BeanProperty var objectId : String,
  @BeanProperty var varA : String,
  @BeanProperty var varB : String,
  @BeanProperty var varC : String
) {
  def this() = this(null,null,null,null)
}
