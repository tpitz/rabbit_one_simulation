package com.rabbit.scenarios.mails

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import ru.tinkoff.gatling.amqp.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ObjCancelMail {
  val ObjCancelMail: ScenarioBuilder = scenario("Cancel_mail")
    .feed(correlationIds)
    .exec(
      amqpTransaction("Mail : Receive obj with wrong type",
        "E-orc", "working", "Q-orc-deadletter", "CANCEL_EMAIL", "data/Mail/objCancelEmailWithWrongType.json")
    )
}
