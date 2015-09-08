#!/usr/bin/osascript

on run argv

    repeat 30 times
        try
        	if application "Simulator" is running then
            	tell application "System Events" to set frontmost of process "Simulator" to true
            end if
            if application "iOS Simulator" is running then
            	tell application "System Events" to set frontmost of process "iOS Simulator" to true
            end if
            exit repeat
        on error msg
            log "BringIOSSimulatorToFront: Failed to bring iOS Simulator to front: " & msg
            log "BringIOSSimulatorToFront: Retrying..."
            delay 1
        end try
    end repeat

end run
