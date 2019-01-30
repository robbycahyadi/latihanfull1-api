package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeviceBrandDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceBrandRequestNewDTO {
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceBrandRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }
}
