package com.coolcompany.smarthome.RemoteControl;

import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.Remote.*;

import java.util.HashMap;
import java.util.Map;

public class MyRC implements RemoteControl {

    private Map<String, RemoteCommand> codesOfCommand = new HashMap<>();
    private SmartHome smartHome;


    public MyRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }



    public void addCodeToCommand(String code, RemoteCommand command){
        codesOfCommand.put(code, command);
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        RemoteCommand rc = codesOfCommand.get(buttonCode);
        if(rc != null){
            rc.execute();
        }
        else {
            System.out.println("There aren't any commands in this button");
        }
    }

    public void initializateMyRC(){
        codesOfCommand.put("1", new AlarmActivateCommand(smartHome, "0000"));
        codesOfCommand.put("2", new AlarmDeactivateCommand(smartHome, "0000"));
        codesOfCommand.put("3", new AlarmAlertCommand(smartHome));
        codesOfCommand.put("A", new TurnOffAllLights(smartHome));
        codesOfCommand.put("B", new TurnOnAllLights(smartHome));
        codesOfCommand.put("C", new TurnOnHallLights(smartHome));
        codesOfCommand.put("D", new CloseHallDoorsCommand(smartHome));
    }

}
