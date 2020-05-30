package com.example.demo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "social")
@Data
@NoArgsConstructor
public class SocialAccount {

    @Id
    private String socialID;

    @Column
    private String login;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account", referencedColumnName = "accountID")
    private Account account;

}
