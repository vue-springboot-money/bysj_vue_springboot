package com.ttcanteen.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("用户")
@AllArgsConstructor
public class TbUserEntity {
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "用户名", required=true)
	private String username;
	
	@ApiModelProperty(value = "密码", required=true)
	private String password;
	
	@ApiModelProperty(value = "昵称")
	private String nickname;
	
	@ApiModelProperty(value = "性别（女：0，男：1）")
	private byte sex;
	
	@ApiModelProperty(value = "类别（教师：0，学生：1，管理员：2）")
	private byte type;
	
	@ApiModelProperty(value = "账户余额")
	private Float balance;

	@ApiModelProperty(value = "创建时间")
	private Date createtime;
}
