package net.jinocryst.jinocrystrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloWorldBean {

    private String message; //final로 선언하면 @Data에서 setter가 생기지 않는다.


}
