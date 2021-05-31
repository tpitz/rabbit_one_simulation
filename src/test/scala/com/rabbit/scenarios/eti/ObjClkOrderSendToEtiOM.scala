package com.rabbit.scenarios.eti

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import ru.tinkoff.gatling.amqp.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ObjClkOrderSendToEtiOM {
  val ObjClkOrderSendToEtiOM: ScenarioBuilder = scenario("ClkOrderSendToOM")
    .feed(correlationIds)
    .exec(
      amqpTransaction("Eti : obj Click and Collect obj and Send obj to ETI Om",
        "E-orc","working","Q-ord-working","process", "data/Eti/ObjClickAndCollect.json")
    )
}
