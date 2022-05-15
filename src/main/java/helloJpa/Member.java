package helloJpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member  {

    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

    //주소
    //멤버에 소속된거라서 요아이는 조회할 때 같이 조회 됨
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name ="FAVORITE_FOOD", joinColumns =
         @JoinColumn(name ="MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name ="ADDRESS", joinColumns =
    @JoinColumn(name ="MEMBER_ID"))
    private List<Address> addressesHistory = new ArrayList<>();


    public Member() {
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressesHistory() {
        return addressesHistory;
    }

    public void setAddressesHistory(List<Address> addressesHistory) {
        this.addressesHistory = addressesHistory;
    }
}
