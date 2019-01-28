
package fireworks;

import java.util.ArrayList;
import java.util.List;


public class InputsFromUser {

     double init_velocity;
     double angle;
     double start_x;
     double start_y;
     boolean checkBreak;
	 int fireworkType;
     static List<InputsFromUser> inputsList;

    public List<InputsFromUser> getInputsList() {

        return inputsList;
    }

    public void setInputsList(List<InputsFromUser> inputsList) {
        this.inputsList = inputsList;
       
    }

}
