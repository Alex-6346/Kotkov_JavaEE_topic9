package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     @Column(name = "login", unique = true)
     private String login;

     @Column(name = "password")
      private String password;

    @ManyToMany
    @JoinTable(name = "user_to_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<PermissionEntity> permissions;

    @ManyToMany
    @JoinTable(name = "user_to_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_isbn"))
    private List<BookDto> favouriteBooks;

    public UserEntity(String login,String password,List<PermissionEntity>permissions, List<BookDto> favouriteBooks){
        this.login=login;
        this.password=password;
        this.permissions=permissions;
        this.favouriteBooks=favouriteBooks;
    }

}
