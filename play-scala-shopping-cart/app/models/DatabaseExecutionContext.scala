package models

import akka.actor.ActorSystem
import play.libs.concurrent.CustomExecutionContext

import javax.inject.{Inject, Singleton}

@Singleton
class DatabaseExecutionContext @Inject()(system: ActorSystem) extends CustomExecutionContext(system, "database.dispatcher")
