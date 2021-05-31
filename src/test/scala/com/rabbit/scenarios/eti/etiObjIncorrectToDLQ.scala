package com.rabbit.scenarios.eti

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import ru.tinkoff.gatling.amqp.Predef._

object etiObjIncorrectToDLQ {
  val etiObjIncorrectToDLQ: ScenarioBuilder = scenario("EtiObjIncorrectToDLQ")
    .feed(correlationIds)
    .exec(
      amqpTransaction("read Incorrect obj must be send to DLQ",
        "E-orc","working","Q-orc-deadletter",
        "Obj", "data/Eti/ObjIncorrect.txt")
    )
}
