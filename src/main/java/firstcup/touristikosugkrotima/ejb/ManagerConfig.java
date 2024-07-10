/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstcup.touristikosugkrotima.ejb;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup

/**
 *
 * @author antho
 */

public class ManagerConfig {

    @EJB
    private RoomManager rm;
    //generates already existing data to the database
    @PostConstruct
    public void createData() {
        rm.createRoom((long)1, "Room", "Simple",false);
        rm.createRoom((long)2, "Room", "Simple",true);
        rm.createRoom((long)3, "Room", "Lux",false);
        rm.createRoom((long)4, "Room", "Lux",true);
        rm.createRoom((long)5, "Double-Room", "Simple",true);
        rm.createRoom((long)6, "Double-Room", "Simple",true);
        rm.createRoom((long)7, "Double-Room", "Lux",true);
        rm.createRoom((long)8, "Double-Room", "Lux",true);
        rm.createRoom((long)9, "Bangalow", "Simple",true);
        rm.createRoom((long)10, "Bangalow", "Simple",true);
        rm.createRoom((long)11, "Bangalow", "Lux",true);
        rm.createRoom((long)12, "Bangalow", "Lux",false);
        rm.createRoom((long)13, "Double-Room", "Simple",true);
        
        rm.createBook((long)1, "2024-06-30", "2024-07-01",(long)1);
        rm.createBook((long)2, "2024-07-01", "2024-07-02",(long)3);
        rm.createBook((long)3, "2024-07-01", "2024-07-02",(long)12);
    
    }
     @PreDestroy
    public void deleteData() {
        
    }
   
    
}
