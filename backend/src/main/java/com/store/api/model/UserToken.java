package com.store.api.model;

import Util.Helper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToken extends AbstractAuditModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="user_id")
    private long userId;

    private boolean isRevoked;

    private String token;

    public boolean isValid() {
        return isRevoked || Helper.isExpiredToken(this.getCreatedAt());
    }

}
