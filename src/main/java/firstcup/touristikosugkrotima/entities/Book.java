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
import javax.persistence.Table;
import javax.persistence.NamedQuery;

/**
 *
 * @author antho
 */
@Named(value="book")
@Table(name="book")
@Entity
@NamedQuery(
    name="findAllBooks",
    query="SELECT b FROM Book b " +
          "ORDER BY b.id"
)
public class Book implements Serializable {

    //id|checkInDate|checkOutDate|columnId All the columns of book table are being created here
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="checkInDate", nullable=false)
    private String checkInDate;
    
    @Column(name="checkOutDate", nullable=false)
    private String checkOutDate;
    
   //saves the roomId which is picked by the client
    @Column(name="roomId")
    private Long roomId;

    //constructor build
    public Book(Long id, String checkInDate, String checkOutDate,long roomId) {
        this.id=id;
        this.checkInDate=checkInDate;
        this.checkOutDate=checkOutDate; //To change body of generated methods, choose Tools | Templates.
        this.roomId=roomId;
    }

    public Book() {}

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCheckInDate()
    {
        return checkInDate;
    }
    public void setCheckInDate(String checkInDate)
    {
        this.checkInDate=checkInDate;
    }
    public String getCheckOutDate()
    {
        return checkOutDate;
    }
    public void setCheckOutDate(String checkOutDate)
    {
        this.checkOutDate=checkOutDate;
    }
    
    public long getRoomId()
    {
        return roomId;
    
    }
    public void setRoomId(long roomId)
    {
       this.roomId=roomId;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "firstcup.touristikosugkrotima.entities.Book[ id=" + id + " ]";
    }
    
}
