package com.rabbit.scenarios.om

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object OMWithoutPrepItemSendToObj {
  val OMWithoutPrepItemSendToObj: ScenarioBuilder = scenario("OMWithoutPrepItemSendToWorkingObj")
    .feed(correlationIds)
    .exec(
      amqpTransaction("OM : read update from OM with null prepared item and send update to Obj",
        "E-orc", "working", "Q-ord-working", "PROCESS_OM_UPDATE", "data/OMS/validOmUpdatePreparedNullItem.json")
    )
}
