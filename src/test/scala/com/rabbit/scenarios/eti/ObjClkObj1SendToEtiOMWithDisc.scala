package com.rabbit.scenarios.eti

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object ObjClkObj1SendToEtiOMWithDisc {
  val ObjClkObj1SendToEtiOMWithDisc: ScenarioBuilder = scenario("ClkOrderSendToEtiOMWithDis")
    .feed(correlationIds)
    .exec(
      amqpTransaction("obj Click and Collect obj and Send obj to ETI Om with disc",
      "E-orc","working","Q-ord-working",
      "process", "data/Eti/ObjClickAndCollectWithDisc.json")
    )
}
