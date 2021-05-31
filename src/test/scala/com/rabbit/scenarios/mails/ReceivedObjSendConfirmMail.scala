package com.rabbit.scenarios.mails

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import ru.tinkoff.gatling.amqp.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ReceivedObjSendConfirmMail {
  val ReceivedObjSendConfirmMail: ScenarioBuilder = scenario("Confirmation email")
    .feed(correlationIds)
    .exec(
      amqpTransaction("Mail : Receive obj click and collect, map it and send confirmation e-mail",
        "E-orc", "working", "Q-ord-working", "PROCESS", "data/Mail/validObjClickAndCollect.json")
    )
}
