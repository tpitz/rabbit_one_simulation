package com.rabbit.simulations

import com.rabbit.config.Config._
import io.gatling.core.Predef._
import ru.tinkoff.gatling.amqp.Predef._
import com.rabbit.scenarios.mails.MailWithoutObj._
import com.rabbit.scenarios.mails.ReceivedObjSendConfirmMail._
import com.rabbit.scenarios.mails.ObjCancelMail._
import com.rabbit.scenarios.om.OMWithPrepItemSendToWorkingObj._
import com.rabbit.scenarios.om.IncorrectOMSendToObjDeadLetter._
import com.rabbit.scenarios.om.OMWithoutPrepItemSendToObj._
import com.rabbit.scenarios.om.UpdateOMSendToWorkingObj._
import com.rabbit.scenarios.eti.etiObjCancel._
import com.rabbit.scenarios.eti.etiObjIncorrectToDLQ._
import com.rabbit.scenarios.eti.etiObjPrepared._
import com.rabbit.scenarios.eti.etiObjReady._
import com.rabbit.scenarios.eti.ObjClkOrderSendToEtiOM._
import com.rabbit.scenarios.eti.ObjClkObj1SendToEtiOMWithDisc._

class Nominal extends Simulation {

  val tpsByScn: Int = math.ceil(tps / 13).toInt

  setUp(
    etiObjCancel.inject(constantUsersPerSec(tpsByScn) during duration),
    etiObjIncorrectToDLQ.inject(constantUsersPerSec(tpsByScn) during duration),
    etiObjPrepared.inject(constantUsersPerSec(tpsByScn) during duration),
    etiObjReady.inject(constantUsersPerSec(tpsByScn) during duration),
    ObjClkObj1SendToEtiOMWithDisc.inject(constantUsersPerSec(tpsByScn) during duration),
    ObjClkOrderSendToEtiOM.inject(constantUsersPerSec(tpsByScn) during duration),
    MailWithoutObj.inject(constantUsersPerSec(tpsByScn) during duration),
    ObjCancelMail.inject(constantUsersPerSec(tpsByScn) during duration),
    ReceivedObjSendConfirmMail.inject(constantUsersPerSec(tpsByScn) during duration),
    IncorrectOMSendToObjDeadLetter.inject(constantUsersPerSec(tpsByScn) during duration),
    OMWithoutPrepItemSendToObj.inject(constantUsersPerSec(tpsByScn) during duration),
    OMWithPrepItemSendToWorkingObj.inject(constantUsersPerSec(tpsByScn) during duration),
    UpdateOMSendToWorkingObj.inject(constantUsersPerSec(tpsByScn) during duration)
  ).protocols(amqpConf)
    .assertions(
      forAll.successfulRequests.percent.gt(90),
      forAll.responseTime.percentile3.lte(200)
    )


  before {
    println("Test launched on env: '" + env +"'")
    println("Test with: " + tps + " Message per second, during " + duration)
  }
}
