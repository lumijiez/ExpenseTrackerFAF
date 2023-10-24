package com.faf223.expensetrackerfaf.dto;

import com.faf223.expensetrackerfaf.model.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonIgnoreProperties({"expenseCategory"})
public class ExpenseDTO {
    private long expenseId;
    private UserDTO userDTO;
    private ExpenseCategory expenseCategory;
    private LocalDate date;
    private BigDecimal amount;
}