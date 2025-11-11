# Loan 컨텍스트

## 아키텍처
레이어드 아키텍처  
의존성 방향: 단방향

```
┌─────────────────┐
│   Presentation  │
└─────────────────┘
          │
          ▼
┌─────────────────┐
│   Application   │
└─────────────────┘
          │
          ▼
┌─────────────────┐
│     Domain      │   ← Domain이 Interface 정의 (repository)
└─────────────────┘
          ▲           ← 의존성 역전 (DIP)
          │
┌─────────────────┐
│ Infrastructure  │     ← Infrastructure가 Domain의 Interface 구현
└─────────────────┘
```
