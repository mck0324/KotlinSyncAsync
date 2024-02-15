package sample

import mu.KotlinLogging
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {  }

val single = Schedulers.newSingle("worker")

fun main() {
    measureTimeMillis {
        Mono.zip(
            subA(),
            subB()
        ).subscribeOn(single).block()
    }.let { logger.debug { ">> elasped : $it ms" } }
}




private fun subA(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "Start A" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { "End A" } }
}

private fun subB(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "Start B" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { "End B" } }

}

//싱글스레드에선 여러가지 일을 처리할 수 있어야하는데 자바에선 불가능하기에 리엑터라는 라이브러리를 사용
//그래서 single변수를 사용해서 싱글스레드로 만듬
//로그라이브 사용한 이유는 쓰레드가 어떤 함수가 어디서 실행되는지 보려고 한거임.