# VO, DTO 차이

1. DTO는 데이터 전송만을 위한 객체, VO 는 특정 비즈니스 로직을 가짐
2. DTO는 데이터 전달 목적, VO는 객체 자체 값으로 사용
    - 외부 시스템과 데이터 통신의 경우 DTO, DB에서 가져오는 data 는 VO 로 정의 후 사용
3. DTO 는 rw 가 가능해 가변성을 갖고 VO 는 불변성 및 read-only 속성을 가짐
4. VO 는 equals(), hashCode()를 오버리이딩해 각 객체의 동일성 판별


# git commit message
제목과 본문을 빈 행으로 구분  
제목은 영문 기준 50글자 이하    
첫 글자는 대문자로 작성  
제목 끝에 마침표X  
제목은 명령문으로 사용, 과거형X   
본문의 각 행은 영문 기준 72글자 이하  
어떻게 보다는 무엇과 왜  

```properties
Feat 	: 새로운 기능 추가
Fix	: 버그 수정
Docs	: 문서 수정
Style	: 코드 스타일 변경(formatting, ; 누락 등) 기능 수정이 없는 경우
Design	: 사용자 UI 디자인 변경(css 등)
Test	: 테스트 코드, 리팩토링 테스트 코드 추가
Refactor	: 코드 리팩토링
Build	: 빌드 파일 수정
Ci	: CI 설정 파일 수정
Perf	: 성능 개선
Chore	: 빌드 업무 수정, 패키지 매니저 수정(git.ignore 수정 등)
Rename	: 파일 또는 폴더명을 수정만 한 경우
Remove	: 파일을 삭제만 한 경우
```

#### 관련 이슈 - 해결
```properties
Close	: 종료
Fixes	: 수정
Resolves	: 해결
```
#### 관련 이슈 - 참고
```properties
Ref	: 참고
Related to	: 관련
See also	: 참고
```
