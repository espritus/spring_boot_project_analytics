package com.example.spring_boot_project_analytics.Faq.controller;

import com.example.spring_boot_project_analytics.Faq.dto.FaqDto;
import com.example.spring_boot_project_analytics.Faq.service.FaqService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/faqs")
public class FaqController {

    private FaqService faqService;
    //Build Post FAQ REST API
    @PostMapping
    public ResponseEntity<FaqDto> createFaq(@RequestBody FaqDto faqDto){
        FaqDto saved_fag = faqService.createFaq(faqDto);
        return new ResponseEntity<>(saved_fag, HttpStatus.CREATED);
    }

    //Build Get FAQ REST API
    @GetMapping("{id}")
    public ResponseEntity<FaqDto> getFaqById(@PathVariable("id") Long faq_id) {
        FaqDto faqDto = faqService.getFaqById(faq_id);
        return ResponseEntity.ok(faqDto);
    }
    //Build Get All FAQ REST API
    @GetMapping
    public ResponseEntity<List<FaqDto>> getAllFaqs() {
        List<FaqDto> faqs = faqService.getAllFaqs();
        return ResponseEntity.ok(faqs);
    }

}
