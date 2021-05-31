package com.rabbit.config

import java.util.concurrent.atomic.AtomicInteger

object feeders {

  val idCorrelation = new AtomicInteger(1)
  val correlationIds: Iterator[Map[String, String]] = Iterator.continually(Map("correlationId" -> ("correlationId_"+idCorrelation.getAndIncrement())))
}