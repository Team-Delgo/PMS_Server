package com.pms.comm.ncp.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class SmsMessageDTO {
    private String to;
    private String content;
}
