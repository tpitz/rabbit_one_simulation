package com.rabbit.scenarios.mails

import com.rabbit.config.feeders.correlationIds
import com.rabbit.transactions.AMQPTransaction.amqpTransaction
import io.gatling.core.Predef.scenario
import ru.tinkoff.gatling.amqp.Predef._
import io.gatling.core.structure.ScenarioBuilder

object MailWithoutObj {
  val MailWithoutObj: ScenarioBuilder = scenario("Mail_with_missing_obj")
    .feed(correlationIds)
    .exec(
      amqpTransaction("Mail : Try to send e-mail with missing obj",
        "E-orc", "working", "Q-orc-deadletter", "CONFIRM_EMAIL", "data/Mail/invalidObjClickAndCollect.json")
    )
}
