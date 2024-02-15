package sample

import mu.KotlinLogging
import java.util.concurrent.CompletableFuture
import kotlin.time.measureTime

private val logger = KotlinLogging.logger {  }

fun main() {
    measureTime {
        val taskA = subA()
        subB()
        while (!taskA.isDone) {
            logger.debug { "...waiting A" }
            Thread.sleep(100)
        }
    }.let { logger.debug { ">> elasped : $it ms" } }
}

private fun subA(): CompletableFuture<Unit> {
    return CompletableFuture.supplyAsync {
        logger.debug { "Start A" }
        Thread.sleep(3000)
        logger.debug { "End A" }
    }
}

private fun subB() {
    logger.debug { "Start B" }
    Thread.sleep(1000)
    logger.debug { "End B" }
}

//Sync Non-Blocking
//A를 호출하고 다른 일을 할 수 도 있음
//A를 기다리는동안 진행상태를 찍어볼 수 있음