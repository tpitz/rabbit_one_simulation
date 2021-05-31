package com.rabbit.transactions

import io.gatling.core.Predef._
import ru.tinkoff.gatling.amqp.Predef._
import ru.tinkoff.gatling.amqp.request._

/**
 * Instead of all other com.carrefour.transactions, here we create a function returning
 * an HttpRequestBuilder to avoid duplicating the request for each use case.
 * Take a look at the SynchronizeBasketDrive scenario for a better understanding
 */
object AMQPTransaction {

  def amqpTransaction(scenarioName: String, exchangeName: String,routeKey: String,replyQueue: String,action: String,filePathMessage: String): RequestReplyDslBuilder = {
    val transaction: RequestReplyDslBuilder = amqp(scenarioName).requestReply
      .directExchange(exchangeName, routeKey)
      .replyExchange(replyQueue)
      .bytesMessage(RawFileBody(filePathMessage))
      .contentType("application/json")
      .headers("action" -> action, "correlationId" -> "${correlationId}")
      .priority(0)
    transaction
  }
}