package lab2;

import java.util.List;
import java.util.ArrayList;

public class MadLib extends MadLibTemplate {

    List<String> strList = new ArrayList<String>();
    List<Integer> orderList = new ArrayList<Integer>();

    public MadLib(String title){
        super(title);
    }

    @Override
    void addString(String text) {
        //throw new RuntimeException("implement me");
        strList.add(text);
        orderList.add(1);
    }

    @Override
    void addSlot(String type) {
        //throw new RuntimeException("implement me");
        strList.add(type);
        orderList.add(0);
    }

    @Override
    void doLib(UserInterface ui) {
        //throw new RuntimeException("implement me");
        for (int i = 0; i < strList.size();i++){
            if (orderList.get(i) == 1){
                ui.writeString(strList.get(i) + "\n");
            } else {
                String temp = ui.promptUser("please enter a <" + strList.get(i) + ">");
                strList.set(i, temp);
            }
        }
    }

    @Override
    void printAsTemplate(UserInterface ui) {
        //throw new RuntimeException("implement me");
        for (int i = 0; i < strList.size();i++){
            ui.writeString(strList.get(i) + " ");
        }
    }

    // this is where most of your code is going to go
  
}


// but you can also declare your helper classes out here (or in their
// own files in the lab2 package).

