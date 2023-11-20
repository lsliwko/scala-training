package lessons

import com.google.gson.GsonBuilder
import org.apache.commons.lang3.builder.{EqualsBuilder, HashCodeBuilder, ReflectionToStringBuilder}

import scala.beans.BeanProperty

class ObjectData(
  // @BeanProperty creates getters / setters
  @BeanProperty var objectId : String, //mapped to java.lang.String
  @BeanProperty var fieldA : String,   //mapped to java.lang.String
  @BeanProperty var fieldB : java.lang.Integer,
  @BeanProperty var fieldC : java.lang.Boolean,
) {

  //need empty constructor for jaxb, json serializers, etc.
  def this() = this(null,null,null,null)

  override def toString: String = ReflectionToStringBuilder.toString(this)

  override def equals(obj: Any): Boolean = EqualsBuilder.reflectionEquals(this, obj)

  override def hashCode(): Int = HashCodeBuilder.reflectionHashCode(this) //32 bits
}


object MainJsonBeanPropertiesApp extends App {

  //many Java libraries (e.g. persistence, jabx, serialisation) require setters / getters
  val gson = new GsonBuilder().setPrettyPrinting().create
  val objectData = new ObjectData("objectId-123", "fieldA-123", 99, true)

  println {
    "Json:\n" +
    gson.toJson(objectData) +
    "\n-----"
  }

  val objectDataTmp = gson.fromJson(
    """{
      |  "objectId": "objectId-123",
      |  "fieldA": "fieldA-123",
      |  "fieldB": 99,
      |  "fieldC": true
      |}""".stripMargin,
    classOf[ObjectData] //Java's DataObject.class
  )

  println {
    "ReflectionToStringBuilder:\n" +
    objectDataTmp +
    "\n-----"
  }

}
