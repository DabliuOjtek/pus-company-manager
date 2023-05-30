package com.pus.companymanager.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
    private Long memberId;
    private String memberEmail;
}
