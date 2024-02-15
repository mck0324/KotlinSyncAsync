package sample.coffee

import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {  }


fun main() {
    measureTimeMillis {
        repeat(1) {
            makeCoffee()
        }
    }.let { logger.debug { ">>커피 만드는데 걸리는 시간: $it ms" } }
}

private fun makeCoffee() {
    grindCoffee()
    brewCoffee()
    boilMilk()
    formMilk()
    mixCoffeeAndMilk()

}
private fun grindCoffee() {
    logger.debug { "커피원두 갈기" }
    Thread.sleep(1000)
    logger.debug { ">> 커피가루" }
}

private fun brewCoffee() {
    logger.debug { "커피 내리기" }
    Thread.sleep(1000)
    logger.debug { ">> 커피 원액" }
}

private fun boilMilk() {
    logger.debug { "우유 끓이기" }
    Thread.sleep(1000)
    logger.debug { ">> 데워진 우유" }
}

private fun formMilk() {
    logger.debug { "우유 거품 내기" }
    Thread.sleep(1000)
    logger.debug { ">> 거품 우유" }
}

private fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    Thread.sleep(1000)
    logger.debug { ">> 카페라떼 완성" }
}

//Block방식
//순차적으로 실행되므로 커피 한잔 만드는데 걸리는 시간:5035 ms
//순차적으로 실행되므로 커피 두잔 만드는데 걸리는 시간:10071 ms