package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeviceConditionDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceConditionRequestNewDTO {
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceConditionRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }
}
