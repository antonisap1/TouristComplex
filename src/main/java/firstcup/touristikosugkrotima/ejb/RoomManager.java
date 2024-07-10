/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.touristikosugkrotima.ejb;

import firstcup.touristikosugkrotima.entities.Book;
import firstcup.touristikosugkrotima.entities.Room;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author antho
 */
@Stateless
@Named(value = "roomManager")
public class RoomManager {

    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = Logger.getLogger("order.ejb.RequestBean");
    
    //creates new room object
    private Room room = new Room();

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    //creating new room using its attributes
    public void createRoom(Long id, String roomType, String category,Boolean availability) {
        try {
            room = new Room(id, roomType, category,availability);
            em.persist(room);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    //getting all room list
    public List<Room> getRooms() {
        List<Room> rooms = (List<Room>) em.createNamedQuery("findAllRooms").getResultList();
        return rooms;
        //return em.createQuery("SELECT r FROM Room r", Room.class).getResultList();
    }
    //finds the room client asked using the roomType and category parameters
    public Long identifyRoom(String roomType,String category)
    {
           TypedQuery<Long> query = (TypedQuery<Long>) em.createNamedQuery("identifyRoom");
            query.setParameter("roomType",roomType);
            query.setParameter("category",category);
            List<Long> roomIdList = query.getResultList();
            if (!roomIdList.isEmpty()) {
                return roomIdList.get(0);
            } else {
            return null; // or throw an exception, or handle the "not found" case as needed
            }
    }
    //counts all available rooms based on parameters eg.for roomtype room and category simple
    public long countAvailableRooms(String roomType, String category) {
    try {
        TypedQuery<Long> query =(TypedQuery<Long>) em.createNamedQuery("countAvailableRooms");
        query.setParameter("roomType", roomType);
        query.setParameter("category", category);
        return query.getSingleResult();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}

    //creates new book object
    private Book book = new Book();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    //creating a new booking in the book table based on its attributes
    public void createBook(Long id, String checkInDate,String checkOutDate,Long roomId) {
        try {
            book = new Book(id, checkInDate, checkOutDate, roomId);
            em.persist(book);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    //gets a list of all bookings
    public List<Book> getBooks() {
        List<Book> books = (List<Book>) em.createNamedQuery("findAllBooks").getResultList();
        return books;
        //return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
    //counts all bookings
    public int countAllBooks() {
        int count = 0;
        try {
            count = em.createNamedQuery(
                    "findAllBooks")
                    .getResultList()
                    .size();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return count;
    }
    
     //updates the availability in room table for a room that gets booked
     public void updateRoomAvailability(long roomId, boolean availability) {
    try {
        // Find the room entity by ID
        Room room = em.find(Room.class, roomId);
        //if room gets found
        if (room != null) {
            // Update the availability status
            room.setAvailability(availability);
            em.merge(room); // Persist the change
            System.out.println("Updated availability for room ID: " + roomId + " to: " + availability);
        } else {
            System.out.println("Room with ID: " + roomId + " not found.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    

    /**
     * Creates a new instance of RoomManager
     */
    public RoomManager() {

    }

}
//an einai dia8esimo to dwmatio
    /* public Boolean isAvailable(long id) {
        try {
            Query query = em.createNativeQuery("SELECT availability FROM Room WHERE id = ?");
            query.setParameter(1, id);

            List<Boolean> roomValid = query.getResultList();
            System.out.println("Room ID: " + id + ", Availability result: " + roomValid);

            //an i lista den einai adeia
            if (!roomValid.isEmpty()) {
                Object result = roomValid.get(0);
                System.out.println("Result type: " + result.getClass().getName() + ", Value: " + result);

                // Convert result to Boolean, handling various possible types
                Boolean availability;
                if (result instanceof Boolean) {
                    availability = (Boolean) result;
                } else if (result instanceof Number) {
                    availability = ((Number) result).intValue() != 0;
                } else {
                    availability = Boolean.valueOf(result.toString());
                }
                System.out.println("Returning availability: " + availability);
                return availability;

            } else {
                System.out.println("No result found for room ID: " + id);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
}*/
