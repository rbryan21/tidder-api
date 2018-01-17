package com.tidder.api.domain.associative.key;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class UserThreadId implements Serializable {

    private Long userId;

    private Long threadId;

}
