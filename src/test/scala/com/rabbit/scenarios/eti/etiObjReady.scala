package com.rabbit.scenarios.eti

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object etiObjReady {
  val etiObjReady: ScenarioBuilder = scenario("EtiObjReady")
    .feed(correlationIds)
    .exec(
      amqpTransaction("read ready to pick obj and send obj to obj",
        "E-orc","working","Q-ord-working",
        "Obj", "data/Eti/ObjReady.txt")
    )
}
