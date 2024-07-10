/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.touristikosugkrotima.web;

import firstcup.touristikosugkrotima.ejb.RoomManager;
import firstcup.touristikosugkrotima.entities.Book;
import firstcup.touristikosugkrotima.entities.Room;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author antho
 */
@Named(value = "choise")
@SessionScoped
public class Choise implements Serializable {

    private String name;
    private String roomType;
    private String category;
    private String checkInDate;
    private String checkOutDate;
    @NotNull(message = "Please assign a positive number")
    @Digits(integer = 2, fraction = 0)
    @Min(value = 1, message = "Error: A positive number is required.")
    private Integer number;
    private List<Room> rooms;
    private List<Book> books;
    private String confirmationCode;
    private static final Logger logger = Logger.getLogger("order.web.OrderManager");
    

    /**
     * Creates a new instance of Choise
     */
    @EJB
    private RoomManager rm;

    //gets all rooms from entity manager to display them
    public List<Room> getRooms() {
        try {
            this.rooms = rm.getRooms();
        } catch (Exception e) {
            logger.warning("Couldn't get rooms.");
        }

        return rooms;
    }
    

private String generateConfirmationCode() {
            return java.util.UUID.randomUUID().toString();
}

//gets all books from entity manager to display them
    public List<Book> getBooks() {
        try {
            this.books = rm.getBooks();
        } catch (Exception e) {
            logger.warning("Couldn't get books.");
        }

        return books;
    }

    //input is checked and submited to the Book database
    public String submitOrder() {
        try {
            //counting all bookings
            int id = rm.countAllBooks();
            //checking which of the rooms the user selected and if its available
            long validRoom;
            try {
                validRoom = rm.identifyRoom(roomType, category);
                logger.log(Level.INFO, "Identified room: {0}", validRoom);
            } catch (Exception e) {
                return "index.xhtml?faces-redirect=true";
            }
            //counts all the available identified rooms
            long countAvailableRooms;
            try {
                countAvailableRooms = rm.countAvailableRooms(roomType, category);
            } catch (Exception e) {
                return "index.xhtml?faces-redirect=true";
            }
            
            //if there is a valid room available and if the number the user gave as input is smaller or equal to total available room number
            if (validRoom != 0 && countAvailableRooms >= number) {
                try {
                    //creates a new booking or x number of booking depending on available rooms and user request 
                    for (int i = 0; i < number; i++) {
                        id++;
                        rm.createBook((long) id, checkInDate, checkOutDate, validRoom);
                        rm.updateRoomAvailability(validRoom, false);
                        //checks the other available room in case of more than one room booking
                        if(i<number-1)
                            validRoom = rm.identifyRoom(roomType, category);

                    }
                    //generates a random confirmation code
                    confirmationCode = generateConfirmationCode();
                    System.out.println(confirmationCode);
                } catch (Exception e) {
                    return "index.xhtml?faces-redirect=true";
                }
            } else {
                return "index.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "response.xhtml?faces-redirect=true";
    }
    

    public Choise() {
    }

    public String getName() {
        return name;

    }

    public void setName(String user_name) {
        this.name = user_name;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;

    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;

    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;

    }

    public String getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;

    }

    public String getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;

    }
    public String getConfirmationCode() {
        return confirmationCode;  // Getter for confirmationCode
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;  // Setter for confirmationCode
    }
}
/* boolean validation;
        try {
            validation = rm.isAvailable(validRoom);
            logger.info("Room availability: " + validation);
        } catch (Exception e) {
            logger.warning("Error in isAvailable: " + e.getMessage());
            return "index.xhtml?faces-redirect=true";
        }*/
