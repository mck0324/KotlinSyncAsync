package sample.coffee

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {  }

private val employee = Schedulers.newSingle("employee")

fun main() {
    measureTimeMillis {
//        Flux.range(1,10).flatMap {
//            makeCoffee()
//        }.subscribeOn(employee).blockLast()
        makeCoffee().subscribeOn(employee).block()
    }.let { logger.debug { ">>커피 만드는데 걸리는 시간: $it ms" } }
}

private fun makeCoffee(): Mono<Unit> {
    return Mono.zip(
        grindCoffee().then(brewCoffee()),
        boilMilk().then(formMilk()),
    ).then(mixCoffeeAndMilk())
}

private fun grindCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피원두 갈기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employee)
        .doOnNext { logger.debug { ">> 커피가루" } }
}

private fun brewCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피 내리기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employee)
        .doOnNext { logger.debug { ">> 커피 원액" } }
}

private fun boilMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유 끓이기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employee)
        .doOnNext { logger.debug { ">> 데워진 우유" } }
}

private fun formMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유 거품 내기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employee)
        .doOnNext { logger.debug { ">> 거품 우유" } }
}

private fun mixCoffeeAndMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피와 우유 섞기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(employee)
        .doOnNext { logger.debug { ">> 카페라떼 완성" } }
}


//싱글스레드로 Asnyc를 한다면 커피 만드는데 걸리는 시간: 3068 ms
//ex 한명에서 커피를 만듬
//쓰레드가 1명
//두잔을 만들경우 커피 만드는데 걸리는 시간: 3068 ms
//세잔을 만들경우 커피 만드는데 걸리는 시간: 3070 ms