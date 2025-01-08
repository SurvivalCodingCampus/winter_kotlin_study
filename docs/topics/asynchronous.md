# 비동기 프로그래밍

## 동기 vs 비동기
- 작업 완료를 기다리는 방식의 차이 
- 동기: 한 작업이 끝날때 까지 기다렸다가 다음 작업을 시작함
- 비동기: 기다리지 않고 다른 작업을 시작함

## 동기 프로그래밍
- 코드가 순서대로 실행된다.
- 작업이 완료될때 까지 프로그램이 중단될 수 없다. 
- 모든 작업은 이전 작업의 실행이 완료될때 까지 기다려야 한다.
- 코드의 실행 순서가 예측 가능하다.

## 비동기 프로그램 
- 여러 작업이 병렬로 실행될 수있다. 
- 한 작업이 완료 여부와 관계없이 다음 작업을 시작할 수 있다.
- 작업의 완료 순서를 예측할 수 없다.
- I/O 작업이나 네트워크 요청과 같이 시간이 오래 걸리는 작업에 유용하다. 

## 동시성 vs 병렬성
- 여러 작업을 처리하는 방식에 대한 차이 
- 동시성: 여러 작업을 번갈아가면서 처리하는 것
- 병렬성: 여러 작업을 실제로 동시에 처리하는것 

## 동시성
- 여러 작업이 논리적으로 동시에 실행되는 것처럼 보이는 개념
- 시분할 방식으로 여러 스레드를 활용해 동시성을 구현할 수 있음

## 병렬성
- 여러 작업이 물리적으로 동시에 실행되는 개념
- 멀티코어 환경에서 실제로 여러 스레드가 병렬로 실행될 수 있음 

## 정리
- 하나의 교사가 여러가지 일을 하는 것(싱글 코어)
-> 동시성

- 교사와 조교가 함께 일을 하는 것(멀티 코어)
-> 병렬성

- 순서대로 실행하는 것
-> 동기

- 동시에 실행하는 것
-> 비동기(동시성, 병렬성을 모두 표현할 수 있음)


## 동기 프로그래밍
```text
코드가 순서대로 실행된다.
작업이 완료될 때까지 프로그램이 중단 될 수 없다.
모든 작업은 이전 작업의 실행이 완료될 때 까지 기다려야 한다. 
```

## 병렬성
```text
임의의 순서로 또는 동시에 작업이 실행될 수 있다.
비동기를 처리하는 방법에는 Thread, 콜백, Future 방식이 있다.
그리고 Coroutine이 있음.
```


## 왜 코루틴을 써야 하는지?
- Java의 기본 API 로도 충분히 가능한데 왜 코루틴을 배워야 하는가?

## 현재 Thread 확인
```kotlin
println(Thread.currentThread())
```
-> 프로그램은 main 스레드에서 실행 됨 

## 단일 Thread의 문제점
- 모든 인스턴스는 main스레드에서 생성되며 처리 된다.
- Thread를 Block하는 동안 (sleep 메서드)프로그램이 멈춘다.


## 코루틴의 등장
- 코틀린 1.3에 등장
- 경량
- 메모리 누수 감소
- 기본 제공 취소 지원
- Jetpack 통합
- 구글은 이후에 비동기 처리 방식은 kotlin과 코루틴을 권장하게 됨
- 자바로 개발할 때 최대 단점은 


## 콜백 방식의 처리 예
```kotlin
object Weathers

fun Weathers.tomorrow(callback: (String) -> Unit): Unit {
    Thread.sleep(2000)
    callback("완료")
}

fun main() {
    println("main start")
    Weathers.tomorrow {
        println(it)
    }

    println("main end")
}
```

## 콜백 기준의 동기화 방식의 문제점
- 코드의 깊이가 깊어지고 관리하기 어려워짐
- 콜백 지옥
- 디버깅이 어려움
- 병렬처리의 어려움 

## 동시 실행 라이브러리
- 스레딩의 기본 메커니즘을 이해했다면 높은 수준의 추상화를 제공하는 라이브러리를 사용하는것이 좋음 
RxJava
Kotlin Coroutine

## Coroutine
- Kotlin 표준 병해 프로그래밍 API
- 코루틴은 오래 걸리는 작업을 수행하는 방법 중 우아하고 효율적인 방법 중 하나이다.
- 콜백 기반 코드를 순차 코드로 변환할 수 있다.
- 순차 코드는 읽기 쉽고 에러 처리가 편하다.


## 일반적인 멀티태스킹과 코루틴
- 일반적으로는 하나의 코루틴이 특정한 스레드에서 동작
- 코틀린 코루틴은 suspend와 resume을 통해 스레드를 양보한다.
- 코루틴은 비동기식이며 스레드를 차단하지 않는다.
- 코루틴은 일시 중단 함수를 사용하여 비동기 코드를 순차적으로 만든다.

## Coroutine 사용시 이점
- 경량
코루틴은 실행 중인 스레드를 차단하지 않는 정지를 지원하여 메모리를 절약하면서 많은 동시 작업을 처리한다.
- 메모리 누수 감소
스레드를 활용한 동기화는 휴먼 에러에 의한 메모리 누수나 데드락과 같은 위험이 존재하지만, 코루틴은 안전하다.
- 스레드보다 가볍고 효율적이다.
- 많은 안드로이드 API가 지원하고 있다.

## Coroutine 주요 컨셉
- 다른 언어의 async, await 같은 키워드가 제공되지 않는다.
- 대신 정지 함수를 활용한 안전하고 에러가 발생하지 않는 비동기 처리에 중점을 두고 있음 

## Coroutine을 적용한 Hello World 코드 
```kotlin
fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

Hello
World // 1초후에 
```


## 코루틴 스코프
- 코루틴은 스코프에 의존적이다.

## 코루틴 빌더 
- 새로운 코루틴을 시작하는 함수를 코루틴 빌더라고 한다. 
-> runBlocking, withContext, ContextScope.launch, ContextScope.async 등
- 코루틴 빌더는 코루틴의 생명주기를 제한하는 CoroutineScope를 생성한다.
- launch로 새로운 코루틴을 시작해도 독립적으로 동작


## 코루틴 빌더: launch
```text
// - 반환값: Job
// - 용도: 결과가 필요없는 비동기 작업
launch {
    // 비동기 작업 
}

-> Android 에서 주로 사용 
```

## 코루틴 빌더: async
```text
// - 반환값: Deffered<T>
// - 용도: 결과가 필요한 비동기 작업
async {
    // 결과를 반환하는 비동기 작업 
}

거의 안쓸거임 
```
## 코루틴 빌더: runBlocking
```text
// - 반환값: T
// - 용도: 코루틴과 일반 코드 연결
runBlocking {
    // 블로킹 방식으로 코루틴 실행
   
}

Kotlin 하는 동안 Unit Test에서만 사용 
```

## delay
- suspend 함수는 일정시간 코루틴을 일시 정지함
- 코루틴이 정지되어도 Main Thread는 차단되지 않음 
- suspend 함수는 CoroutineScope 블록 or 다른 suspend 함수에서 호출 가능 

## suspend
- suspend는 비동기 함수를 표기하는 코틀린 문법이다.
- suspend 함수가 실행되면 완료될때 까지 일시 중지 됨
- 그 사이 다른 함수나 코루틴이 실행될수 있음 


## 구조화된 동시성

- 코루틴의 수명은 runBlocking, launch 와 같은 CoroutineScope 내에서 결정됨
- 이러한 규칙은 메모리 누수나 손실을 방지한다.


## CoroutineScope 정리
- 모든 코루틴을 추적하여 실행되어야 하는 시기를 관리하는 구성요소
- 모든 비동기 작업을 특정 스코프에서 실행되어야 함
- 코루틴은 스코프에 의존적이다.
- 적절한 스코프가 없으면 전역적인 스코프인 Global Scope를 사용 가능

## 코루틴은 기본적으로 순차 실행
- suspend 함수 2개를 실행하면 순차적으로 실행 


## 코루틴으로 병렬 실행 : async 코루틴 빌더와 await 함수 
- async는 launch와 기본적으로 동일한 코루틴 빌더이지만 Job 대신 나중에 결과를 리턴하는 Deffered 타입을 반환 (Deffered는 Job을 구현 )
- Deffered는 await()를 통해 미래의 값을 얻는다. 

## Job
- CoroutineBuilder에 의해 작성된 코루틴
- launch는 Job을 리턴
- Job을 통해 실행중인 코루틴 제어
- Join은 해당 코루틴 내에 처리가 끝나는 것을 기다린다. 

## 코루틴 취소 
- Job을 통해 코루틴 취소 가능 

## Dispatcher 
- 코루틴이 어떤 스케줄러에 의해 수행될지를 지정할 수 있다.
- Default
  - 백그라운드 스레드 
  - 코어 수에 비례해서 스레드를 만든다. 연산용
  - CPU에 부하를 줄만한 처리 (로컬에서 List 정렬)

- Main
    - 메인 스레드에 연결되는 디스패처
  
- IO
    - 백그라운드 스레드
    - Input/Ouput 조작용
    - 상대적으로 많은 스레드를 만드는 디스패처(DB 통신 / Http 통신)

- Unconfied
  - 특정 스레드를 한정하지 않음. 공식문서에서는 보통 사용하지 않는다고 명시되어 있음 

## withContext()
- 디스패처를 전환해주는 함수

## 디스패처 전환 사용 예
디스패처를 전환하여 Worker 스레드와 Main 스레드를 넘나들 수 있다. 

## 정리
- Thread.sleep()은 스레드를 차단 (점유)하는 문제가 있음
- delay()는 현재 실행되는 코드 블록을 일정 시간 쉬게하고 다른 코드 블록에 스레드를 쓸 기회르 준다. 
- 이것을 중단 한다.
- Thread.sleep은 소비하지만, delay는 양보한다. 

## 병렬 처리
- 병렬 처리는 동시에 여러가지 일을 진행하는 것이다.
- vs 동시성 (Concurrency) = 동시에 실행되는 것 같아 보임 


## 정리
- 병렬 프로그래밍은 성능 향상의 장점이 있다.
- 병렬 프로그래밍은 다음과 같은 단점이 있다.
    - 복잡도 증가
    - 동기화 필요
    - 디버깅 어려움

## 정리
- 코루틴은 비동기 프로그래밍을 효율적으로 만들어주는 도구이다.
- 복잡한 비동기 작업을 동기 코드처럼 작성할 수 있다.
- Compose UI는 코루틴 기반으로 동작한다.
- 많은 Android 컴포넌트 코루틴을 지원한다. 

















