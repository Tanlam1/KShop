package com.kshop.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
	private String recipient; // người nhận
    private String msgBody; // nội dung
    private String subject; // tiêu đề
    private String attachment; // file đính kèm
}
