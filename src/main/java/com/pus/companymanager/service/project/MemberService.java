package com.pus.companymanager.service.project;

import com.pus.companymanager.repository.project.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;
}
