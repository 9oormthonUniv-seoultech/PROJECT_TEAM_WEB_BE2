package com.pocket.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class User extends BaseEntity {
    private String name;
    private String email;
}
