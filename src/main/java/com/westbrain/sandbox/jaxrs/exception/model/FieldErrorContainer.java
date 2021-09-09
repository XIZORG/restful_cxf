package com.westbrain.sandbox.jaxrs.exception.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldErrorContainer {
    private List<FieldErrorModel> errorList = new ArrayList<>();
}
