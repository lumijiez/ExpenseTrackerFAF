package com.faf223.expensetrackerfaf.dto;

import com.faf223.expensetrackerfaf.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncomeCreationDTO {
    private long incomeId;
    private User userDTO;
}
