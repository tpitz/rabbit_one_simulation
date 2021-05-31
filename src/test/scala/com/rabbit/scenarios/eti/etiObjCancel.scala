package com.rabbit.scenarios.eti

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object etiObjCancel {
  val etiObjCancel: ScenarioBuilder = scenario("EtiObjCancel")
    .feed(correlationIds)
    .exec(
      amqpTransaction("read cancel obj04 and send obj1 to obj2",
        "E-orc","working","Q-ord-working",
        "Obj", "data/Eti/ObjCancel.txt")
    )
}
