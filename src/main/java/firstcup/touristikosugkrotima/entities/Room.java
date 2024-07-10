/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.touristikosugkrotima.entities;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author antho
 */
@Named(value = "room")
        @Table(name = "room")
        @Entity
        @NamedQueries({
    @NamedQuery(
            name = "findAllRooms",
            query = "SELECT r FROM Room r "
            + "ORDER BY r.id"
    ),
    @NamedQuery(
    name = "identifyRoom",
    query = "SELECT r.id FROM Room r WHERE r.roomType = :roomType AND r.category = :category AND r.availability = true"
    ),
        @NamedQuery(
    name = "countAvailableRooms",
    query = "SELECT COUNT(r.id) FROM Room r WHERE r.roomType = :roomType AND r.category = :category AND r.availability = true"
    )})


public class Room implements Serializable {

    //iD|roomType|category|availability room table is being created here
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="roomType", nullable=false)
    private String roomType;
    @Column(name="category", nullable=false)
    private String category;
    @Column(name="availability",nullable=false)
    private Boolean availability;

    public Room() {
    }

    //Builds the constructor
    public Room(Long id, String roomType, String category,Boolean availability) {
        this.id=id;
        this.roomType=roomType;
        this.category=category;
        this.availability=availability;//To change body of generated methods, choose Tools | Templates.
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "firstcup.touristikosugkrotima.entities.Room[ id=" + id + " ]";
    }
    
}
