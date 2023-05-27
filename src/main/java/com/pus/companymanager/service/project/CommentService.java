package com.pus.companymanager.service.project;

import com.pus.companymanager.repository.project.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
}
