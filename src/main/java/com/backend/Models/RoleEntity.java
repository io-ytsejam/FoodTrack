package com.backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "ROLE", schema = "TEST", catalog = "")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleid;
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleid=" + roleid +
                ", name='" + name + '\'' +
                '}';
    }
}
