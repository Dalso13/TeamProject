package org.worldfinder.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	private String u_writer, u_pw, u_name, phone, mail, nationality, auth, old_pw, birth, gender;
	private Date reg_date;
}
