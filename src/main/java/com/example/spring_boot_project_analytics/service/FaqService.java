package com.example.spring_boot_project_analytics.service;

import com.example.spring_boot_project_analytics.dataTransferObject.FaqDto;

import java.util.List;

public interface FaqService {
    FaqDto getFaqById(Long faq_id);
    FaqDto createFaq(FaqDto faqDto);
    List<FaqDto> getAllFaqs();

}
