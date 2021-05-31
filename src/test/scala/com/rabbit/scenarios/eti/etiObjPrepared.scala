package com.rabbit.scenarios.eti

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object etiObjPrepared {
  val etiObjPrepared: ScenarioBuilder = scenario("EtiObjPrepared")
    .feed(correlationIds)
    .exec(
      amqpTransaction("read prepared obj with substitution",
        "E-orc","working","Q-ord-working",
        "Obj", "data/Eti/ObjPrepared.txt")
    )
}
