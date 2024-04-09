package com.example.spring_boot_project_analytics.mapper;

import com.example.spring_boot_project_analytics.dataTransferObject.FaqDto;
import com.example.spring_boot_project_analytics.entity.Faq;

public class FaqMapper {
    public static FaqDto mapToFaqDto(Faq faq) {
        return new FaqDto(
                faq.getId(),
                faq.getQuestion(),
                faq.getAnswer()
        );

    }

    public static Faq mapToFaq(FaqDto faqDto) {
        return new Faq(
                faqDto.getId(),
                faqDto.getQuestion(),
                faqDto.getAnswer()
        );
    }
}
