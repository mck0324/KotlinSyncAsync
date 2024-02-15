package sample.coffee

import mu.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {  }


//직원 3명 고용
private val employees = Executors.newFixedThreadPool(3)
fun main() {
    measureTimeMillis {
//        makeCoffee()
        repeat(2) {
            makeCoffee()
        }
        employees.shutdown()
        employees.awaitTermination(100, TimeUnit.SECONDS)
    }.let { logger.debug { ">>커피 만드는데 걸리는 시간: $it ms" } }
}

private fun makeCoffee() {
    val taskA = employees.submit {
        grindCoffee()
        brewCoffee()
    }

    val taskB = employees.submit {
        boilMilk()
        formMilk()
    }
    employees.submit {
        while ( !taskA.isDone || !taskB.isDone ) {
            Thread.sleep(10)
        }
        mixCoffeeAndMilk()

    }

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

//멀티쓰레드로 만들땐
//커피 한잔 만드는데 걸리는 시간: 3042 ms
// 커피 두잔 만드는데 걸리는 시간: 5046 ms