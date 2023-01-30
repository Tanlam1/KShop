package com.kshop.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSort {
    
    private Date start_date = new Date();
    private Date end_date = new Date();
    private int timeSpeci = -1; // thời gian cụ thể: tháng hoặc năm cụ thể
    private int type = 0;
    private Object objs;
    
    //type: 0 => sort theo start end date
    //type: 1 => sort theo tháng cụ thể
    //type: 2 => sort theo năm cụ thể
    
}
