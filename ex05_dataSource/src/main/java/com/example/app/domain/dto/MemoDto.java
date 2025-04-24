package com.example.app.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {
	@Min(value = 10,message = "ID�� 10�̻� �̾���մϴ�.")
	@Max(value=65535,message="ID�� �ִ���ڴ� 65535 �����̾�� �մϴ�")
	@NotNull(message="ID�� �ʼ��׸��Դϴ�")
	private Integer id;
	@NotBlank(message="�޸� �Է��ϼ���")
	private String text;
	@NotBlank(message="�޸� �Է��ϼ���")
	@Email(message="example@example.com�� �°� �Է����ּ���")
	private String writer;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createAt;
	
	private LocalDate dateTest;
}
