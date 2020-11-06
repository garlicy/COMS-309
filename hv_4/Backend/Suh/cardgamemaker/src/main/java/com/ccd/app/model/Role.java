package com.ccd.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
/**
 * Entity Model of Role
 * @author jsuh_mac
 *
 */
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> user;

    /**
     * get id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     * @param id set to this id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * set name
     * @param name set to this name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * get user
     * @return User
     */
    public Set<User> getUser() {
        return user;
    }
    
    /**
     * set user
     * @param user set to this user
     */

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
