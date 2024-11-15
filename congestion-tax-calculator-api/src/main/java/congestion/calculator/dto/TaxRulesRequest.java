package congestion.calculator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class TaxRulesRequest {

    @NotEmpty(message = "list of dates shouldn't be empty!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    private LocalDateTime[] datesTime;

    public LocalDateTime[] getDatesTime() {
        return datesTime;
    }
}
