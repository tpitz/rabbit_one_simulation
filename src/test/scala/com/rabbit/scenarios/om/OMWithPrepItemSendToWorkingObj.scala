package com.rabbit.scenarios.om

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object OMWithPrepItemSendToWorkingObj {
  val OMWithPrepItemSendToWorkingObj: ScenarioBuilder = scenario("OMWithPrepItemSendToWorkingObj")
    .feed(correlationIds)
    .exec(
      amqpTransaction("OM : read update with prepared item and send update to obj",
        "E-orc", "working", "Q-ord-working", "PROCESS_OM_UPDATE", "data/OMS/validOmUpdateDelivered.json")
    )
}
