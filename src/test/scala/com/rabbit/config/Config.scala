package com.rabbit.config

import io.gatling.core.Predef._
import ru.tinkoff.gatling.amqp.Predef._
import ru.tinkoff.gatling.amqp.protocol.AmqpProtocolBuilder
import ru.tinkoff.gatling.amqp.request.AmqpProtocolMessage


object Config {

  val rabbitEnv = Map(
    "local" -> rabbitmq
      .host("localhost")
      .port(5672)
      .username("guest")
      .password("guest")
      .vhost("/")
  )

  val duration: Int = getProperty("duration", "1200").toInt
  val tps: Int = getProperty("tps", "300").toInt
  val env: String = getMandatoryProperty("env")

  val amqpConf: AmqpProtocolBuilder = amqp
    .connectionFactory(
      rabbitEnv(env)
    )
    .usePersistentDeliveryMode
    .replyTimeout(900000)
    .matchByMessage(getMatcher)

  def getMatcher(message: AmqpProtocolMessage): String = {
    message.amqpProperties.getHeaders.get("correlationId").toString
  }

  def getMandatoryProperty(propertyName: String): String = {
    Option(getProperty(propertyName, null)).getOrElse(throw new IllegalArgumentException(propertyName + " parameter is mandatory"))
  }

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getProperty(propertyName))
      .orElse(Option(System.getenv(propertyName)))
      .getOrElse(defaultValue)
  }
}
