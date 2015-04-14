#!/usr/bin/osascript

on run argv

    repeat 30 times
        try
            tell application "System Events" to set frontmost of process "iOS Simulator" to true
            exit repeat
        on error msg
            log "BringIOSSimulatorToFront: Failed to bring iOS Simulator to front: " & msg
            log "BringIOSSimulatorToFront: Retrying..."
            delay 1
        end try
    end repeat

end run
