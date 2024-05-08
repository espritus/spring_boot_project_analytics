package com.example.spring_boot_project_analytics.Faq.service.impl;

import com.example.spring_boot_project_analytics.Faq.dto.FaqDto;
import com.example.spring_boot_project_analytics.Faq.entity.Faq;
import com.example.spring_boot_project_analytics.exception.ResourceNotFoundException;
import com.example.spring_boot_project_analytics.Faq.mapper.FaqMapper;
import com.example.spring_boot_project_analytics.Faq.repository.FaqRepository;
import com.example.spring_boot_project_analytics.Faq.service.FaqService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FaqServiceImpl implements FaqService {

    private FaqRepository faqRepository;

    @Override
    public FaqDto getFaqById(Long faq_id) {
        Faq faq = faqRepository.findById(faq_id).orElseThrow(
                () -> new ResourceNotFoundException("That question does not exist with the given id: " + faq_id)
        );
        return FaqMapper.mapToFaqDto(faq);
    }

    @Override
    public FaqDto createFaq(FaqDto faqDto) {
        Faq faq = FaqMapper.mapToFaq(faqDto);
        Faq savedFAQ = faqRepository.save(faq);
        return FaqMapper.mapToFaqDto(savedFAQ);
    }

    @Override
    public List<FaqDto> getAllFaqs() {
        List<Faq> faqs = faqRepository.findAll();
        return faqs.stream().map(FaqMapper::mapToFaqDto)
                .collect(Collectors.toList());
    }

}
