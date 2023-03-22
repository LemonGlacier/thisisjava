package ch12.sec03.exam05;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
public class Member {
	private final String id;
	@NonNull String name;
	private int age;

}
