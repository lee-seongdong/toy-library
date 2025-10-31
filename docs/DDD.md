# Domain-Driven Design (DDD)
복잡한 비즈니스 문제를 해결하기 위해, **비즈니스 영역(도메인)을 중심으로** 소프트웨어를 설계하는 방법론  

> DDD와 전통적 설계의 차이
> |구분|DDD|전통적 방식|
> |---|---|---|
> |설계 중심|도메인(비즈니스 로직)|테이블(데이터베이스)|
> |설계 방향|도메인 모델 -> DB|ERD -> DB -> 클래스|
> |중심 용어|비즈니스 용어|기술적 용어|
> |로직 위치|도메인에 집중|Service 계층에 분산|

## 1. 구분
목적에 따라 세부 설계 기법으로 구분
- [전략적 설계](전략적%20설계.md): 무엇을 해결할 것인가?
- [전술적 설계](./전술적%20설계.md): 어떻게 구현할 것인가?


## 2. 안티패턴
### 2.1. 빈약한 도메인 모델(Anemic Domain Model)
문제: 로직이 없고, getter와 setter만 존재하는 도메인
```java
public class Loan {
    private Long id;
    private Book book;
    private LocalDate dueDate;
    // getter/setter만 존재
}

// 로직이 Service에 집중
public class LoanService {
    public void extendLoan(Loan loan, int days) {
        loan.setDueDate(loan.getDueDate().plusDays(days));
    }
}
```

해결: 풍부한 도메인 모델로 전환. 도메인에 비즈니스 로직을 배치
```java
public class Loan {
    private Long id;
    private Book book;
    private LocalDate dueDate;
    
    public void extend(int days) {
        if (this.isOverdue()) {
            throw new CannotExtendOverdueLoanException();
        }
        this.dueDate = this.dueDate.plusDays(days);
    }
}
```

### 2.2. 과도한 애그리거트
문제: 너무 큰 애그리거트. 성능 저하 및 동시성 문제 발생
```java
// Member가 모든 Loan을 직접 관리
public class Member {
    private List<Loan> loans; // 수백개의 대출 기록
    private List<Reservation> reservations;
    private List<Review> reviews;
}
```

해결: 애그리거트 경계를 작게 유지 (ID참조)
```java
public class Member {
    private Long id;
    // 다른 애그리거트는 ID로만 참조
}

public class Loan {
    private Long memberId; // Member 애그리거트 참조
    private Long bookId;   // Book 애그리거트 참조
}
```

### 2.3. 잘못된 컨텍스트 경계
-문제: 모든것을 하나의 컨텍스트에 배치하여 복잡도 증가
```
library/ (모든 것이 하나)
  ├── Book (대출 정보 + 카탈로그 정보 혼재)
  └── ...
```

해결: 유비쿼터스 언어가 다른 의미로 작용되는 지점에서 컨텍스트 분리
```
library/
├── loan/       (대출 컨텍스트)
│   └── Book (대출 상태, 반납일)
└── catalog/    (카탈로그 컨텍스트)
    └── Book (제목, 저자, 출판사)
```

### 2.4. 기술 중심 설계
문제: JPA 엔티티 부터 설계하여 도메인 로직이 기술에 종속
```java
@Entity
@Table(name = "loans")
public class Loan {
    @Id 
    @GeneratedValue
    private Long id;
    // JPA 제약에 맞춰 설계
}
```
해결: 도메인 모델을 먼저 설계한 후, 영속성 매핑
```java
// 1단계: 순수 도메인 모델 설계
public class Loan {
    private LoanId id;
    private Book book;
    
    public void extend(int days) { ... }
}

// 2단계: 인프라에서 매핑
@Entity
public class LoanJpaEntity {
    // JPA 매핑은 인프라 계층에서
}
```


## 3. 도구
- Context Mapper: DDD 모델링 도구
- PlantUML: 텍스트기반 UML
- jMolecules: 도메인 모델 표현을 위한 어노테이션을 제공하는 라이브러리
- Spring Modulith: 모듈리스 애플리케이션 구축을 위한 Spring boot 툴킷
