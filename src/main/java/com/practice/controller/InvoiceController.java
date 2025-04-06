package com.practice.controller;

import com.practice.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
}
