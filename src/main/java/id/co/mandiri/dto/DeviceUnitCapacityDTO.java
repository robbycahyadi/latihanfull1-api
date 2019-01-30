package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeviceUnitCapacityDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceUnitCapacityRequestNewDTO {
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceUnitCapacityRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }
}
