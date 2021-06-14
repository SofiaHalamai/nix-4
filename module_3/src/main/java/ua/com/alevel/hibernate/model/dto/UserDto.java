package ua.com.alevel.hibernate.model.dto;

import java.util.List;

public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Integer> listIdAccounts;


    public UserDto(Integer id, String firstName, String lastName, String email, List<Integer> listIdAccounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.listIdAccounts = listIdAccounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getListIdAccounts() {
        return listIdAccounts;
    }
}
