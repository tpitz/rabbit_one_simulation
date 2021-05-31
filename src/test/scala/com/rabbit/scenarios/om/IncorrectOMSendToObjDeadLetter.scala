package com.rabbit.scenarios.om

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object IncorrectOMSendToObjDeadLetter {
  val IncorrectOMSendToObjDeadLetter: ScenarioBuilder = scenario("IncorrectOMSendToObj")
    .feed(correlationIds)
    .exec(
      amqpTransaction("OM : read Incorrect OM abc must be send to Deadletter",
        "E-orc", "working", "Q-orc-deadletter", "PROCESS_OM_UPDATE", "data/OMS/invalidOmUpdate.json")
    )
}
