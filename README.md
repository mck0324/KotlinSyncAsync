# KotlinSyncAsync
Blocking 과 Non-Blocking
Os Kernel처리를 쓰레드가 기다리는지 기다리지 않는지.

Syncrhronous와 Asynchronous
싱글스레드가 Task를 순차적으로 실행하는지,아니면 동시에 여러개 처리하는지

커피를 1잔,10잔 만드는 예시로 소요시간을 비교해봤습니다.
1.Sync-Blocking의 방식일 경우
1잔: 커피 만드는데 걸리는 시간: 5042 ms
10잔: 커피 만드는데 걸리는 시간: 50250 ms

2.MultiThread(직원)의 방식일 경우
1잔: 커피 만드는데 걸리는 시간: 3025 ms
10잔: 커피 만드는데 걸리는 시간: 21110 ms

3.NonBlocking를 이용한 Asnyc 의 방식(싱글쓰레드)
1잔: 커피 만드는데 걸리는 시간: 3066 ms
10잔: 커피 만드는데 걸리는 시간: 3057 ms

