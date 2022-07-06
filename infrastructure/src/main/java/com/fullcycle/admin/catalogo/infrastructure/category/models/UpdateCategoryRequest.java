package com.fullcycle.admin.catalogo.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCategoryRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("is_active") Boolean active
) {

    public boolean isActive() {
        return this.active != null ? this.active() : true;
    }

}
