package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;

    @Column
    private String title;

    @OneToMany(
            targetEntity = SocialAccount.class,
            mappedBy = "account",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<SocialAccount> socials;

}
