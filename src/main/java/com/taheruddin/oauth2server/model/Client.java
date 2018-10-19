package com.taheruddin.oauth2server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString(exclude = "secret")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    private String id;

    private String secret;

    private String redirectUri;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_grant_type",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "grant_type_id")
    )
    private Set<GrantType> grantTypes;
}
