package com.example.bookshop.models;

import com.example.bookshop.enums.Errors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Errors> errors;

    public void appendError(Errors error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }
    @JsonProperty("success")
    public boolean isSuccess(){
        return errors == null || errors.isEmpty();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object message;
}
