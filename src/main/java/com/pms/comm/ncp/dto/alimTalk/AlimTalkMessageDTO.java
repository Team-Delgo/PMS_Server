package com.pms.comm.ncp.dto.alimTalk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class AlimTalkMessageDTO {
    private String countryCode;
    private String to;
    private String content;
    private List<AlimTalkButtonDTO> buttons;
}
