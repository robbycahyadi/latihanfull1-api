package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class MasterColorDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MasterColorRequestNewDTO {
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MasterColorRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        @NotNull
        private String code;
        private String description;
    }
}
