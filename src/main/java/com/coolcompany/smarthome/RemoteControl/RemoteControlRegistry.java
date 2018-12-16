package com.coolcompany.smarthome.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlRegistry {

    private final Map<String, RemoteControl> remotes;

    public RemoteControlRegistry(){
        remotes = new HashMap<>();
    }

    public void registerRemoteControl(RemoteControl rc, String rcId){
        remotes.put(rcId, rc);
    }

    public RemoteControl getRemoteControl(String rcId){
        RemoteControl rc = remotes.get(rcId);
        return rc;
    }
}
