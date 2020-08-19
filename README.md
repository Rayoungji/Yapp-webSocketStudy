
## 메소드 설명

* reginsterStompEndpoints : 클라이언트에서webSocket을 연결할 api를 설정하기 위한 메소드  
  * addEndpoint로 여러개의 endPoint를 설정할 수 있다  
* configureMessageBroker : 메세지 라우팅을 위한 메소드
  * enableSimpleBroker : 인메모리 기반의 메세지 브로커로 해당 api를 구독하고 있는 클라이언트에게 메세지를 전달한다
     * RabbitMQ 또는 ActiveMQ와 같은 다른 모든 기능을 갖춘 메세지 브로커를 자유롭게 사용 가능하다
  * setApplicationDestinationPrefixes : 클라이언트로부터 메세지를 받을 api prefix(고정링크)를 설정한다 / message - handler 과 연결된다

* sendMessage : 클라이언트에서 /app + @MessageMapping의 api로 수신된 메세지를 처리한다  
chatMessage의 파라미터로 메세지가 binding되어 서버로 넘어오고 이 메세지는 메소드의 실행으로 메세지 브로커에게 전달된다 메세지 브로커는 /topic/public을 구독한 클라이언트들에게 전달받은 메세지를 브로드캐스팅한다
* addUser : 클라이언트에서 /app + @MessageMapping의 api로 수신된 메세지를 처리한다  
SimpleMessageHeaderAccessor로 채팅 사용자의 이름을 전달하고 메소드의 실행으로 메세지 브로커에게 메세지 내용이 전달된다 메세지 브로커는 /topic/public을 구독한 클라이언트들에게 전달받은 메세지를 브로드캐스팅한다


## 흐름 요약

<img width="1012" alt="스크린샷 2020-08-19 오후 5 14 24" src="https://user-images.githubusercontent.com/50194824/90611642-f89cd200-e241-11ea-873e-54b742b1a5a7.png">

클라이언트는 /app 을 통하여 메세지 전달을 요청 & /topic을 통하여 구독을 요청  

MessageHandler 은 메세지와 구독 요청을 메세지브로커에게 전달  

메세지 브로커는 구독 요청을 등록하고 받은 메세지를 구독요청한 클라이언트들에게 브로드 캐스트하여 전달  

## 참고 자료
* https://asfirstalways.tistory.com/359  
* https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/web.html#websocket-stomp-message-flow
