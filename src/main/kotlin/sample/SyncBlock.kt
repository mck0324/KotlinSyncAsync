package sample

import mu.KotlinLogging
import kotlin.time.measureTime

private val logger = KotlinLogging.logger {  }

fun main() {
    measureTime {
        subA()
        subB()
    }.let { logger.debug { ">> elasped : $it ms" } }
}
private fun subB() {
    logger.debug { "start B" }
    Thread.sleep(1000)
    logger.debug { "end B" }
}
private fun subA() {
    logger.debug { "start A" }
    Thread.sleep(1000)
    logger.debug { "end A" }
}


//Sync Blocking
//일반적인 A 함수가 끝난 뒤 B가 실행