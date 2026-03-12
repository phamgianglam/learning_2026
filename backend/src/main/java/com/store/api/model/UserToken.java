package com.store.api.model;

import com.store.api.util.Helper;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user_tokens")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserToken extends AbstractAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private boolean isRevoked;

    private String token;

    public boolean isValid() {
        return isRevoked || Helper.isExpiredToken(this.getCreatedAt());
    }

}
