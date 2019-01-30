package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeviceLoanStatusDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceLoanStatusRequestNewDTO {
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceLoanStatusRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }
}
