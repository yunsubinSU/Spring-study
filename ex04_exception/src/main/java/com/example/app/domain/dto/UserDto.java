package com.example.app.domain.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String userid;		//����ID
	@NotBlank(message="password �� �Է��ϼ���")
	private String password;	//�н�����
	@NotBlank(message="rePassword �� �Է��ϼ���")
	private String rePassword;	//�н�����Ȯ��
	@NotBlank(message="username �� �Է��ϼ���")
	private String username;	//�����̸�
	@NotBlank(message="phone �� �Է��ϼ���")
	private String phone;		//��ȭ��ȣ
	@NotBlank(message="zipcode �� �Է��ϼ���")
	private String zipcode;		//�����ȣ
	@NotBlank(message="addr1 �� �Է��ϼ���")
	private String addr1;		//�⺻�ּ�
	@NotBlank(message="addr2 �� �Է��ϼ���")
	private String addr2;		//���ּ�
	
	@NotNull(message="birthday �� �Է��ϼ���")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;	//�������(yyyy-MM-dd)
}