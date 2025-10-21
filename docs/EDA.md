# Event-Driven Architecture (EDA)
시스템이나 서비스간에 이벤트로 통신하는 시스템 구조. kafka와 같은 메시지 브로커를 통해 이벤트를 전달한다.

## 개념 정리
1. Event: 이벤트는 디커플링을 위한 **기술**. Spring 기반의 프로젝트에서는 `Spring Events`를 통해 구현
2. DDD Event: 도메인에서 비즈니스 의미를 담아 발행하는 이벤트
3. EDA: 시스템이나 서비스간에 이벤트로 통신하는 시스템 구조
4. Event Sourcing: 이벤트자체를 영속화 하여, 이를 기반으로 데이터를 재구성하는 기법
