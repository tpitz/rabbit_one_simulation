package com.rabbit.scenarios.om

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import ru.tinkoff.gatling.amqp.Predef._
import io.gatling.core.structure.ScenarioBuilder

object UpdateOMSendToWorkingObj {
  val UpdateOMSendToWorkingObj: ScenarioBuilder = scenario("UpdateOMSSendToWorkingOrder")
    .feed(correlationIds)
    .exec(
      amqpTransaction("OM : Obj Click and Collect Obj and Send Obj to ETI Om",
        "E-orc", "working", "Q-ord-working", "PROCESS_OM_UPDATE", "data/OMS/validOmUpdate.json")
    )
}
